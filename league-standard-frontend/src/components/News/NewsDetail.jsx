import { useParams } from "react-router-dom";
import { useNewsByID } from '../../hooks/useNews.js';
import ErrorMessage from '../UI/ErrorMessage.jsx';
import Loading from "../UI/Loading";
import { getFormattedDateTime } from "../../utils/dateUtils.js";
import Gallery from "../UI/Elements/Gallery.jsx";
import NewsDetailIcons from "./NewsDetailIcons.jsx";
import { useBookmarks } from "../Context/BookmarksContext";


function NewsDetail() {
    const { id } = useParams();
    const { data: article, isLoading, error } = useNewsByID(id);
    const { bookmarks, toggleNewsBookmark } = useBookmarks();

    let isBookmarked = false;
    if (Array.isArray(bookmarks)) {
        isBookmarked = bookmarks.find(b => b.news && b.news.id === article.id);
    }

    if (isLoading) {
        return <Loading />
    }


    if (error) {
        return <ErrorMessage message={error.message} title="Error Loading Article..." />
    }

    if (!article) {
        return <ErrorMessage message="Article not found" title="Error..." />
    }


    return (
        <>
            <div className="container text-center">
                <h2 className="ms-4 mt-4 me-4 fw-bold d-flex align-items-start justify-content-center gap-1">
                    <button className='btn btn-link p-0 border-0 flex-shrink-0' onClick={() => toggleNewsBookmark(article.id)} style={{ background: 'none', lineHeight: 1, marginTop: '4px' }} title={isBookmarked ? "Remove bookmark" : "Add Bookmark"}>
                        {isBookmarked ? (
                            <svg width="24" height="24" viewBox="0 0 24 24" fill="#ff6b35" stroke="#000000ff" strokeWidth="2">
                                <path d="M19 21l-7-5-7 5V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v16z"/>
                            </svg>
                        ) : (
                            <svg width="24" height="24" viewBox="0 0 24 24" fill="#ffffff50" stroke="#636161ff" strokeWidth="2">
                                <path d="M19 21l-7-5-7 5V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v16z"/>
                            </svg>
                        )}
                    </button>
                    {article.headline}
                </h2>
                <p className="ms-4 me-4 text-muted text-start">{getFormattedDateTime(article.published)}</p>
                <Gallery image_urls={article.image_urls} />
                <p className="m-4 text-start">{article.description}</p>

                <a href={article.link} target="_blank"><h5>Read the Full Article!</h5></a>

                <NewsDetailIcons categories={article.categories} />

            </div>
            
        </>
    );
}


export default NewsDetail;