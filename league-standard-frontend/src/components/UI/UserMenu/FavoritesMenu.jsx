import { useFavorites } from '../../Context/FavoritesContext';
import { generatePlayerHeadshotPath } from '../../Utilities/playerUtils';
import { DEFAULT_IMAGE_PATHS } from '../../../constants/ui';
import { ROUTES } from '../../../constants/routes';

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
                                    <a href={`${ROUTES.TEAMS}/${favorite.team.id}`} className='text-end'>
                                        <img className="logo-favorite" src={`${DEFAULT_IMAGE_PATHS.TEAM_LOGOS}/${(favorite.team.name).toLowerCase()}.svg`} alt={`${favorite.team.market} ${favorite.team.name} Logo`} />
                                        {(favorite.team.market).toUpperCase()} {(favorite.team.name).toUpperCase()}
                                    </a>
                                
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
                                    <a href={`${ROUTES.PLAYERS}/${favorite.player.id}`}>
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