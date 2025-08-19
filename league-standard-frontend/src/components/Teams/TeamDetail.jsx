import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import Loading from "../UI/Loading";
import { useTeamColors } from '../Context/TeamColorsContext.jsx';

function TeamDetail() {
    const { id } = useParams();
    const [team, setTeam] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const { teamColors } = useTeamColors();

    useEffect(() => {
        const fetchTeam = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/teams/${id}`);
                const teamData = await response.json();

                setTeam(teamData);
            } catch (error) {
                console.error('Error fetching team: ', error)
            } finally {
                setIsLoading(false)
            }
        };

        fetchTeam();
    }, [id]);

    if (isLoading) {
        return (
            <Loading />
        )
    }

    if (!team) {
        return (
            <>
                <div className="text-center">
                    <h2>Team not found</h2>
                </div>
            </>
        )
    }



    return (
        <>
        {console.log(team)}
            <h2>{team.market} {team.name}</h2>
            <p className="text-muted">Founded: {team.yearFounded}</p>

        </>
    );
}

export default TeamDetail;