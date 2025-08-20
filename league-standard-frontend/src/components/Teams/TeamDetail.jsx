import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import Loading from "../UI/Loading";
import { useTeams } from '../Context/TeamsContext.jsx';
import { useTeamColors } from '../Context/TeamColorsContext.jsx';
import ListElement from "../Players/ListElement.jsx";
import GameElement from "../Games/GameElement.jsx";

function TeamDetail() {
    const { id } = useParams();
    const { teams } = useTeams();
    const team = teams.find(t => t.id === id);
    const [players, setPlayers] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const { teamColors } = useTeamColors();
    const [game, setGame] = useState(null);

    useEffect(() => {
        const fetchTeam = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/players/team/${id}`);
                const playersData = await response.json();

                setPlayers(playersData);
            } catch (error) {
                console.error('Error fetching players: ', error)
            }

            try {
                const response = await fetch(`http://localhost:8080/api/games/next/${id}`);
                const gameData = await response.json();

                setGame(gameData);
            } catch (error) {
                console.error('Error fetching gameData: ', error)
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
            <h2>{team.market} {team.name}</h2>
            <p className="text-muted">Founded: {team.yearFounded}</p>

            <div className="container mt-4">
                <div className="row">
                    <div className="col-md-6 col-sm-12">
                        <div style={{ overflow: "hidden", borderRadius: "5px",
                            backgroundImage: `linear-gradient(0deg, ${(teamColors.find((color) => color.teamID == team.id && color.colorType == "secondary")?.hexCode || "#ccc")} 15%, #ffffff 25%`}} 
                            className="shadow-sm">
                            <img src={`/team-logos/${(team.name).toLowerCase()}.svg`} className='large-logo mt-2 pr-4 pl-4' alt={`${team.market} ${team.name} Logo`}/>
                        </div>
                    </div>
                    <div className="col-md-6 col-sm-12">
                        <table className="table table-borderless align-middle stats-table table-responsive">
                            <tr>
                                <th>MARKET</th>
                                <th>NAME</th>
                            </tr>
                            <tr>
                                <td>{team.market}<br /> ( {team.alias} )</td>
                                <td>{team.name}</td>
                            </tr>
                            <tr>
                                <th>MASCOT</th>
                                <th>OWNER</th>
                            </tr>
                            <tr>
                                <td>{team.mascot}</td>
                                <td>{team.owner}</td>
                            </tr>
                        </table>
                    </div>

                </div>
                <div className="row mt-4">
                    <h3>Next Game</h3>
                    <GameElement game={game} />
                </div>

                <div className="row g-3 mt-4">
                    <h3>Players</h3>
                    <ListElement players={players} displayHeaders={false} />
                </div>
            </div>
        </>
    );
}

export default TeamDetail;