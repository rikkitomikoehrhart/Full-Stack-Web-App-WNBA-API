import { useTeams } from "../../hooks/useTeams";
import { usePlayers } from "../../hooks/usePlayers";
import ErrorMessage from "../UI/ErrorMessage";
import Loading from "../UI/Loading";
import TeamLogo from "../UI/TeamLogo";
import { generatePlayerHeadshotPath } from "../../utils/playerUtils";
import { ROUTES } from "../../constants/routes";

function NewsDetailIcons({ categories }) {
    const { data: teams = [], teamError } = useTeams();
    const { data: players, isLoading, playerError } = usePlayers();
    const article_teams = getTeams(categories);
    const article_players = getPlayers(categories);


    if (isLoading) return <Loading/>
    if (teamError || playerError) return <ErrorMessage message={teamError.message} title="Error Loading Subjects..."/>

    return (
        <>
            {article_teams.map(article_team => {
                const article_team_names = article_team.description.split(' ');
                const name = article_team_names[article_team_names.length-1];

                const team = teams.find(t => t.name == name);

                return (<TeamLogo key={team.id} team={team} size="medium" className="" showLink={true}/>)
            })}
            
            {article_players.map(article_player => {
                const player = players.find(p => `${p.first_name} ${p.last_name}` == article_player.description);

                return (<a key={player.id} href={`${ROUTES.PLAYERS}/${player.id}`}><img src={generatePlayerHeadshotPath(player)} className="player-icon" alt={`${article_player.description}`}/></a>)
            })}
        </>
    )
}


function getTeams(categories) {
    return (categories.filter(category => category.type == 'team'));
}

function getPlayers(categories) {
    return (categories.filter(category => category.type == "athlete"));
}

export default NewsDetailIcons;