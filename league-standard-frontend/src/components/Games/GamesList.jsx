import { useState, useEffect } from 'react';
import GameElement from './GameElement';
import { useGames } from '../../hooks/useGames';

function GamesList() {
    const { data: games, error } = useGames();
    const [filteredGames, setFilteredGames] = useState([]);
    const [activeFilter, setActiveFilter] = useState('all');


    useEffect(() => {
        if (games) {
            setFilteredGames(games);
        }
    }, [games]);

    function showAllGames() {
        setFilteredGames(games || []);
        setActiveFilter('all');
    }

    function showUpcomingGames() {
        if (games) {
            const today = new Date();
            today.setHours(0, 0, 0, 0);
            const upcoming = games.filter(game => {
                const gameDate = new Date(game.scheduled)

                return gameDate >= today;
                
            });
            
            setFilteredGames(upcoming);
        }
        setActiveFilter('upcoming');
    }


    if (error) {
        return (
            <div className='text-center mt-5'>
                <h3>Error loading games</h3>
                <p className='text-muted'>{error.message}</p>
            </div>
        );
    }

    return (
        <>
            <div>
                <button type="button" className={`mt-4 ms-5 mb-0 btn ${activeFilter === 'all' ? 'btn-success' : 'btn-outline-success'}`} onClick={showAllGames}>
                    All Games
                </button>
                <button type="button" className={`mt-4 ms-2 mb-0 btn ${activeFilter === 'upcoming' ? 'btn-success' : 'btn-outline-success'}`} onClick={showUpcomingGames}>
                    Upcoming Games
                </button>
               
                {filteredGames.map(game => ( 
                    <GameElement key={game.id} game={game} />
                ))}
            </div>
        </>
    );
}

export default GamesList;