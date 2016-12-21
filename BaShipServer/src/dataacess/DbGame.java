/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacess;

import java.sql.*;

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
     * @param pos           ship's position
     * @param playerId      user's DB identifier who place the ship
     * @param gameId        game's DB identifier
     * @throws SQLException problems interacting with the DB
     */
    public static void setShipPosition(int idOnGame, int[][] pos, int playerId, int gameId) throws SQLException {
        Connection conn = DbUtils.openConnection();
        String insertShip = "INSERT INTO ships (id_on_game, pos, id_player, id_game) VALUES (?, '{{?, ?},{?, ?}}', ?, ?)";
        
        try {
            try (PreparedStatement prepSt = conn.prepareStatement(insertShip)) {
                prepSt.setInt(1, idOnGame);
                prepSt.setInt(3, pos[0][0]);
                prepSt.setInt(4, pos[0][1]);
                prepSt.setInt(5, pos[1][0]);
                prepSt.setInt(6, pos[1][1]);
                prepSt.setInt(7, playerId);
                prepSt.setInt(8, gameId);
                prepSt.executeUpdate();
            }
        } finally {
            DbUtils.closeConnection(conn);
        }
    }
    
    /**
     * Saves a game's move and if it hit a ship in the data base
     * 
     * @param pos           move's position
     * @param hit           boolean that indicates if it was a ship hit
     * @param playerId      user's DB identifier who played the move
     * @param gameId        game's DB identifier 
     * @throws SQLException problems interacting with the DB
     */
    public static void setMovePosition(int[] pos, boolean hit, int playerId, int gameId) throws SQLException {
        Connection conn = DbUtils.openConnection();
        String insertMove = "INSERT INTO moves (pos, hit, id_player, id_game) VALUES ('{?, ?}', ?, ?, ?)";
        
        try {
            try (PreparedStatement prepSt = conn.prepareStatement(insertMove)) {
                prepSt.setInt(1, pos[0]);
                prepSt.setInt(2, pos[1]);
                prepSt.setBoolean(3, hit);
                prepSt.setInt(4, playerId);
                prepSt.setInt(5, gameId);
                prepSt.executeUpdate();
            }
        } finally {
            DbUtils.closeConnection(conn);
        }
    }
    
    /**
     * Create a new game and save their players
     * 
     * @param player1Id     user's DB identifier of played one
     * @param player2Id     user's DB identifier of played two
     * @return              game's DB identifider
     * @throws SQLException problems interacting with the DB
     */
    public static int setGame(int player1Id, int player2Id) throws SQLException {
        Connection conn = DbUtils.openConnection();
        String insertGame = "INSERT INT games (id_player_1, id_player_2) VALUES (?, ?)";
        String getIdGame = "SELECT id FROM games WHERE id >= ALL (SELECT id FROM games)";
        
        try {
            try (PreparedStatement prepSt = conn.prepareStatement(insertGame)) {
                prepSt.setInt(1, player1Id);
                prepSt.setInt(2, player2Id);
                prepSt.executeUpdate();
            }
            
            try (PreparedStatement prepSt = conn.prepareStatement(getIdGame)) {
                try (ResultSet rs = prepSt.executeQuery()) {
                    return rs.getInt("id");
                }
            }
        } finally {
            DbUtils.closeConnection(conn);
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
        Connection conn = DbUtils.openConnection();
        String insertWinner = "UPDATE games id_winner = ? WHERE id = ?";
        
        try {
            try (PreparedStatement prepSt = conn.prepareStatement(insertWinner)) {
                prepSt.setInt(1, winnerId);
                prepSt.setInt(2, gameId);
                prepSt.executeUpdate();
            }
        } finally {
            DbUtils.closeConnection(conn);
        }
    }
    
    public static int getShipsPosition() throws SQLException {
        Connection conn = DbUtils.openConnection();
        
        return 0;
    }
    
    public static int getMovesPosition() throws SQLException {
        Connection conn = DbUtils.openConnection();
        
        return 0;
    }
    
    public static int getGame() throws SQLException {
        Connection conn = DbUtils.openConnection();
        
        return 0;
    }
}
