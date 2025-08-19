import { useFavorites } from '../../Context/FavoritesContext';
import { generatePlayerHeadshotPath } from '../../Utilities/playerUtils';

function FavoritesMenu() {
    const { favoriteTeams, favoritePlayers } = useFavorites();

    return (
        <>
            <div className="card text-center shadow-sm">
                <div className="card-title">
                    <h4 className="mt-4">Favorites</h4>
                </div>
                <div className="card-body">
                    {favoriteTeams.length > 0 ? (
                        <ul className="list-group list-group-flush">
                            {favoriteTeams.map(favorite => (
                                <li key={favorite.team.id} className='list-group-item text-muted'>
                                <img className="logo-favorite" src={`/team-logos/${(favorite.team.name).toLowerCase()}.svg`} alt={`${favorite.team.market} ${favorite.team.name} Logo`} />
                                    {(favorite.team.market).toUpperCase()} {(favorite.team.name).toUpperCase()}
                                </li>
                            ))}
                        </ul>
                    ) : (
                        <p className="text-muted">No favorite teams yet</p>
                    )}

                    {favoritePlayers.length > 0 ? (
                        <ul className="list-group list-group-flush">
                            {favoritePlayers.map(favorite => (
                                <li key={favorite.player.id} className='list-group-item text-muted'>
                                    <a href={`/players/${favorite.player.id}`}>
                                        <img className="logo-favorite" src={generatePlayerHeadshotPath(favorite.player)} alt={`${favorite.player.first_name} ${favorite.player.last_name}`} />
                                        {(favorite.player.first_name).toUpperCase()} {(favorite.player.last_name).toUpperCase()}
                                    </a>
                                
                                </li>
                            ))}
                        </ul>
                    ) : (
                        <p className="text-muted">No favorite players yet</p>
                    )}
                </div>
            </div>
        </>
    );
}

export default FavoritesMenu;