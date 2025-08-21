import { usePlayers } from '../../hooks/usePlayers.js';
import ListElement from './ListElement.jsx';


function PlayersList() {
    const { data: players, error } = usePlayers();

    if (error) {
        return(
            <div className='text-center mt-5'>
                <h3>Error loading players</h3>
                <p className='text-muted'>{error.message}</p>
            </div>
        );
    };

    return(
        <>
            <div className='container text-center'>
                <div className='row g-3'>
                    <ListElement players={players} displayHeaders={true} />
                </div>
            </div>
        </>
    );
}

export default PlayersList;