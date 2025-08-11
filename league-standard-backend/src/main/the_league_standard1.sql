-- Create the database
CREATE DATABASE IF NOT EXISTS `the_league_standard`;
USE `the_league_standard`;

-- Table 1: Teams
CREATE TABLE teams (
    id VARCHAR(100) PRIMARY KEY,
    alias VARCHAR(10) NOT NULL,
    market VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    year_founded INT,
    mascot VARCHAR(50),
    owner VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    INDEX idx_alias (alias)
);

-- Table 2: Coaches  
CREATE TABLE coaches (
    id VARCHAR(100) PRIMARY KEY,
    team_id VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    position VARCHAR(30) NOT NULL, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE,
    INDEX idx_team_id (team_id)
);

-- Table 3: Team Colors
CREATE TABLE team_colors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    team_id VARCHAR(100) NOT NULL,
    color_type ENUM('primary', 'secondary', 'accent') NOT NULL,
    hex_code VARCHAR(7) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE,
    UNIQUE KEY unique_team_color (team_id, color_type),
    INDEX idx_team_id (team_id)
);

-- Table 4: Games
CREATE TABLE games (
    id VARCHAR(100) PRIMARY KEY, 
    home_team_id VARCHAR(100) NOT NULL,
    away_team_id VARCHAR(100) NOT NULL,
    scheduled_date DATETIME NOT NULL,
    status ENUM('scheduled', 'created', 'inprogress', 'halftime', 'complete', 'closed', 'cancelled', 'delayed', 'postponed', 'time-tbd', 'if-necessary', 'unnecessary') NOT NULL DEFAULT 'scheduled',
    home_score INT NULL, 
    away_score INT NULL, 
    season_year INT NOT NULL, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (home_team_id) REFERENCES teams(id),
    FOREIGN KEY (away_team_id) REFERENCES teams(id),
    
    INDEX idx_scheduled_date (scheduled_date),
    INDEX idx_home_team (home_team_id),
    INDEX idx_away_team (away_team_id),
    INDEX idx_status (status),
    INDEX idx_season_year (season_year)
);

-- Table 5: User Favorites 
CREATE TABLE user_favorite_teams (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    team_id VARCHAR(100) NOT NULL, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE,
    UNIQUE KEY unique_favorite_team (team_id)
);