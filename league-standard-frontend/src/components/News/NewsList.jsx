import Loading from '../UI/Loading';
import NewsCard from './NewsCard';
import { useNews } from '../../hooks/useNews';
import ErrorMessage from '../UI/ErrorMessage';


function NewsList() {
    const { data: news = [], isLoading, error } = useNews();

    if (isLoading) {
        return (
            <Loading />
        )
    }

    if (error) {
        return <ErrorMessage message={error.message} title="Error Loading News..."/>
    }



    return(
        <>
            <div className="container">
                {news.map(article => (
                    <NewsCard key={article.id} article={article} />
                ))}
            </div>
        </>
    );
}

export default NewsList;