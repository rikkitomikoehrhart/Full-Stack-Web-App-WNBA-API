SELECT p.id, p.status, p.first_name, p.last_name, p.height, p.position, p.jersey_number, p.experience, p.college, p.birth_place, p.birthdate, p.rookie_year, p.draft_round, p.draft_pick,
	   t.id as team_id, t.alias, t.market, t.name, t.year_founded, t.mascot, t.owner
FROM players p
JOIN teams t ON p.team_id = t.id;
