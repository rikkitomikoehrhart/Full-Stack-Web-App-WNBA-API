SELECT 
    g.id as game_id,
    g.scheduled_date,
    g.status,
    g.home_score,
    g.away_score,
    g.season_year,
    
    ht.id as home_team_id,
    ht.alias as home_team_alias,
    ht.market as home_team_market,
    ht.name as home_team_name,
    ht.year_founded as home_team_year_founded,
    ht.mascot as home_team_mascot,
    ht.owner as home_team_owner,
    
    at.id as away_team_id,
    at.alias as away_team_alias,
    at.market as away_team_market,
    at.name as away_team_name,
    at.year_founded as away_team_year_founded,
    at.mascot as away_team_mascot,
    at.owner as away_team_owner
    
FROM games g
JOIN teams ht ON g.home_team_id = ht.id 
JOIN teams at ON g.away_team_id = at.id