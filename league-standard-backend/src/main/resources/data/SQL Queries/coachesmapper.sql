SELECT 
    c.id as coach_id,
    c.first_name,
    c.last_name, 
    c.position,
    t.id as team_id,
    t.alias,
    t.market,
    t.name,
    t.year_founded,
    t.mascot,
    t.owner
FROM coaches c 
JOIN teams t ON c.team_id = t.id