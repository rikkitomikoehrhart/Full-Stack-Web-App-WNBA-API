import { useState, useEffect } from 'react';
import Loading from '../UI/Loading';

function TeamsList() {
    const [teams, setTeams] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetch("http://localhost:8080/api/teams")
            .then(res => res.json())
            .then(data => {
                setTeams(data)
                setLoading(false)
            })
            .catch(err => console.error('Error: ', err))
    }, [])

    if (loading) {
        return (
            <Loading />
        )
    }

    return (
        <>

        </>
    );
}

export default TeamsList;