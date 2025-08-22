import { useFavorites } from '../Context/FavoritesContext';
import { useTeams } from '../../hooks/useTeams';
import ErrorMessage from '../UI/ErrorMessage';
import Loading from '../UI/Loading';
import { ROUTES } from '../../constants/routes';
import TeamLogo from '../UI/TeamLogo';

function TeamsList() {
    const { data: teams = [], isLoading, error } = useTeams();
    const { favoriteTeamIDs, toggleTeamFavorite } = useFavorites();


    if (isLoading) {
        return <Loading />
    }



    if (error) {
        return <ErrorMessage message={error.message} title='Error Loading Teams...' />
    }



    return (
        <>
            <div className='mt-4 container text-center'>
                <div className='row'>
                    {teams.map(team => (
                        <div key={team.id} className='col-lg-4 col-md-6 col-sm-12 mb-3'>
                            <div className='card shadow-sm h-100 position-relative'>
                                <div className='mt-auto mb-3 pt-2'>
                                    <button className='btn btn-link p-0 border-0 position-absolute' onClick={() => toggleTeamFavorite(team.id)} style={{ background: 'none', top: '10px', left: '10px', zIndex: 1 }}>
                                        {favoriteTeamIDs.has(team.id) ? (
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
                                
                                
                                
                                <div className='card-body d-flex flex-column'>
                                    <a href={`${ROUTES.TEAMS}/${team.id}`}>
                                        <p className='card-text text-muted small'>{team.market}</p> 
                                        <TeamLogo team={team} size='medium' className='' showLink={false} />
                                        <p className='card-text'>{team.name}</p>
                                    </a>
                                    
                                </div>
                            </div>
                        </div>
                    ))}          
                </div>
            </div>
        </>
    );
}

export default TeamsList;