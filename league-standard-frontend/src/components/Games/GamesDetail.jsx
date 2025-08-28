import { useParams } from "react-router-dom";
import { useGame } from "../../hooks/useGames.js"
import Loading from "../UI/Loading.jsx";
import ErrorMessage from "../UI/ErrorMessage.jsx";
import GameElement from "./GameElement.jsx";
import { useBoxscoreByGame } from "../../hooks/useBoxscores.js";
import QuarterlyScores from "./QuarterlyScores.jsx";
import PlayerGameStats from "./PlayerGameStats.jsx";

function GamesDetail() {
    const { id } = useParams();
    const { data: game, gameError } = useGame(id);
    const { data: boxscores, isLoading, boxscoresError} = useBoxscoreByGame(id);

    if (isLoading) {
        return <Loading />
    }


    if (gameError) {
        return <ErrorMessage message={gameError.message} title="Error Loading Game..." />
    }
    if (boxscoresError) {
        return <ErrorMessage message={boxscoresError.message} title="Error Loading Game Stats..." />
    }

    const hasBoxscoreData = boxscores && boxscores[0];

    return (
        <>
            <GameElement game={game} />

            {hasBoxscoreData ? (
                <>
                    <QuarterlyScores game={game} scores={boxscores[0]} />
                    <PlayerGameStats scores={boxscores[0]} />
                </>
            ) : (
                <>
                    <div className="container mt-4">
                        <div className="alert alert-info">
                            <i className="fas fa-info-circle me-2"></i>
                            Game data is not available for this game yet. 
                            Please try again after it airs.
                        </div>
                    </div>
                </>
            )}
            
            
        </>
    );
}

export default GamesDetail;