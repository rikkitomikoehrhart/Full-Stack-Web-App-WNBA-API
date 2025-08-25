import { useState, useEffect } from 'react';
import GameElement from './GameElement';
import { useGames } from '../../hooks/useGames';
import ErrorMessage from '../UI/ErrorMessage';
import Loading from '../UI/Loading';
import { useTeams } from '../../hooks/useTeams';
import TeamLogo from '../UI/TeamLogo';

function GamesList() {
    const { data: games, gameError } = useGames();
    const [filteredGames, setFilteredGames] = useState([]);
    const [activeFilter, setActiveFilter] = useState('ALL');
    const { data: teams = [], isLoading, teamsError } = useTeams();
    let selectedTeam = false;
    const [selectedTeamGames, setSelectedTeamGames] = useState([]);


    useEffect(() => {
        if (games) {
            setFilteredGames(games);
        }
    }, [games]);

    function showAllGames() {
        setFilteredGames(games || []);
        setActiveFilter('ALL');
        selectedTeam = false;
    }

    function showUpcomingGames() {
        if (games & !selectedTeam) {
            const today = new Date();
            today.setHours(0, 0, 0, 0);
            const upcoming = games.filter(game => {
                const gameDate = new Date(game.scheduled)

                return gameDate >= today;
                
            });
            
            setFilteredGames(upcoming);
        } else {
            const today = new Date();
            today.setHours(0, 0, 0, 0);
            const upcoming = selectedTeamGames.filter(game => {
                const gameDate = new Date(game.scheduled)

                return gameDate >= today
            });

            setFilteredGames(upcoming);
        };
        setActiveFilter('UPCOMING');
    }

    function showGamesByTeam(team) {
        if (games) {
            const gamesByTeam = games.filter(game => game.homeTeam.id == team.id || game.awayTeam.id == team.id)

            setFilteredGames(gamesByTeam);
            setSelectedTeamGames(gamesByTeam);
            selectedTeam = true;
        }
        
    }

    if (isLoading) {
        return (
            <Loading />
        )
    }

    if (gameError) return <ErrorMessage message={gameError.message} title='Error Loading Games...' />
    if (teamsError) return <ErrorMessage message={teamsError.message} title='Error Loading Teams...' />

    return (
        <>
            <div>
                <button type="button" className={`mt-4 ms-5 mb-0 btn ${activeFilter === 'ALL' ? 'btn-success' : 'btn-outline-success'}`} onClick={showAllGames}>
                    All Games
                </button>
                <button type="button" className={`mt-4 ms-2 mb-0 btn ${activeFilter === 'upcoming' ? 'btn-success' : 'btn-outline-success'}`} onClick={showUpcomingGames}>
                    Upcoming Games
                </button>
                <br />
                <div className='container text-center mt-4'>
                    {teams.map(team => (
                        <button key={team.alias} onClick={() => showGamesByTeam(team)} className='logo-button'>
                            <TeamLogo key={team.id} team={team} size='favorite' className='' showLink={false} />
                        </button>
                        
                    ))}
                </div>


                {filteredGames.map(game => ( 
                    <GameElement key={game.id} game={game} />
                ))}
            </div>
        </>
    );
}

export default GamesList;