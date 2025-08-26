import { useState } from 'react';
import Loading from '../UI/Loading';
import NewsCard from './NewsCard';
import { useNews } from '../../hooks/useNews';
import ErrorMessage from '../UI/ErrorMessage';


function NewsList() {
    const { data: news = [], isLoading, error } = useNews();
    const [visibleCount, setVisibleCount] = useState(10);
    const ITEMS_PER_LOAD = 10;



    if (isLoading) {
        return (
            <Loading />
        )
    }

    if (error) {
        return <ErrorMessage message={error.message} title="Error Loading News..."/>
    }


    const sortedNews = news.sort((a, b) => new Date(b.published) - new Date(a.published));

    const visibleNews = sortedNews.slice(0, visibleCount);

    const hasMore = visibleCount < sortedNews.length;

    const loadMore = () => {
        setVisibleCount(prev => prev + ITEMS_PER_LOAD);
    };

    return(
        <>
            <div className='container'>
                <div className='input-group mt-4'>
                    <input type="text" className='form-control' placeholder='Enter Team Name or Player Name to Search Articles...' aria-label="Search" aria-describedby='button-addon2'/>
                    <button className='btn btn-success' type="button" id="button-addon2">Search</button>
                </div>
            </div>

            <div className="container">
                {visibleNews.map(article => (
                    <NewsCard key={article.id} article={article}/>
                ))}

                {hasMore && (
                    <div className='load-more-container' style={{ textAlign: 'center', margin: '2rem 0'}}>
                        <button type="button" onClick={loadMore} className=' btn btn-outline-success'>
                            Load More Articles ({sortedNews.length - visibleCount} remaining)
                        </button>
                    </div>
                )}

                {!hasMore && sortedNews.length > 0 && (
                    <div className='text-center'>
                        <p className='text-muted'>You've reached the end of all the articles!</p>
                    </div>
                )}
            </div>
        </>
    );
}

export default NewsList;