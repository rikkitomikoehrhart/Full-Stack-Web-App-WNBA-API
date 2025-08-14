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
            <div className='mt-4 container text-center'>
                <div className='row'>
                    {teams.map(team => (
                        <div key={team.id} className='col-lg-4 col-md-6 col-sm-12 mb-3'>
                            <p>{team.market} {team.name}</p>
                        </div>
                    ))}          
                </div>
            </div>
        </>
    );
}

export default TeamsList;