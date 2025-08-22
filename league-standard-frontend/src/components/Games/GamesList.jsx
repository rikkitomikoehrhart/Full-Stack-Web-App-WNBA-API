import { useState } from 'react';
import GameElement from './GameElement';
import { useGames } from '../../hooks/useGames';

function GamesList() {
    const { data: games, error } = useGames();
    const [allGames, setAllGames] = useState(games);


    function showAllGames() {
        setAllGames(games);
    }

    function showUpcomingGames() {
        const upcoming = games.filter(game => new Date(game.scheduled) >= new Date());
        setAllGames(upcoming);
    }

    if (error) {
        return(
            <div className='text-center mt-5'>
                <h3>Error loading games</h3>
                <p className='text-muted'>{error.message}</p>
            </div>
        );
    };

    return (
        <>
            <div>
                <button type="button" className='mt-4 ms-5 mb-0 btn btn-outline-success' onClick={showAllGames}>All Games</button>
                <button type="button" className='mt-4 ms-2 mb-0 btn btn-outline-success' onClick={showUpcomingGames}>Upcoming Games</button>
               
                {allGames?.map(game => ( 
                    <GameElement game={game} />
                )) || []}
            </div>
        </>
    );
}

export default GamesList;