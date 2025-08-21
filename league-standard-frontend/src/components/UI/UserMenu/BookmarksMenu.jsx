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
                                getFormattedBookmark(bookmark)
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

function getFormattedBookmark(bookmark) {
    if (bookmark.news) {
        if (bookmark.news.headline <= 25) {
            return (
                <li key={bookmark.id} className="list-group-item">
                    <a href={`/news/${bookmark.news.id}`}>
                        {bookmark.news.headline}
                    </a>
                </li>

            );
        } else {
            let headline = (bookmark.news.headline).slice(0, 25) + "...";
            return (
                <li key={bookmark.id} className="list-group-item">
                    <a href={`/news/${bookmark.news.id}`}>
                        {headline}
                    </a>
                </li>

            );
        }
    } else {
        const gameDate = new Date(bookmark.game.scheduled + 'T12:00:00').toLocaleDateString('en-US', {
            year: 'numeric',
            month: 'short',
            day: 'numeric'
        })
        return (
            <li key={bookmark.id} className="list-group-item">
                <a href={`/games/${bookmark.game.id}`}>
                    {gameDate + " | " + bookmark.game.homeTeam.alias + " v. " + bookmark.game.awayTeam.alias}
                </a>
            </li>

        );
    }

}

export default BookmarksMenu;