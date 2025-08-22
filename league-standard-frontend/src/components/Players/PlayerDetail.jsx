import { useParams } from "react-router-dom";
import { usePlayer } from "../../hooks/usePlayers.js";
import { generatePlayerHeadshotPath } from "../../utils/playerUtils.js";
import { getFormattedDate, getFormattedAge } from "../../utils/dateUtils.js";
import { getFormattedHeight } from "../../utils/formatUtils.js";
import { getZodiacSign } from "../../utils/zodiacUtils.js";
import { useTeamColors } from "../../hooks/useTeamColors.js";
import ErrorMessage from '../UI/ErrorMessage';
import Loading from '../UI/Loading';


function PlayerDetail() {
    const { id } = useParams();
    const { data: player, error } = usePlayer(id);
    const { data: teamColors, isLoading, colorsError } = useTeamColors();

    if (isLoading) {
        return <Loading />
    }


    if (error) {
        return <ErrorMessage message={error.message} title="Error Loading Player..." />
    }

    if (colorsError) {
        return <ErrorMessage message={colorsError.message} title="Error Loading Team Colors..."/>
    }

    if (!player) {
        return <ErrorMessage message="Player not found." title="Error..." />
    }

    return (
        <>
            <h2>{player.first_name} {player.last_name}</h2>
            <p className="text-muted">#{player.jersey_number} on the <a href={`/teams/${player.team.id}`}>{player.team.market} {player.team.name}</a>, Position: {player.position}</p>

            <div className="container mt-4">
                <div className="row">
                    <div className="col-md-6 col-sm-12">
                        <div style={{ overflow: "hidden",
                            backgroundImage: `linear-gradient(0deg, ${(teamColors.find((color) => color.teamID == player.team.id && color.colorType == "primary")?.hexCode || "#ccc")} 15%, #ffffff 25%`}} 
                            className="shadow-sm">
                            <img src={generatePlayerHeadshotPath(player)} className='player-detail-picture mt-5 pr-4 pl-4' alt={`${player.first_name} ${player.last_name}`}/>
                        </div>
                    </div>
                    <div className="col-md-6 col-sm-12">
                        <table className="table table-borderless align-middle stats-table table-responsive">
                            <tbody>
                                <tr>
                                    <th>HEIGHT</th>
                                    <th>EXPERIENCE</th>
                                </tr>
                                <tr>
                                    <td>{getFormattedHeight(player.height)}</td>
                                    <td>{player.experience} years</td>
                                </tr>
                                <tr>
                                    <th>COLLEGE</th>
                                    <th>DRAFT</th>
                                </tr>
                                <tr>
                                    <td>{player.college}</td>
                                    <td>{player.rookie_year} - Round {player.draft_round}, Pick {player.draft_pick}</td>
                                </tr>
                                <tr>
                                    <th>AGE</th>
                                    <th>BIRTH PLACE</th>
                                </tr>
                                <tr>
                                    <td>{getFormattedAge(player.birthdate)} years old</td>
                                    <td>{player.birth_place}</td>
                                </tr>
                                <tr>
                                    <th>BIRTHDAY</th>
                                    <th>SIGN</th>
                                </tr>
                                <tr>
                                    <td>{getFormattedDate(player.birthdate)}</td>
                                    <td>{getZodiacSign(player.birthdate)}</td>
                                </tr>

                            </tbody>
                            
                        </table>

                    </div>
                </div>
            </div>
            
        </>
    );
}

export default PlayerDetail;