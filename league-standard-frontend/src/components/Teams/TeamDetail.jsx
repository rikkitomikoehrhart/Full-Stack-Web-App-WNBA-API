import { useParams } from "react-router-dom";
import { useTeamByID } from "../../hooks/useTeams.js";
import { useTeamColors } from "../../hooks/useTeamColors.js";
import ListElement from "../Players/ListElement.jsx";
import GameElement from "../Games/GameElement.jsx";
import { usePlayersByTeam } from "../../hooks/usePlayers.js";
import { useNextGameByTeam } from "../../hooks/useGames.js";
import ErrorMessage from "../UI/ErrorMessage.jsx";
import Loading from '../UI/Loading';
import { DEFAULT_IMAGE_PATHS } from "../../constants/ui.js";

function TeamDetail() {
    const { id } = useParams();
    const { data: team, error: teamsError } = useTeamByID(id);
    const { data: players, error: playersError } = usePlayersByTeam(id);
    const { data: game, error: gameError } = useNextGameByTeam(id);
    const { data: teamColors, isLoading, colorError } = useTeamColors();


    if (isLoading) {
        return <Loading />
    }


    if (!team) {
        return <ErrorMessage message={"Team not found"} title="Error..."/>
    }

    if (teamsError) {
        return <ErrorMessage message={teamsError.message} title="Error Loading Team..." />
    }

    if (gameError) {
        return <ErrorMessage message={gameError.message} title="Error Loading Next Game..." />
    }

    if (playersError) {
        return <ErrorMessage message={playersError.message} title="Error Loading Players..." />
    }
    
    if (colorError) {
        return <ErrorMessage message={colorError.message} title="Error Loading Team Colors..." />
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
                            <img src={`${DEFAULT_IMAGE_PATHS.TEAM_LOGOS}/${(team.name).toLowerCase()}.svg`} className='large-logo mt-2 pr-4 pl-4' alt={`${team.market} ${team.name} Logo`}/>
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
                    <ListElement players={players || []} displayHeaders={false} />
                </div>
            </div>
        </>
    );
}

export default TeamDetail;