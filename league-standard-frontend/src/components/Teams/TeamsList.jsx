import { useState, useEffect } from 'react';
import Loading from '../UI/Loading';

function TeamsList() {
    const [teams, setTeams] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        fetch("http://localhost:8080/api/teams")
            .then(res => res.json())
            .then(data => {
                setTeams(data)
                setIsLoading(false)
            })
            .catch(err => console.error('Error: ', err))
    }, [])

    if (isLoading) {
        return (
            <Loading />
        )
    }

    return (
        <>
            <div>
                <h1>Teams</h1>
                <ul className="list-group list-group-flush">
                    {teams.map(team => (
                        <li key={team.id} className='list-group-item'>
                            <p>{team.market} {team.name}</p>
                        </li>
                    ))}
                </ul>

            </div>
        </>
    );
}

export default TeamsList;