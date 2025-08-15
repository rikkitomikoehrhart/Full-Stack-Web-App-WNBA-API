SELECT s.id, s.wins, s.losses, s.win_pct, s.points_for, s.points_against, s.point_diff, s.league_rank,
	   t.id as team_id, t.alias, t.market, t.name, t.year_founded, t.mascot, t.owner
FROM standings s
JOIN teams t ON s.team_id = t.id;