import { useFavorites } from '../../Context/FavoritesContext';
import { generatePlayerHeadshotPath } from '../../../utils/playerUtils';
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
                                    <a href={`${ROUTES.TEAMS}/${favorite.team.id}`} className='d-flex justify-content-between align-items-center text-decoration-none p-2'>
                                        <img className="logo-favorite" src={`${DEFAULT_IMAGE_PATHS.TEAM_LOGOS}/${(favorite.team.name).toLowerCase()}.svg`} alt={`${favorite.team.market} ${favorite.team.name} Logo`} />
                                        {(favorite.team.market).toUpperCase()} {(favorite.team.name).toUpperCase()}
                                    </a>
                                </li>
                            ))}
                            {favoritePlayers.length > 0 ? (
                                favoritePlayers.map(player => (
                                    <li key={player.player.id} className='list-group-item text-muted'>
                                        <a href={`${ROUTES.PLAYERS}/${player.player.id}`} className='d-flex justify-content-between align-items-center text-decoration-none p-2'>
                                        <img className="logo-favorite" src={generatePlayerHeadshotPath(player.player)} alt={`${player.player.first_name} ${player.player.last_name}`} />
                                        {(player.player.first_name).toUpperCase()} {(player.player.last_name).toUpperCase()}
                                    </a>
                                    </li>
                                ))
                            ) : (
                                <p className="text-muted">No favorite players yet</p>
                            )}
                        </ul>
                    ) : (
                        <p className="text-muted">No favorite teams yet</p>
                    )}

                   
                </div>
            </div>
        </>
    );
}

export default FavoritesMenu;