import { useParams } from "react-router-dom";
import { useGame } from "../../hooks/useGames.js"
import Loading from "../UI/Loading.jsx";
import ErrorMessage from "../UI/ErrorMessage.jsx";
import GameElement from "./GameElement.jsx";

function GamesDetail() {
    const { id } = useParams();
    const { data: game, isLoading, error } = useGame(id);

    if (isLoading) {
        return <Loading />
    }


    if (error) {
        return <ErrorMessage message={error.message} title="Error Loading Game..." />
    }

    return (
        <>
            <GameElement game={game} />
        </>
    );
}

export default GamesDetail;