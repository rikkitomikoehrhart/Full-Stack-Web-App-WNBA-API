CREATE TABLE players (
    id VARCHAR(36) PRIMARY KEY,
    team_id VARCHAR(36),
    status VARCHAR(4),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    height VARCHAR(10),
    position VARCHAR(5),
    jersey_number INT,
    experience INT,
    college VARCHAR(100),
    birth_place VARCHAR(100),
    birthdate DATE,
    rookie_year INT,
    draft_round INT,
    draft_pick INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_players_team 
        FOREIGN KEY (team_id) 
        REFERENCES teams(id) 
        ON DELETE SET NULL 
        ON UPDATE CASCADE
);