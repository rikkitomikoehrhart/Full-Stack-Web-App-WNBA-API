import { generatePlayerHeadshotPath } from "../../utils/playerUtils";
import TeamLogo from "../UI/TeamLogo";
import { usePlayers } from "../../hooks/usePlayers.js";
import Loading from "../UI/Loading";
import ErrorMessage from "../UI/ErrorMessage";



function PlayerGameStats({ scores }) {
    const { data: players, isLoading, error } = usePlayers();

    const home_top_players = scores.home_top_players
        .filter(top_player => {
            const found = players?.find(p => p.id == top_player.playerID);
            return found !== undefined;
        })
        .filter((player, index, array) => 
            array.findIndex(p => p.playerID === player.playerID) === index
        );
    
    const away_top_players = scores.away_top_players
        .filter(top_player => {
            const found = players?.find(p => p.id == top_player.playerID);
            return found !== undefined;
        })
        .filter((player, index, array) => 
            array.findIndex(p => p.playerID === player.playerID) === index
        );


    if (isLoading) {
        return <Loading />
    }


    if (error) {
        return <ErrorMessage message={error.message} title="Error Loading Players..." />
    }


 // Helper function to render player accordion items
    const renderPlayerAccordion = (playersArray, teamType, accordionId) => {
        return playersArray.map((player) => {
            const foundPlayer = players.find(p => p.id == player.playerID);
            const itemId = `${accordionId}-${player.playerID}`;
            
            return (
                <div key={player.playerID} className="accordion-item">
                    <h2 className="accordion-header">
                        <button 
                            className="accordion-button collapsed" 
                            type="button" 
                            data-bs-toggle="collapse" 
                            data-bs-target={`#${itemId}`}
                            aria-expanded="false" 
                            aria-controls={itemId}
                        >
                            <img 
                                src={generatePlayerHeadshotPath(foundPlayer)} 
                                alt={`${foundPlayer.first_name} ${foundPlayer.last_name}`}
                                className="logo-favorite me-3" 
                                style={{ width: 'auto', height: '90px'}}
                            />
                            <div>
                                <p className="text-uppercase" style={{letterSpacing: '0.1em'}}>{foundPlayer.first_name} {foundPlayer.last_name} </p>
                                <div className="text-muted small">
                                    {foundPlayer.position} • #{foundPlayer.jersey_number} • {foundPlayer.team.alias}
                                </div>
                            </div>
                        </button>
                    </h2>
                    <div 
                        id={itemId} 
                        className="accordion-collapse collapse" 
                        data-bs-parent={`#${accordionId}`}
                    >
                        <div className="accordion-body">
                            <div className="row">
                                <p><strong>Minutes:</strong> {player.minutesPlayed || 0}</p>
                            </div>
                            <div className="row">
                                <div className="col-md-6">
                                    <h5>Game Stats</h5>
                                    <ul className="list-unstyled">
                                        <li><strong>Points:</strong> {player.points || 0}</li>
                                        <li><strong>Rebounds:</strong> {player.offensiveRebounds + player.defensiveRebounds || 0}</li>
                                        <li><strong>Assists:</strong> {player.assists || 0}</li>
                                        <li><strong>Turnovers:</strong> {player.turnovers || 0}</li>
                                        <li><strong>Steals:</strong> {player.steals || 0}</li>
                                        <li><strong>Blocks:</strong> {player.blocks || 0}</li>
                                    </ul>  
                                </div>
                                <div className="col-md-6">
                                    <h5>Shooting</h5>
                                    <ul className="list-unstyled">
                                        <li><strong>FG:</strong> {player.fieldGoalsMade || 0} ({player.fieldGoalPercentage || 0.00} %)</li>
                                        <li><strong>3PT:</strong> {player.threePointsMade || 0} ({player.threePointPercentage || 0.00} %)</li>
                                        <li><strong>2PT:</strong> {player.twoPointsMade || 0} ({player.twoPointPercentage || 0.00} %)</li>
                                        <li><strong>FT:</strong> {player.freeThrowsMade || 0} ({player.freeThrowPercentage || 0.00} %)</li>
                                        <li><strong>Fouls:</strong> {player.personalFouls + player.techFouls + player.flagrant_fouls || 0}</li>
                                    
                                        <li><strong>Fouls Drawn:</strong> {player.foulsDrawn || 0}</li>
                                        

                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            );
        });
    };

    return (
        <div className="container mt-4">
            {/* Home Team Players */}
            {home_top_players.length > 0 && (
                <div className="mb-4">
                    <h4 className="mt-5 mb-3">
                        Top Players
                    </h4>
                    <div className="accordion" id="homePlayersAccordion">
                        {renderPlayerAccordion(home_top_players, 'home', 'homePlayersAccordion')}
                        {renderPlayerAccordion(away_top_players, 'away', 'awayPlayersAccordion')}
                    </div>
                </div>
            )}

        

            {home_top_players.length === 0 && away_top_players.length === 0 && (
                <div className="alert alert-info">
                    <i className="fas fa-info-circle me-2"></i>
                    No player statistics available for this game.
                </div>
            )}
        </div>
    );
}

export default PlayerGameStats;