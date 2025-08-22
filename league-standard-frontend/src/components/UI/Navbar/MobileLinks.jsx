import { ROUTES } from "../../../constants/routes";

function MobileLinks({ isOpen, toggleMenu }) {
    return (
        <>
            <div className={`mobile-nav d-lg-none ${isOpen ? 'active' : ''}`}>
                <ul className="mobile-nav-links">
                    <li><a href={`${ROUTES.HOME}`} className="mobile-nav-link" onClick={toggleMenu}>Home</a></li>
                    <li><a href={`${ROUTES.STANDINGS}`} className="mobile-nav-link" onClick={toggleMenu}>Standings</a></li>
                    <li><a href={`${ROUTES.GAMES}`} className="mobile-nav-link" onClick={toggleMenu}>Games</a></li>
                    <li><a href={`${ROUTES.TEAMS}`} className="mobile-nav-link" onClick={toggleMenu}>Teams</a></li>
                    <li><a href={`${ROUTES.PLAYERS}`} className="mobile-nav-link" onClick={toggleMenu}>Players</a></li>
                </ul>
            </div>
        </>
    );
}

export default MobileLinks;