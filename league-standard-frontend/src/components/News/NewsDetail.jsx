import { useParams } from "react-router-dom";
import { useNewsByID } from '../../hooks/useNews.js';
import ErrorMessage from '../UI/ErrorMessage.jsx';
import Loading from "../UI/Loading";
import { getFormattedDateTime } from "../../utils/dateUtils.js";
import Gallery from "../UI/Elements/Gallery.jsx";

function NewsDetail() {
    const { id } = useParams();
    const { data: article, isLoading, error } = useNewsByID(id);

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
            <h2 className="ms-4 mt-4 me-4 fw-bold">{article.headline}</h2>
            <p className="ms-4 me-4 text-muted text-start">{getFormattedDateTime(article.published)}</p>
            <Gallery image_urls={article.image_urls} />
            <p className="m-4 text-start">{article.description}</p>

            <a href={article.link} target="_blank"><h5>Read the Full Article!</h5></a>
        </>
    );
}


export default NewsDetail;