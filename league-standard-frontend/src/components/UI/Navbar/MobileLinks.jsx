function MobileLinks({ isOpen, toggleMenu }) {
    return (
        <>
            <div className={`mobile-nav d-lg-none ${isOpen ? 'active' : ''}`}>
                <ul className="mobile-nav-links">
                    <li><a href="/" className="mobile-nav-link" onClick={toggleMenu}>Home</a></li>
                    <li><a href="/standings" className="mobile-nav-link" onClick={toggleMenu}>Standings</a></li>
                    <li><a href="/games" className="mobile-nav-link" onClick={toggleMenu}>Games</a></li>
                    <li><a href="/teams" className="mobile-nav-link" onClick={toggleMenu}>Teams</a></li>
                    <li><a href="/players" className="mobile-nav-link" onClick={toggleMenu}>Players</a></li>
                </ul>
            </div>
        </>
    );
}

export default MobileLinks;