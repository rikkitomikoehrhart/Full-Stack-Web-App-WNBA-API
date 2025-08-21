import { useBookmarks } from "../../Context/BookmarksContext";


function BookmarksMenu() {
    const { bookmarks } = useBookmarks();

    return (
        <>
            <div className="mt-5 mb-5 card text-center shadow-sm">
                <div className="card-title">
                    <h4 className="mt-4">Bookmarks</h4>
                </div>
                <div className="card-body">
                    {bookmarks.length > 0 ? (
                        <ul className="list-group list-group-flush">
                            {bookmarks.map(bookmark => (
                                <li key={bookmark.id} className="list-group-item">
                                    <a href={`/news/${bookmark.news.id}`}>
                                        {getFormattedHeadline(bookmark.news.headline)}
                                    </a>
                                </li>
                            ))}
                        </ul>
                    ) : (
                        <p className="text-muted">No bookmarks yet</p>
                    )}
                </div>
            </div>
        </>
    );
}

function getFormattedHeadline(headlineString) {
    if (headlineString.length <= 25) {
        return headlineString
    } else {
        return headlineString.slice(0, 25) + "...";
    }
}

export default BookmarksMenu;