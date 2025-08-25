import { useBookmarks } from "../Context/BookmarksContext";

function NewsCard({ article }) {

    const { bookmarks, toggleNewsBookmark } = useBookmarks();

    let isBookmarked = false;
    if (Array.isArray(bookmarks)) {
        isBookmarked = bookmarks.find(b => b.news && b.news.id === article.id);
    }

    return (
        <>
            <div className="card m-4 shadow-sm" key={article.id}>

                <div className='mt-auto'>
                    <button className='btn btn-link p-0 border-0 position-absolute' onClick={() => toggleNewsBookmark(article.id)} style={{ background: 'none', top: '10px', left: '10px', zIndex: 1 }}>
                        {isBookmarked ? (
                            <svg width="24" height="24" viewBox="0 0 24 24" fill="#ff6b35" stroke="#000000ff" strokeWidth="2">
                                <path d="M19 21l-7-5-7 5V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v16z"/>
                            </svg>
                        ) : (
                            <svg width="24" height="24" viewBox="0 0 24 24" fill="#ffffff50" stroke="#fff" strokeWidth="2">
                                <path d="M19 21l-7-5-7 5V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v16z"/>
                            </svg>
                        )}
                    </button>
                </div>



                <img src={article.image_urls[0]} className="card-img-top" alt="..."/>
                <div className="card-body text-start m-3">
                    <h4 className="card-title fw-bold">{article.headline}</h4>
                    <p className="card-text text-muted">{article.description}</p>
                </div>
                <div className="card-footer text-body-secondary text-end">
                    <a href={`/news/${article.id}`}><p className="card-text">Read More...</p></a>
                </div>
            </div>
        </>
    );
}

export default NewsCard;