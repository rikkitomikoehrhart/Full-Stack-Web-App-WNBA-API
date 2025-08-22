import { ROUTES } from "../../../constants/routes";

function DesktopLinks() {
    return (
        <>
            <ul className="nav-links desktop-nav d-none d-lg-flex">
                <li><a href={`${ROUTES.HOME}`} className="nav-link">Home</a></li>
                <li><a href={`${ROUTES.STANDINGS}`} className="nav-link">Standings</a></li>
                <li><a href={`${ROUTES.GAMES}`} className="nav-link">Games</a></li>
                <li><a href={`${ROUTES.TEAMS}`} className="nav-link">Teams</a></li>
                <li><a href={`${ROUTES.PLAYERS}`} className="nav-link">Players</a></li>
            </ul>
        </>
    );
}

export default DesktopLinks;