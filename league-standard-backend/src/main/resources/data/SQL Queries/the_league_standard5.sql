CREATE TABLE user_favorite_players (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    player_id VARCHAR(100) NOT NULL, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE,
    UNIQUE KEY unique_favorite_player (player_id)
);