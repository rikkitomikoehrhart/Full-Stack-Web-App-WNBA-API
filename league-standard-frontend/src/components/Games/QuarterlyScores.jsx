import TeamLogo from "../UI/TeamLogo";


function QuarterlyScores({ game, scores }) {
    const home_scoring = scores.home_scoring;
    const away_scoring = scores.away_scoring;

    return (
        <>
            <div className="container text-center mt-5 ps-0 ms-0">
                <table className="table">
                    <thead>
                        <tr>
                            <th className="ps-0 pe-0"><h4>Quarters -</h4></th>
                            <th><h4>1</h4></th>
                            <th><h4>2</h4></th>
                            <th><h4>3</h4></th>
                            <th><h4>4</h4></th>
                        </tr>
                        
                    </thead>
                    <tbody>
                        <tr>
                            <td className="ps-0 pe-0"><strong className="text-muted">HOME</strong> <TeamLogo team={game.homeTeam} size="favorite" className="" showLink={true} /></td>
                            <td>{home_scoring.q1Points}</td>
                            <td>{home_scoring.q2Points}</td>
                            <td>{home_scoring.q3Points}</td>
                            <td>{home_scoring.q4Points}</td>
                        </tr>
                        <tr>
                            <td className="ps-0 pe-0"><strong className="text-muted">AWAY</strong> <TeamLogo team={game.awayTeam} size="favorite" className="" showLink={true} /></td>
                            <td>{away_scoring.q1Points}</td>
                            <td>{away_scoring.q2Points}</td>
                            <td>{away_scoring.q3Points}</td>
                            <td>{away_scoring.q4Points}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </>
    );
}

export default QuarterlyScores;