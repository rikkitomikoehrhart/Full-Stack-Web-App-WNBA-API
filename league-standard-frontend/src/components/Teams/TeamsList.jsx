import { useFavorites } from '../Context/FavoritesContext';
import { useState, useEffect } from 'react';
import Loading from '../UI/Loading';



function TeamsList() {
    const [teams, setTeams] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    const { favoriteTeamIDs, toggleTeamFavorite } = useFavorites();

    useEffect(() => {
        fetch("http://localhost:8080/api/teams")
            .then(res => res.json())
            .then(data => {
                setTeams(data);
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
                                    <a href={`/teams/${team.id}`}>
                                        <p className='card-text text-muted small'>{team.market}</p> 
                                        <img className="logo-team" src={`./team-logos/${(team.name).toLowerCase()}.svg`} alt={`${team.market} ${team.name} Logo`} />
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