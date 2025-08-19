import { useState, useEffect } from 'react';
import Loading from '../UI/Loading';
import GameElement from './GameElement';

function GamesList() {
    const [games, setGames] = useState([]);
    const [allGames, setAllGames] = useState([]);
    const [isLoading, setIsLoading] = useState(true);


    useEffect(() => {
        fetch('http://localhost:8080/api/games')
            .then(res => res.json())
            .then(data => {
                const sortedGames = data.sort((a, b) =>
                    new Date(a.scheduled) - new Date(b.scheduled)
                );
                setAllGames(sortedGames);
                setGames(sortedGames);
                setIsLoading(false);
            })
            .catch(err => console.error("Error: ", err))
    }, [])


    function showAllGames() {
        setGames(allGames);
    }

    function showUpcomingGames() {
        const upcoming = games.filter(game => new Date(game.scheduled) > new Date());
        setGames(upcoming);
    }

    if (isLoading) return <Loading />

    return (
        <>
            <div>
                <button type="button" className='mt-4 ms-5 mb-0 btn btn-outline-success' onClick={showAllGames}>All Games</button>
                <button type="button" className='mt-4 ms-2 mb-0 btn btn-outline-success' onClick={showUpcomingGames}>Upcoming Games</button>
               
                {games.map(game => ( 
                    <GameElement game={game} />
                ))}
            </div>
        </>
    );
}

export default GamesList;