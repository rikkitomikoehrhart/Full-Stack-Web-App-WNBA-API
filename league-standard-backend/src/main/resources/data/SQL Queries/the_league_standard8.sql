CREATE TABLE game_boxscores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    game_id VARCHAR(100) NOT NULL,
    home_scoring JSON,
    away_scoring JSON,
    home_top_players JSON,
    away_top_players JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
    UNIQUE KEY unique_game_boxscore (game_id)
);