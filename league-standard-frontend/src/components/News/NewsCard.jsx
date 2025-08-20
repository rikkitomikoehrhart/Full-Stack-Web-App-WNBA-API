

function NewsCard({ article }) {

    return (
        <>
            <div className="card m-4 shadow-sm" key={article.id}>
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