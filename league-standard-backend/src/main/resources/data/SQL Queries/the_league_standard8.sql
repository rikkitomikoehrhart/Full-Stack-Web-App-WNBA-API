CREATE TABLE boxscores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    game_id VARCHAR(100) NOT NULL,
    attendance INT,
    lead_changes INT,
    times_tied INT,
    home_team_id VARCHAR(100) NOT NULL,
    
    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
    FOREIGN KEY (home_team_id) REFERENCES teams(id) ON DELETE RESTRICT
);

CREATE TABLE scoring (
    id INT PRIMARY KEY AUTO_INCREMENT,
    boxscore_id INT NOT NULL,
    team_id VARCHAR(100) NOT NULL,
    quarter INT NOT NULL,
    points INT NOT NULL DEFAULT 0,
    
    FOREIGN KEY (boxscore_id) REFERENCES boxscores(id) ON DELETE CASCADE,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE RESTRICT
);


CREATE TABLE player_stats (
    id INT PRIMARY KEY AUTO_INCREMENT,
    player_id VARCHAR(100) NOT NULL,
    boxscore_id INT NOT NULL,
    minutes VARCHAR(10),
    field_goals_made INT DEFAULT 0,
    field_goals_pct DECIMAL(4,1),
    three_points_made INT DEFAULT 0,
    three_points_pct DECIMAL(4,1),
    two_points_made INT DEFAULT 0,
    two_points_pct DECIMAL(4,1),
    free_throws_made INT DEFAULT 0,
    free_throws_pct DECIMAL(4,1),
    offensive_rebounds INT DEFAULT 0,
    defensive_rebounds INT DEFAULT 0,

    assists INT DEFAULT 0,
    turnovers INT DEFAULT 0,
    steals INT DEFAULT 0,
    blocks INT DEFAULT 0,
    personal_fouls INT DEFAULT 0,
    tech_fouls INT DEFAULT 0,
    flagrant_fouls INT DEFAULT 0,
    points INT DEFAULT 0,
    double_double BOOLEAN DEFAULT FALSE,
    triple_double BOOLEAN DEFAULT FALSE,
    effective_fg_pct DECIMAL(4,1),
    efficiency INT DEFAULT 0,
    efficiency_game_score INT DEFAULT 0,
    fouls_drawn INT DEFAULT 0,
    points_in_paint INT DEFAULT 0,
    points_in_paint_pct DECIMAL(4,1),
    points_off_turnovers INT DEFAULT 0,
    

    FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE RESTRICT,
    FOREIGN KEY (boxscore_id) REFERENCES boxscores(id) ON DELETE CASCADE,
    
    
    UNIQUE KEY unique_player_boxscore (player_id, boxscore_id)
);