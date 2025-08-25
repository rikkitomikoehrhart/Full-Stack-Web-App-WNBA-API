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
    const [currentPage, setCurrentPage] = useState(1);
    const GAMES_PER_PAGE = 10;


    useEffect(() => {
        if (games) {
            setFilteredGames(games);
            setCurrentPage(1);
        }
    }, [games]);

    const totalPages = Math.ceil(filteredGames.length/GAMES_PER_PAGE);
    const startIndex = (currentPage - 1) * GAMES_PER_PAGE;
    const endIndex = startIndex + GAMES_PER_PAGE;
    const currentGames = filteredGames.slice(startIndex, endIndex);

    const resetPagination = () => {
        setCurrentPage(1);
    };

    function showAllGames() {
        setFilteredGames(games || []);
        setActiveFilter('ALL');
        selectedTeam = false;
    }

    function showUpcomingGames() {
        if (games && !selectedTeam) {
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
        resetPagination();
    }

    function showGamesByTeam(team) {
        if (games) {
            const gamesByTeam = games.filter(game => game.homeTeam.id == team.id || game.awayTeam.id == team.id)

            setFilteredGames(gamesByTeam);
            setSelectedTeamGames(gamesByTeam);
            selectedTeam = true;
            resetPagination();
        }
    }

    const goToPage = (page) => {
        setCurrentPage(page);

        document.getElementById('games-container')?.scrollIntoView({ behavior: 'smooth' });
    };

    const goToPrevious = () => {
        if (currentPage > 1) {
            goToPage(currentPage - 1);
        }
    };

    const goToNext = () => {
        if (currentPage < totalPages) {
            goToPage(currentPage + 1);
        }
    };

    const getVisiblePages = () => {
        const maxVisible = 5;
        let start = Math.max(1, currentPage - Math.floor(maxVisible / 2));
        let end = Math.min(totalPages, start + maxVisible - 1);

        if (end - start + 1 < maxVisible) {
            start = Math.max(1, end - maxVisible + 1);
        }

        return Array.from({ length: end - start + 1 }, (_, i) => start + i)
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

                <div className='container mt-3'>
                    <p className='text-muted text-center pagination-text'>
                        Showing {startIndex + 1}-{Math.min(endIndex, filteredGames.length)} of {filteredGames.length} games
                        <span className='tab'></span>
                        {totalPages > 1 && `Page ${currentPage} of ${totalPages}`}
                    </p>
                </div>

                <div className='games-container'>
                    {currentGames.map(game => (
                        <GameElement key={game.id} game={game} />
                    ))}
                </div>

                {totalPages > 1 && (
                    <div className='container mt-4 mb-4'>
                        <nav aria-label='Games pagination'>
                            <ul className='pagination pagination-sm pagination-success justify-content-center'>
                                <li className={`page-item ${currentPage == 1 ? 'disable' : ''}`}>
                                    <button className="page-link" onClick={goToPrevious} disabled={currentPage === 1}>
                                        &laquo; Previous
                                    </button>
                                </li>

                                {getVisiblePages()[0] > 1 && (
                                    <>
                                        <li className='page-item'>
                                            <button className='page-link' onClick={() => goToPage(1)}>
                                                1
                                            </button>
                                        </li>
                                        {getVisiblePages()[0] > 2 && (
                                            <li className='page-item disabled'>
                                                <span className='page-link'>...</span>
                                            </li>
                                        )}
                                    </>
                                )}
                                {getVisiblePages().map(page => (
                                    <li key={page} className={`page-item ${currentPage === page ? 'active' : ''}`}>
                                        <button className='page-link' onClick={() => goToPage(page)}>{page}</button>
                                    </li>
                                ))}

                                {getVisiblePages()[getVisiblePages().length - 1] < totalPages && (
                                    <>
                                        {getVisiblePages()[getVisiblePages().length - 1] < totalPages - 1 && (
                                            <li className="page-item disabled">
                                                <span className="page-link">...</span>
                                            </li>
                                        )}
                                        <li className="page-item">
                                            <button className="page-link" onClick={() => goToPage(totalPages)}>
                                                {totalPages}
                                            </button>
                                        </li>
                                    </>
                                )}

                                <li className={`page-item ${currentPage === totalPages ? 'disabled' : ''}`}>
                                    <button className="page-link"onClick={goToNext} disabled={currentPage === totalPages}>
                                        Next &raquo;
                                    </button>
                                </li>
                            </ul>
                        </nav>
                    </div>
                )}
            </div>
        </>
    );
}

export default GamesList;