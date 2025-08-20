import { useState, useEffect } from 'react';
import Loading from '../UI/Loading';
import NewsCard from './NewsCard';



function NewsList() {
    const [news, setNews] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        fetch("http://localhost:8080/api/news")
            .then(res => res.json())
            .then(data => {
                setNews(data);
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
            <div className="container">
                {news.map(article => (
                    <NewsCard article={article} />
                ))}
            </div>
        </>
    );
}

export default NewsList;