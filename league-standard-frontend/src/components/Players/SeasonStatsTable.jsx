

function SeasonStatsTable({ stats }) {


    return (
        <>
            <h3 className="mt-5">2025 Season Stats</h3>
            <div className="container mt-4">
                <table className="table">
                    <thead>
                    </thead>
                    <tbody>
                        <tr>
                            <th>GAMES PLAYED:</th>
                            <td>{stats.games_played}</td>
                            <th>GAMES STARTED:</th>
                            <td>{stats.games_started}</td>
                        </tr>
                        <tr>
                            <th>MINUTES:</th>
                            <td>{stats.minutes}</td>
                            <th>POINTS (avg):</th>
                            <td>{stats.points} ( {stats.avg_points_per_game} ppg )</td>
                        </tr>
                        <tr>
                            <th>FIELD GOALS MADE:</th>
                            <td>{stats.field_goals_made}</td>
                            <th>FIELD GOALS PERCENTAGE:</th>
                            <td>{stats.field_goals_pct} %</td>
                        </tr>
                        <tr>
                            <th>2-POINT MADE:</th>
                            <td>{stats.two_points_made}</td>
                            <th>2-POINT PERCENTAGE:</th>
                            <td>{stats.two_points_pct} %</td>
                        </tr>
                        <tr>
                            <th>3-POINT MADE:</th>
                            <td>{stats.three_points_made}</td>
                            <th>3-POINT PERCENTAGE:</th>
                            <td>{stats.three_points_pct} %</td>
                        </tr>
                        <tr>
                            <th>FREE THROWS MADE:</th>
                            <td>{stats.free_throws_made}</td>
                            <th>FREE THROWS PERCENTAGE:</th>
                            <td>{stats.free_throws_pct}</td>
                        </tr>
                        <tr>
                            <th>OFFENSIVE REBOUNDS:</th>
                            <td>{stats.offensive_rebounds}</td>
                            <th>DEFENSIVE REBOUNDS:</th>
                            <td>{stats.defensive_rebounds}</td>
                        </tr>
                        <tr>
                            <th>ASSISTS:</th>
                            <td>{stats.assists}</td>
                            <th>TURNOVERS:</th>
                            <td>{stats.turnovers}</td>
                        </tr>
                        <tr>
                            <th>STEALS:</th>
                            <td>{stats.steals}</td>
                            <th>BLOCKS:</th>
                            <td>{stats.blocks}</td>
                        </tr>
                        <tr>
                            <th>PERSONAL FOULS:</th>
                            <td>{stats.personal_fouls}</td>
                            <th>TECHNICAL FOULS:</th>
                            <td>{stats.tech_fouls}</td>
                        </tr>
                        <tr>
                            <th>FLAGRANT FOULS:</th>
                            <td>{stats.flagrant_fouls}</td>
                            <th>FOULS DRAWN:</th>
                            <td>{stats.fouls_drawn}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </>
    );
}

export default SeasonStatsTable;