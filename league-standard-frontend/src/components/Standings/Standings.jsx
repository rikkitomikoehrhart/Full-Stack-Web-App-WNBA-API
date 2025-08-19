import { useState, useEffect } from 'react';
import Loading from '../UI/Loading';

function Standings() {
    const [teamStanding, setTeamStanding] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

useEffect(() => {
        fetch("http://localhost:8080/api/standings")
            .then(res => res.json())
            .then(data => {
                const sortedData = data.sort((a, b) => a.league_rank - b.league_rank);
                setTeamStanding(sortedData);
                setIsLoading(false);
            })
            .catch(err => {
                console.error("Error: ", err);
                setIsLoading(false);
            });
    }, []);


    if (isLoading) {
        return (
            <Loading />
        )
    }


    return (
        <>
            <div className='mt-4 table-responsive'>
                <table className='table table-hover table-striped'>
                    <thead className='table-light'>
                        <tr>
                            <td className='table-heading'>RANK</td>
                            <td className='table-heading'></td>
                            <td className='table-heading team-heading'>TEAM</td>
                            <td className='table-heading'>WINS</td>
                            <td className='table-heading'>LOSSES</td>
                            <td className='table-heading'>WIN %</td>
                            <td className='table-heading'>PTS FOR</td>
                            <td className='table-heading'>PTS AGST</td>
                            <td className='table-heading'>PTS DIFF</td>
                        </tr>
                    </thead>
                    <tbody className='table-group-divider'>
                        {teamStanding.map(standing => (
                            <tr key={standing.id}>
                                <td>{standing.league_rank}</td>
                                <td><a href={`/teams/${standing.team.id}`}><img className="standing-logo-team" src={`./team-logos/${(standing.team.name).toLowerCase()}.svg`} alt={`${standing.team.market} ${standing.team.name} Logo`} /></a></td>
                                <td><a href={`/teams/${standing.team.id}`}>{standing.team.market} {standing.team.name}</a></td>
                                <td>{standing.wins}</td>
                                <td>{standing.losses}</td>
                                <td>{((standing.win_pct) * 100).toFixed(1)} %</td>
                                <td>{standing.points_for}</td>
                                <td>{standing.points_against}</td>
                                <td>{standing.point_diff}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </>
    );
}

export default Standings;