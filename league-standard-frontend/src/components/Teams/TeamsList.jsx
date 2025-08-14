import { useState, useEffect } from 'react';
import Loading from '../UI/Loading';

function TeamsList() {
    const [teams, setTeams] = useState([]);
    const [favoriteTeams, setFavoriteTeams] = useState(new Set());
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const fetchTeams = fetch("http://localhost:8080/api/teams")
                            .then(res => res.json());

        const fetchFavoriteTeams = fetch("http://localhost:8080/api/favorites/teams")
                                    .then(res => res.json())
                                    .then(favorites => {
                                        const favoriteIDs = new Set(favorites.map(fav => fav.team.id));
                                        setFavoriteTeams(favoriteIDs);
                                        return favoriteIDs;
                                    })
                                    .catch(err => {
                                        console.error("Error fetching favorites: ", err);
                                        return new Set();
                                    });
        
        Promise.all([fetchTeams, fetchFavoriteTeams])
                .then(([teamsData, favoritesSet]) => {
                    setTeams(teamsData);
                    setIsLoading(false);
                })
                .catch(err => {
                    console.error("Error: ", err);
                    setIsLoading(false);
                });
    }, []);

    const toggleFavorite = async (teamID) => {
        const isFavorite = favoriteTeams.has(teamID);

        try {
            if (isFavorite) {
                await fetch(`http://localhost:8080/api/favorites/teams/${teamID}`, {method: 'DELETE'});
                setFavoriteTeams(prev => {
                    const newSet = new Set(prev);
                    newSet.delete(teamID);
                    return newSet
                })
            } else {
                await fetch(`http://localhost:8080/api/favorites/teams/${teamID}`, {method: 'POST'});
                setFavoriteTeams(prev => new Set([...prev, teamID]));
            }
        } catch (error) {
            console.error("Error toggling favorite: ", error)
        }
    };

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
                            <div className='card shadow-sm h-100'>
                                <div className='card-body d-flex flex-column'>
                                    <p className='card-text text-muted small'>{team.market}</p> 
                                    <p className='card-text'>{team.name}</p>
                                </div>
                                <div className='mt-auto mb-3 pt-2'>
                                    <button className='btn btn-link p-0 border-0' onClick={() => toggleFavorite(team.id)} style={{ background: 'none' }}>
                                        {favoriteTeams.has(team.id) ? (
                                                <svg width="24" height="24" viewBox="0 0 24 24" fill="#ff6b35" stroke="#dd4f1cff" strokeWidth="2" >
                                                    <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"/>
                                                </svg>
                                            ) : (
                                                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#666" strokeWidth="2">
                                                    <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"/>
                                                </svg>
                                            )}
                                    </button>
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