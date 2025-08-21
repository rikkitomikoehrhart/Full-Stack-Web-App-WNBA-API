import { useParams } from "react-router-dom";
import { usePlayer } from "../../hooks/usePlayers.js";
import { generatePlayerHeadshotPath, getFormattedDate } from "../Utilities/playerUtils.jsx";
import { useTeamColors } from '../Context/TeamColorsContext.jsx';



function PlayerDetail() {
    const { id } = useParams();
    const { data: player, error } = usePlayer(id);
    const { teamColors } = useTeamColors();

    if (error) {
        return (
            <div className="text-center mt-5">
                <h3>Error loading player</h3>
                <p className="text-muted">{error.message}</p>
            </div>
        );
    }



    if (!player) {
        return (
            <>
                <div className="text-center">
                    <h2>Player not found</h2>
                </div>
            </>
        )
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

                        </table>

                    </div>
                </div>
            </div>
            
        </>
    );
}

function getFormattedHeight(height) {
    const feet = Math.floor(height/12);
    const inches = height%12;

    return feet + `" ` + inches + `'`;
}

function getFormattedAge(birthdateString) {
    const birthdate = new Date(birthdateString);
    const today = new Date();

    let age = today.getFullYear() - birthdate.getFullYear();
    const monthDifference = today.getMonth() - birthdate.getMonth();
    const dayDifference = today.getDate() - birthdate.getDate();

    if (monthDifference < 0 || (monthDifference === 0 && dayDifference <0)) {
        age--;
    }

    return age;
}

function getZodiacSign(birthdateString) {
    const birthdate = new Date(birthdateString);

    if (birthdate.getMonth() == 0) {
        if (birthdate.getDate() <= 19) {
            return "Capricorn";
        } else {
            return "Aquarius";
        }
    } else if (birthdate.getMonth() == 1) {
        if (birthdate.getDate() <= 18) {
            return "Aquarius";
        } else {
            return "Pisces";
        }
    } else if (birthdate.getMonth() == 2) {
        if (birthdate.getDate() <= 20) {
            return "Pisces";
        } else {
            return "Aries";
        }
    } else if (birthdate.getMonth() == 3) {
        if (birthdate.getDate() <= 19) {
            return "Aries";
        } else {
            return "Taurus";
        }
    } else if (birthdate.getMonth() == 4) {
        if (birthdate.getDate() <= 20) {
            return "Taurus";
        } else {
            return "Gemini";
        }
    } else if (birthdate.getMonth() == 5) {
        if (birthdate.getDate() <= 21) {
            return "Gemini";
        } else {
            return "Cancer";
        }
    } else if (birthdate.getMonth() == 6) {
        if (birthdate.getDate() <= 22) {
            return "Cancer";
        } else {
            return "Leo";
        }
    } else if (birthdate.getMonth() == 7) {
        if (birthdate.getDate() <= 22) {
            return "Leo";
        } else {
            return "Virgo";
        }
    } else if (birthdate.getMonth() == 8) {
        if (birthdate.getDate() <= 22) {
            return "Virgo";
        } else {
            return "Libra";
        }
    } else if (birthdate.getMonth() == 9) {
        if (birthdate.getDate() <= 23) {
            return "Libra";
        } else {
            return "Scorpio";
        }
    } else if (birthdate.getMonth() == 10) {
        if (birthdate.getDate() <= 21) {
            return "Scorpio";
        } else {
            return "Sagittarius";
        }
    } else if (birthdate.getMonth() == 11) {
        if (birthdate.getDate() <= 21) {
            return "Sagittarius";
        } else {
            return "Capricorn";
        }
    }
}


export default PlayerDetail;