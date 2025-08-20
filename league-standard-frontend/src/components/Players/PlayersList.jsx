import { usePlayers } from '../Context/PlayersContext.jsx';
import Loading from '../UI/Loading';
import ListElement from './ListElement.jsx';


function PlayersList() {
    const { players } = usePlayers();


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