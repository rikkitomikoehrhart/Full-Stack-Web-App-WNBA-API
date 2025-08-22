import { usePlayers } from '../../hooks/usePlayers.js';
import ListElement from './ListElement.jsx';
import ErrorMessage from '../UI/ErrorMessage';
import Loading from '../UI/Loading';

function PlayersList() {
    const { data: players, isLoading, error } = usePlayers();

    if (isLoading) {
        return <Loading />
    }


    if (error) {
        return <ErrorMessage message={error.message} title="Error Loading Players..." />
    }

    return(
        <>
            <div className='container text-center'>
                <div className='row g-3'>
                    <ListElement key="players list" players={players} displayHeaders={true} />
                </div>
            </div>
        </>
    );
}

export default PlayersList;