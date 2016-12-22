/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacess;

import dataacess.auxiliarstructs.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Accesses the DB to get, set and update game's data.
 * 
 * @author Diogo Dinis
 */
public class DbGame {
    /**
     * Saves a ship's position and owner on a game
     * 
     * @param idOnGame      ship's id on the game
     * @param beginX        ship's position
     * @param endX          ship's position
     * @param beginY        ship's position
     * @param endY          ship's position
     * @param playerId      user's DB identifier who place the ship
     * @param gameId        game's DB identifier
     * @throws SQLException problems interacting with the DB
     */
    public static void setShipPosition(int idOnGame, int beginX, int beginY, int endX, int endY, int playerId, int gameId) throws SQLException {
        Connection conn = DbUtils.INSTANCE.openConnection();
        String insertShip = "INSERT INTO ships (id_on_game, x_begin, y_begin, x_end, y_end, id_player, id_game) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            try (PreparedStatement prepSt = conn.prepareStatement(insertShip)) {
                prepSt.setInt(1, idOnGame);
                prepSt.setInt(2, beginX);
                prepSt.setInt(3, beginY);
                prepSt.setInt(4, endX);
                prepSt.setInt(5, endY);
                prepSt.setInt(6, playerId);
                prepSt.setInt(7, gameId);
                prepSt.executeUpdate();
            }
        } finally {
            DbUtils.INSTANCE.closeConnection(conn);
        }
    }
    
    /**
     * Saves a game's move and if it hit a ship in the data base
     * 
     * @param x             move's position
     * @param y             move's position
     * @param hit           boolean that indicates if it was a ship hit
     * @param playerId      user's DB identifier who played the move
     * @param gameId        game's DB identifier 
     * @throws SQLException problems interacting with the DB
     */
    public static void setMovePosition(int x, int y, boolean hit, int playerId, int gameId) throws SQLException {
        Connection conn = DbUtils.INSTANCE.openConnection();
        String insertMove = "INSERT INTO moves (x, y, hit, id_player, id_game) VALUES (?, ?, ?, ?, ?)";
        
        try {
            try (PreparedStatement prepSt = conn.prepareStatement(insertMove)) {
                prepSt.setInt(1, x);
                prepSt.setInt(2, y);
                prepSt.setBoolean(3, hit);
                prepSt.setInt(4, playerId);
                prepSt.setInt(5, gameId);
                prepSt.executeUpdate();
            }
        } finally {
            DbUtils.INSTANCE.closeConnection(conn);
        }
    }
    
    /**
     * Create a new game and save their players
     * 
     * @param player1Id     user's DB identifier of player one
     * @param player2Id     user's DB identifier of player two
     * @return              game's DB identifider
     * @throws SQLException problems interacting with the DB
     */
    public static int createGame(int player1Id, int player2Id) throws SQLException {
        Connection conn = DbUtils.INSTANCE.openConnection();
        String insertGame = "INSERT INTO games (id_player_1, id_player_2) VALUES (?, ?)";
        String getIdGame = "SELECT id FROM games WHERE id >= ALL (SELECT id FROM games)";
        
        try {
            try (PreparedStatement prepSt = conn.prepareStatement(insertGame)) {
                prepSt.setInt(1, player1Id);
                prepSt.setInt(2, player2Id);
                prepSt.executeUpdate();
            }
            
            try (PreparedStatement prepSt = conn.prepareStatement(getIdGame)) {
                try (ResultSet rs = prepSt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("id");
                    } else {
                        return 0;
                    }
                }
            }
        } finally {
            DbUtils.INSTANCE.closeConnection(conn);
        }
    }
    
    /**
     * Update a game with it winner
     * 
     * @param winnerId      user's DB identifier of game's winner
     * @param gameId        game's DB identifider
     * @throws SQLException problems interacting with the DB
     */
    public static void setGameWinner(int winnerId, int gameId) throws SQLException {
        Connection conn = DbUtils.INSTANCE.openConnection();
        String insertWinner = "UPDATE games SET id_winner = ? WHERE id = ?";
        
        try {
            try (PreparedStatement prepSt = conn.prepareStatement(insertWinner)) {
                prepSt.setInt(1, winnerId);
                prepSt.setInt(2, gameId);
                prepSt.executeUpdate();
            }
        } finally {
            DbUtils.INSTANCE.closeConnection(conn);
        }
    }
    
    /**
     * Get the last game's identifier
     * <p>
     * This method was create for help on debuging
     * 
     * @return last game's DB identifider
     * @throws SQLException problems interacting with the DB
     */
    public static int getLastGameId() throws SQLException {
        Connection conn = DbUtils.INSTANCE.openConnection();
        String getIdGame = "SELECT id FROM games WHERE id >= ALL (SELECT id FROM games)";
        
        try {
            try (PreparedStatement prepSt = conn.prepareStatement(getIdGame)) {
                try (ResultSet rs = prepSt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("id");
                    } else {
                        return 0;
                    }
                }
            }
        } finally {
            DbUtils.INSTANCE.closeConnection(conn);
        }
    }
    
    /**
     * Get all ship's positions in a game
     * <p>
     * This method returns ships order by player (lowest first) and ship's id on game.
     * 
     * @param gameId        game's DB identifier
     * @return              list of classes ShipType, a struct with ship's information
     * @throws SQLException problems interacting with the DB
     */
    public static ArrayList<ShipType> getShipsPositionByGameId(int gameId) throws SQLException {
        Connection conn = DbUtils.INSTANCE.openConnection();
        String getShipsPosition = "SELECT id_on_game, x_begin, y_begin, x_end, y_end, id_player FROM ships WHERE id_game = ? ORDER BY id_player ASC, id_on_game ASC";
        
        try (PreparedStatement prepSt = conn.prepareStatement(getShipsPosition)) {
            prepSt.setInt(1, gameId);
            
            try (ResultSet rs = prepSt.executeQuery()) {
                ArrayList<ShipType> ShipsList = new ArrayList<>();
                while (rs.next()) {
                    ShipsList.add(new ShipType(rs.getInt("id_on_game"), rs.getInt("x_begin"), rs.getInt("y_begin"), rs.getInt("x_end"), rs.getInt("y_end"), rs.getInt("id_player"), gameId));
                }
                
                return ShipsList;
            }
        } finally {
            DbUtils.INSTANCE.closeConnection(conn);
        }
    }
    
    /**
     * Get all game's moves
     * <p>
     * This method returns moves order by time, from first move to last one.
     * 
     * @param gameId        game's DB identifier
     * @return              list of classes MoveType, a struct with game's moves information
     * @throws SQLException problems interacting with the DB
     */
    public static ArrayList<MoveType> getMovesByGameId(int gameId) throws SQLException {
        Connection conn = DbUtils.INSTANCE.openConnection();
        String getMoves = "SELECT x, y, hit, id_player FROM moves WHERE id_game = ? ORDER BY move_date ASC";
        
        try (PreparedStatement prepSt = conn.prepareStatement(getMoves)) {
            prepSt.setInt(1, gameId);
            
            try (ResultSet rs = prepSt.executeQuery()) {
                ArrayList<MoveType> MovesList = new ArrayList<>();
                while (rs.next()) {
                    MovesList.add(new MoveType(rs.getInt("x"), rs.getInt("y"), rs.getBoolean("hit"), rs.getInt("id_player"), gameId));
                }
                
                return MovesList;
            }
        } finally {
            DbUtils.INSTANCE.closeConnection(conn);
        }
    }
    
    /**
     * Get all player's games
     * 
     * @param playerId      user's DB identifier
     * @return              list of classes GameType, a struct with player's games information
     * @throws SQLException problems interacting with the DB
     */
    public static ArrayList<GameType> getGamesByPlayerId(int playerId) throws SQLException {
        Connection conn = DbUtils.INSTANCE.openConnection();
        String getGames = "SELECT id, id_player_1, id_player_2, id_winner FROM games WHERE id_player_1 = ? OR id_player_2 = ? ORDER BY date DESC";
        
        try (PreparedStatement prepSt = conn.prepareStatement(getGames)) {
            prepSt.setInt(1, playerId);
            prepSt.setInt(2, playerId);
            
            try (ResultSet rs = prepSt.executeQuery()) {
                ArrayList<GameType> GamesList = new ArrayList<>();
                while (rs.next()) {
                    GamesList.add(new GameType(rs.getInt("id"), rs.getInt("id_player_1"), rs.getInt("id_player_2"), rs.getInt("id_winner")));
                }
                
                return GamesList;
            }
        } finally {
            DbUtils.INSTANCE.closeConnection(conn);
        }
    }
}
