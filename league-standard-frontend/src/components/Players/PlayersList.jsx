import { useFavorites } from '../UI/UserMenu/FavoritesContext';
import { useState, useEffect } from 'react';
import Loading from '../UI/Loading';
import { generatePlayerHeadshotPath } from "../Utilities/playerUtils.jsx";

function PlayersList() {
    const [players, setPlayers] = useState([])
    const [isLoading, setIsLoading] = useState(true);

    const { favoritePlayerIDs, togglePlayerFavorite } = useFavorites();

    useEffect(() => {
        fetch("http://localhost:8080/api/players")
            .then(res => res.json())
            .then(data => {
                const sortedData = data.sort((a, b) => {
                    const lastNameCompare = a.last_name.localeCompare(b.last_name);
                    if (lastNameCompare !== 0) {
                        return lastNameCompare;
                    }
                    return a.first_name.localeCompare(b.first_name);
                });
                setPlayers(sortedData);
                setIsLoading(false);
            })
            .catch(err => {
                console.error("Error: ", err);
                setIsLoading(false);
            });
    }, []);



    if (isLoading) {
        return (
            <Loading />
        )
    }

    return(
        <>
            <div className='container text-center'>
                <div className='row g-3'>
                    {players.map((player, index) => {
                        const showLetterHeader = index === 0 || 
                            (player.last_name.charAt(0) !== players[index - 1].last_name.charAt(0));
                        
                        return (
                            <> {/* Use this instead of React.Fragment */}
                                {showLetterHeader && (
                                    <div className='col-12'>
                                        <h1 className='mt-4'>{player.last_name.charAt(0)}</h1>
                                        <hr/>
                                    </div>
                                )}
                                <div className='col-lg-4 col-md-6 col-sm-12'>
                                    <div className='card shadow-sm h-100'>

                                        <div className='mt-auto mb-3 pt-2'>
                                            <button className='btn btn-link p-0 border-0 position-absolute' onClick={() => togglePlayerFavorite(player.id)} style={{ background: 'none', top: '10px', left: '10px', zIndex: 1 }}>
                                                {favoritePlayerIDs.has(player.id) ? (
                                                        <svg width="24" height="24" viewBox="0 0 24 24" fill="#ff6b35" stroke="#dd4f1cff" strokeWidth="2" >
                                                            <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"/>
                                                        </svg>
                                                    ) : (
                                                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#666" strokeWidth="2">
                                                            <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"/>
                                                        </svg>
                                                    )}
                                            </button>
                                        </div>


                                        <img src={generatePlayerHeadshotPath(player)} className='card-img-top' alt={`${player.first_name} ${player.last_name}`}/>
                                        <div className='card-body'>
                                            <h5 className='card-title player-name-card'>{player.first_name} {player.last_name}</h5>
                                            <p className='card-text text-muted player-num-team'>#{player.jersey_number} on the {player.team.market} {player.team.name}</p>
                                        </div>
                                    </div>
                                </div>
                            </>
                        );
                    })}
                </div>
            </div>
        </>
    );
}

export default PlayersList;