package com.rikkitomikoehrhart.league_standard.data.mappers;

import com.rikkitomikoehrhart.league_standard.model.Team;
import com.rikkitomikoehrhart.league_standard.model.UserFavoriteTeams;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;


public class UserFavoriteTeamsMapper {

    public RowMapper<UserFavoriteTeams> userFavoriteTeamsRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            UserFavoriteTeams userFavoriteTeams = new UserFavoriteTeams();

            userFavoriteTeams.setId(rs.getString("user_favorite_teams_id"));

            Team team = new Team();
            team.setId(rs.getString("team_id"));
            team.setAlias(rs.getString("alias"));
            team.setMarket(rs.getString("market"));
            team.setName(rs.getString("name"));
            team.setYearFounded(rs.getInt("year_founded"));
            team.setMascot(rs.getString("mascot"));
            team.setOwner(rs.getString("owner"));
            userFavoriteTeams.setTeam(team);

            return userFavoriteTeams;
        };

    }
}
