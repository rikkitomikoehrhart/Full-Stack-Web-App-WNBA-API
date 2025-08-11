SELECT 
    f.id as user_favorite_teams_id,
    t.id as team_id,
    t.alias,
    t.market,
    t.name,
    t.year_founded,
    t.mascot,
    t.owner
FROM user_favorite_teams as f 
JOIN teams t ON f.team_id = t.id