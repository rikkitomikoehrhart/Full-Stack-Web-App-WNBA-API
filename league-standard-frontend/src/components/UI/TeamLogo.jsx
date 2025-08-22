import { DEFAULT_IMAGE_PATHS } from '../../constants/ui';
import { ROUTES } from '../../constants/routes';

function TeamLogo({ team, size = "medium", className="", showLink = true }) {
    const sizeClasses = {
        small: "standing-logo-team",
        medium: "logo-team",
        large: "large-logo",
        game: "game-logo-team",
        favorite: "logo-favorite"
    };

    const logoElement = (
        <img className={`${sizeClasses[size]} ${className}`} src={`${DEFAULT_IMAGE_PATHS.TEAM_LOGOS}/${(team.name).toLowerCase()}.svg`} alt={`${team.market} ${team.name} Logo`} />
    );

    if (showLink) {
        return (
            <a href={`${ROUTES.TEAMS}/${team.id}`}>
                {logoElement}
            </a>
        );
    };

    return logoElement;
}

export default TeamLogo;