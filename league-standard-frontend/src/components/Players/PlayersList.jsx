import { usePlayers } from '../../hooks/usePlayers.js';
import ListElement from './ListElement.jsx';
import ErrorMessage from '../UI/ErrorMessage';
import Loading from '../UI/Loading';
import { useEffect, useMemo, useState } from 'react';

function PlayersList() {
    const { data: players, isLoading, error } = usePlayers();
    const [selectedLetter, setSelectedLetter] = useState('ALL')
    const alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split('');

    const availableLetters = useMemo(() => {
        if (!players) return new Set();
        return new Set(players.map(player => player.last_name.charAt(0).toUpperCase()));
    }, [players]);

    const filteredPlayers = useMemo(() => {
        if (!players || selectedLetter == 'ALL') return players || [];
        return players.filter(player =>
            player.last_name.charAt(0).toUpperCase() == selectedLetter
        );
    }, [players, selectedLetter]);


    useEffect(() => {
        if (players && selectedLetter != 'ALL' && !availableLetters.has(selectedLetter)) {
            setSelectedLetter('ALL');
        }
    }, [players, selectedLetter, availableLetters]);


    if (isLoading) {
        return <Loading />
    }


    if (error) {
        return <ErrorMessage message={error.message} title="Error Loading Players..." />
    }

    return(
        <>
            <div className='container mb-4 text-center'>
                <div className='row justify-content-center'>
                    <div className='col-12'>
                        <div className='alphabet-filter d-flex flex-wrap justify-content-center gap-2 mt-4'>
                            <button key='ALL' onClick={() => setSelectedLetter('ALL')} className={`btn btn-sm ${selectedLetter == 'ALL' ? 'btn-success' : 'btn-outline-secondary'} alphabet-btn`} >
                                ALL
                            </button>
                            {alphabet.map(letter => (
                                <button key={letter} onClick={() => setSelectedLetter(letter)} className={`btn btn-sm ${selectedLetter == letter ? 'btn-success' : availableLetters.has(letter) ? 'btn-outline-success' : 'btn-outline-secondary'} alphabet-btn`} disabled={!availableLetters.has(letter)} title={!availableLetters.has(letter) ? `No players with last names starting with ${letter}` : ''}>
                                    {letter}
                                </button>
                            ))}
                        </div>
                    </div>
                </div>
            </div> 
            <div className='container text-center'>
                <div className='row g-3'>
                    {filteredPlayers.length > 0 ? (
                        <ListElement key={`players-list-${selectedLetter}`} players={filteredPlayers} displayHeaders={true} />
                    ) : (
                        <ErrorMessage message={`No players found for letter "${selectedLetter}"`} title='No players found...'/>
                    )}
                </div>
            </div>
        </>
    );
}

export default PlayersList;