import { useState } from 'react';
import Loading from '../UI/Loading';
import NewsCard from './NewsCard';
import { useNews } from '../../hooks/useNews';
import ErrorMessage from '../UI/ErrorMessage';

function NewsList() {
    const { data: news = [], isLoading, error } = useNews();
    const [visibleCount, setVisibleCount] = useState(5);
    const [searchValue, setSearchValue] = useState('');
    const [activeSearchTerm, setActiveSearchTerm] = useState(''); 
    const [isSearching, setIsSearching] = useState(false);
    const ITEMS_PER_LOAD = 5;

    if (isLoading) {
        return (
            <Loading />
        )
    }

    if (error) {
        return <ErrorMessage message={error.message} title="Error Loading News..."/>
    }

    const sortedNews = news.sort((a, b) => new Date(b.published) - new Date(a.published));


    const filteredNews = isSearching && activeSearchTerm.trim() ? sortedNews.filter(article => {
        const searchTerm = activeSearchTerm.toLowerCase();
        const titleMatch = article.title?.toLowerCase().includes(searchTerm);
        const descriptionMatch = article.description?.toLowerCase().includes(searchTerm);
        const teamMatch = article.categories?.type == 'team' && article.categories?.description?.toLowerCase().includes(searchTerm);
        const playerMatch = article.categories?.type == 'athlete' && article.categories?.description.toLowerCase().includes(searchTerm);

        return titleMatch || descriptionMatch || teamMatch || playerMatch;
    }) : sortedNews;

    const visibleNews = filteredNews.slice(0, visibleCount);

    const hasMore = visibleCount < filteredNews.length;

    const loadMore = () => {
        setVisibleCount(prev => prev + ITEMS_PER_LOAD);
    };

    const handleSearchClick = () => {
        setActiveSearchTerm(searchValue);
        setIsSearching(true);
        setVisibleCount(ITEMS_PER_LOAD);
    }

    const clearSearch = () => {
        setSearchValue('');
        setActiveSearchTerm('');
        setIsSearching(false);
        setVisibleCount(ITEMS_PER_LOAD);
    }

    const handleKeyPress = (e) => {
        if (e.key == 'Enter') {
            handleSearchClick();
        }
    }

    return(
        <>
            <div className='container'>
                <div className='input-group mt-4'>
                    <input type="text" className='form-control shadow-lg' placeholder='Enter Team Name or Player Name to Search Articles...' aria-label="Search" aria-describedby='button-addon2' value={searchValue} onChange={(e) => setSearchValue(e.target.value)} onKeyDown={handleKeyPress}/>
                    <button className='btn btn-success shadow-lg' type="button" id="button-addon2" onClick={handleSearchClick}>
                        Search
                    </button>
                    {isSearching && (
                        <button className='btn btn-outline-secondary' type="button" onClick={clearSearch}>
                            Clear
                        </button>
                    )}
                </div>
                
                {isSearching && (
                    <div className='mt-3'>
                        <p className='text-muted'>
                            Showing results for "{activeSearchTerm}"
                        </p>
                    </div>
                )}
            </div>

            <div className="container">
                {visibleNews.length === 0 && isSearching ? (
                    <div className='text-center mt-4'>
                        <p className='text-muted'>No articles found matching your search.</p>
                    </div>
                ) : (
                    visibleNews.map(article => (
                        <NewsCard key={article.id} article={article}/>
                    ))
                )}

                {hasMore && (
                    <div className='load-more-container' style={{ textAlign: 'center', margin: '2rem 0'}}>
                        <button type="button" onClick={loadMore} className='btn btn-outline-success'>
                            Load More Articles
                        </button>
                    </div>
                )}

                {!hasMore && filteredNews.length > 0 && (
                    <div className='text-center'>
                        <p className='text-muted mt-4'>You've reached the end of all the articles!</p>
                    </div>
                )}
            </div>
        </>
    );
}

export default NewsList;