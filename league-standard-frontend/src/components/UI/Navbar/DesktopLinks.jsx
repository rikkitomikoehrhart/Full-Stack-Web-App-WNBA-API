function DesktopLinks() {
    return (
        <>
            <ul className="nav-links desktop-nav d-none d-lg-flex">
                <li><a href="/" className="nav-link">Home</a></li>
                <li><a href="/standings" className="nav-link">Standings</a></li>
                <li><a href="/games" className="nav-link">Games</a></li>
                <li><a href="/teams" className="nav-link">Teams</a></li>
                <li><a href="/players" className="nav-link">Players</a></li>
            </ul>
        </>
    );
}

export default DesktopLinks;