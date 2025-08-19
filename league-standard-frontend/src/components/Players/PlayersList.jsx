
import { useState, useEffect } from 'react';
import Loading from '../UI/Loading';
import ListElement from './ListElement.jsx';


function PlayersList() {
    const [players, setPlayers] = useState([])
    const [isLoading, setIsLoading] = useState(true);



    useEffect(() => {
        fetch("http://localhost:8080/api/players")
            .then(res => res.json())
            .then(data => {
                const sortedData = data.sort((a, b) => {
                    const lastNameCompare = a.last_name.localeCompare(b.last_name);
                    if (lastNameCompare !== 0) {
                        return lastNameCompare;
                    }
                    return a.first_name.localeCompare(b.first_name);
                });
                setPlayers(sortedData);
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