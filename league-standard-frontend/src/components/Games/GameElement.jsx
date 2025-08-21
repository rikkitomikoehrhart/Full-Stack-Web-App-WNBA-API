import { useBookmarks } from "../Context/BookmarksContext";

function GameElement({ game }) {
    const { bookmarks, toggleGameBookmark } = useBookmarks();

    let isBookmarked = false;
    if (Array.isArray(bookmarks)) {
        isBookmarked = bookmarks.find(b => b.games && b.games.id === game.id);
    }

    return(
        <>
            <div key={game.id} className="mt-5 card text-center shadow-sm">
                <div className='card-header'>
                    {new Date(game.scheduled + 'T12:00:00').toLocaleDateString('en-US', {
                        weekday: 'short',
                        year: 'numeric',
                        month: 'short',
                        day: 'numeric'
                    })}
                </div>
                <div className='card-body'>


                    <div className='mt-auto'>
                        <button className='btn btn-link p-0 border-0 position-absolute' onClick={() => toggleGameBookmark(game.id)} style={{ background: 'none', top: '10px', left: '10px', zIndex: 1 }}>
                            {isBookmarked ? (
                                <svg width="24" height="24" viewBox="0 0 24 24" fill="#ff6b35" stroke="#000000ff" strokeWidth="2">
                                    <path d="M19 21l-7-5-7 5V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v16z"/>
                                </svg>
                            ) : (
                                <svg width="24" height="24" viewBox="0 0 24 24" fill="#ffffff50" stroke="#666" strokeWidth="2">
                                    <path d="M19 21l-7-5-7 5V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v16z"/>
                                </svg>
                            )}
                        </button>
                    </div>

                    <div className='container text-center'>
                        <div className='row'>
                            <div className='col'><a href={`/teams/${game.homeTeam.id}`}>{game.homeTeam.market} {game.homeTeam.name}</a></div>
                            <div className='col'></div>
                            <div className='col'><a href={`/teams/${game.awayTeam.id}`}>{game.awayTeam.market} {game.awayTeam.name}</a></div>
                        </div>

                        <div className='row'>
                            <div className='col'><a href={`/teams/${game.homeTeam.id}`}><img className="game-logo-team" src={`/team-logos/${(game.homeTeam.name).toLowerCase()}.svg`} alt={`${game.homeTeam.market} ${game.homeTeam.name} Logo`} /></a></div>
                            <div className='col'><h3>vs.</h3></div>
                            <div className='col'><a href={`/teams/${game.awayTeam.id}`}><img className="game-logo-team" src={`/team-logos/${(game.awayTeam.name).toLowerCase()}.svg`} alt={`${game.awayTeam.market} ${game.awayTeam.name} Logo`} /></a></div>
                        </div>

                        <div className='row'>
                            <div className='col'><h3>{game.homeScore == 0 ? "--" : game.homeScore}</h3></div>
                            <div className="col"></div>
                            <div className='col'><h3>{game.awayScore == 0 ? "--" : game.awayScore}</h3></div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default GameElement;