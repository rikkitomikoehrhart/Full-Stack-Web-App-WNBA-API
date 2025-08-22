import { useFavorites } from '../Context/FavoritesContext.jsx';
import { generatePlayerHeadshotPath } from "../Utilities/playerUtils.jsx";
import { useTeamColors } from '../../hooks/useTeamColors.js';
import ErrorMessage from '../UI/ErrorMessage';

function ListElement( { players = [], displayHeaders = false } ) {
    const { data: teamColors =[], isLoading, error } = useTeamColors();
    const { favoritePlayerIDs, togglePlayerFavorite } = useFavorites();

    if (isLoading) {
        return <Loading />
    }

    if (error) {
        return <ErrorMessage message={error.message} title="Error Loading Team Colors..." />
    }


    return (
        <>
            {players.map((player, index) => {
                const showLetterHeader = index === 0 || 
                    (player.last_name.charAt(0) !== players[index - 1].last_name.charAt(0));
                
                return (
                    <>
                        {showLetterHeader && displayHeaders && (
                            <div className='col-12'>
                                <h1 className='mt-4'>{player.last_name.charAt(0)}</h1>
                                <hr/>
                            </div>
                        )}
                        <div key={player.id} className='col-lg-4 col-md-6 col-sm-12'>
                                <div className='card shadow-sm h-100'>

                                    <div className='mt-auto'>
                                        <button className='btn btn-link p-0 border-0 position-absolute' onClick={() => togglePlayerFavorite(player.id)} style={{ background: 'none', top: '10px', left: '10px', zIndex: 1 }}>
                                            {favoritePlayerIDs.has(player.id) ? (
                                                    <svg width="24" height="24" viewBox="0 0 24 24" fill="#ff6b35" stroke="#000000ff" strokeWidth="2" >
                                                        <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"/>
                                                    </svg>
                                                ) : (
                                                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#666" strokeWidth="2">
                                                        <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"/>
                                                    </svg>
                                                )}
                                        </button>
                                    </div>

                                    <div style={{ backgroundColor: (teamColors.find((color) => color.teamID == player.team.id && color.colorType == "primary")?.hexCode + "cc" || "#ccc"), borderRadius: "5px"}}>
                                        <img src={generatePlayerHeadshotPath(player)} className='card-img-top pt-4' alt={`${player.first_name} ${player.last_name}`}/>
                                    </div>
                                    
                                    <div className='card-body'>
                                        <a href={`/players/${player.id}`}>
                                            <h5 className='card-title player-name-card'>{player.first_name} {player.last_name}</h5>
                                            <p className='card-text text-muted player-num-team'>#{player.jersey_number} on the {player.team.market} {player.team.name}</p>
                                        </a>
                                    </div>
                                </div>




                        </div>
                    </>
                );
            })}
        </>
    );
}

export default ListElement;