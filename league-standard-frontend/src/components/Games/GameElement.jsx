import { useBookmarks } from "../Context/BookmarksContext";
import ErrorMessage from "../UI/ErrorMessage";
import { DEFAULT_IMAGE_PATHS } from "../../constants/ui";
import TeamLogo from "../UI/TeamLogo";

function GameElement({ game }) {
    const { bookmarks, toggleGameBookmark } = useBookmarks();


    if (!game) {
        return <ErrorMessage message={"Game information is not available..."} title="Error..." />
    }


    let isBookmarked = false;
    if (Array.isArray(bookmarks)) {
        isBookmarked = bookmarks.find(b => b.game && b.game.id === game.id);
    }

    return(
        <>
            <div key={game.id} className="mt-5 card text-center shadow-sm">
                <div className='card-header'>
                    {new Date(game.scheduled).toLocaleDateString('en-US', {
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
                            <div className='col'><TeamLogo team={game.homeTeam} size="game" className="" showLink={true} /></div>
                            <div className='col'><h3>vs.</h3></div>
                            <div className='col'><TeamLogo team={game.awayTeam} size="game" className="" showLink={true} /></div>
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