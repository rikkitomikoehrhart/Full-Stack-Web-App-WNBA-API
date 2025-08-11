SELECT 
    c.id as team_colors_id,
    c.color_type,
    c.hex_code, 
    t.id as team_id,
    t.alias,
    t.market,
    t.name,
    t.year_founded,
    t.mascot,
    t.owner
FROM team_colors c 
JOIN teams t ON c.team_id = t.id