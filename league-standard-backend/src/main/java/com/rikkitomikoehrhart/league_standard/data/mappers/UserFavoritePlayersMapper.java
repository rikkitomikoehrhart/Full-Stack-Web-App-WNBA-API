package com.rikkitomikoehrhart.league_standard.data.mappers;

import com.rikkitomikoehrhart.league_standard.model.Player;
import com.rikkitomikoehrhart.league_standard.model.Team;
import com.rikkitomikoehrhart.league_standard.model.UserFavoritePlayer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class UserFavoritePlayersMapper {
    public RowMapper<UserFavoritePlayer> userFavoritePlayersRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            UserFavoritePlayer favoritePlayer = new UserFavoritePlayer();

            favoritePlayer.setId(rs.getString("id"));

            Player player = new Player();
            player.setId(rs.getString("player_id"));
            player.setStatus(rs.getString("status"));
            player.setFirst_name(rs.getString("first_name"));
            player.setLast_name(rs.getString("last_name"));
            player.setHeight(rs.getInt("height"));
            player.setPosition(rs.getString("position"));
            player.setJersey_number(rs.getInt("jersey_number"));
            player.setExperience(rs.getInt("experience"));
            player.setCollege(rs.getString("college"));
            player.setBirth_place(rs.getString("birth_place"));
            player.setBirthdate(rs.getDate("birthdate").toLocalDate());
            player.setRookie_year(rs.getInt("rookie_year"));
            player.setDraft_round(rs.getInt("draft_round"));
            player.setDraft_pick(rs.getInt("draft_pick"));


            Team team = new Team();
            team.setId(rs.getString("team_id"));
            team.setAlias(rs.getString("alias"));
            team.setMarket(rs.getString("market"));
            team.setName(rs.getString("name"));
            team.setYearFounded(rs.getInt("year_founded"));
            team.setMascot(rs.getString("mascot"));
            team.setOwner(rs.getString("owner"));
            player.setTeam(team);

            favoritePlayer.setPlayer(player);

            return favoritePlayer;
        };

    }
}
