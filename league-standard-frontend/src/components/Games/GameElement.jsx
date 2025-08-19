

function GameElement({ game }) {


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