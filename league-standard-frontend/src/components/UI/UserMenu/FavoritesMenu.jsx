import { useFavorites } from './FavoritesContext';

function FavoritesMenu() {
    const { favoriteTeams } = useFavorites();

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
                                <img className="logo-favorite" src={`./team-logos/${(favorite.team.name).toLowerCase()}.svg`} alt={`${favorite.team.market} ${favorite.team.name} Logo`} />
                                    {(favorite.team.market).toUpperCase()} {(favorite.team.name).toUpperCase()}
                                </li>
                            ))}
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