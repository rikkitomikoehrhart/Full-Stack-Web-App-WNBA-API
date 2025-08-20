import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import Loading from "../UI/Loading";
import { getFormattedDateTime } from "../Utilities/playerUtils.jsx";
import Gallery from "../UI/Elements/Gallery.jsx";

function NewsDetail() {
    const { id } = useParams();
    const [article, setArticle] = useState(null);
    const [isLoading, setIsLoading] = useState(true);


    useEffect(() => {
        const fetchArticle = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/news/${id}`);
                const articleData = await response.json();

                setArticle(articleData);
            } catch (error) {
                console.error('Error fetching article: ', error)
            } finally {
                setIsLoading(false)
            }
        };

        fetchArticle();
    }, [id]);


    if (isLoading) {
        return (
            <Loading />
        )
    }

    if (!article) {
        return (
            <>
                <div className="text-center">
                    <h2>Article not found</h2>
                </div>
            </>
        )
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