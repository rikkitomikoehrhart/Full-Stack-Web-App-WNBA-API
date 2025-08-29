

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
                            <td>{stats.games_played || "ERR"}</td>
                            <th>GAMES STARTED:</th>
                            <td>{stats.games_started || "ERR"}</td>
                        </tr>
                        <tr>
                            <th>MINUTES:</th>
                            <td>{stats.minutes || "ERR"}</td>
                            <th>POINTS (avg):</th>
                            <td>{stats.points  || "ERR"} ( {stats.avg_points_per_game  || "ERR"} ppg )</td>
                        </tr>
                        <tr>
                            <th>FIELD GOALS MADE:</th>
                            <td>{stats.field_goals_made || "ERR"}</td>
                            <th>FIELD GOALS PERCENTAGE:</th>
                            <td>{stats.field_goals_pct || "ERR"} %</td>
                        </tr>
                        <tr>
                            <th>2-POINT MADE:</th>
                            <td>{stats.two_points_made || "ERR"}</td>
                            <th>2-POINT PERCENTAGE:</th>
                            <td>{stats.two_points_pct || "ERR"} %</td>
                        </tr>
                        <tr>
                            <th>3-POINT MADE:</th>
                            <td>{stats.three_points_made || "ERR"}</td>
                            <th>3-POINT PERCENTAGE:</th>
                            <td>{stats.three_points_pct || "ERR"} %</td>
                        </tr>
                        <tr>
                            <th>FREE THROWS MADE:</th>
                            <td>{stats.free_throws_made || "ERR"}</td>
                            <th>FREE THROWS PERCENTAGE:</th>
                            <td>{stats.free_throws_pct || "ERR"}</td>
                        </tr>
                        <tr>
                            <th>OFFENSIVE REBOUNDS:</th>
                            <td>{stats.offensive_rebounds || "ERR"}</td>
                            <th>DEFENSIVE REBOUNDS:</th>
                            <td>{stats.defensive_rebounds || "ERR"}</td>
                        </tr>
                        <tr>
                            <th>ASSISTS:</th>
                            <td>{stats.assists || "ERR"}</td>
                            <th>TURNOVERS:</th>
                            <td>{stats.turnovers || "ERR"}</td>
                        </tr>
                        <tr>
                            <th>STEALS:</th>
                            <td>{stats.steals || "ERR"}</td>
                            <th>BLOCKS:</th>
                            <td>{stats.blocks || "ERR"}</td>
                        </tr>
                        <tr>
                            <th>PERSONAL FOULS:</th>
                            <td>{stats.personal_fouls || "ERR"}</td>
                            <th>TECHNICAL FOULS:</th>
                            <td>{stats.tech_fouls || "ERR"}</td>
                        </tr>
                        <tr>
                            <th>FLAGRANT FOULS:</th>
                            <td>{stats.flagrant_fouls || "ERR"}</td>
                            <th>FOULS DRAWN:</th>
                            <td>{stats.fouls_drawn || "ERR"}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </>
    );
}

export default SeasonStatsTable;