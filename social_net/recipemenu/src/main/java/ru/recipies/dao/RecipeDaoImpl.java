package ru.recipies.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.recipies.entity.Recipe;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class RecipeDaoImpl implements RecipeDao {
    private DataSource dataSource;

    @Autowired
    public RecipeDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<Recipe> getAll() {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM recipe WHERE deleted = FALSE", new RecipeRowMapper());
    }

    @Override
    public List<Recipe> getByName(String s) {
        return getAll()
                .stream()
                .filter(recipe -> recipe.getName().equals(s))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(int id) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("UPDATE RECIPE r SET r.DELETED = TRUE where r.ID = ?", id);
        return true;
    }

    private class RecipeRowMapper implements RowMapper<Recipe> {
        public Recipe mapRow(ResultSet resultSet, int i) throws SQLException {
            Recipe recipe = new Recipe();
            recipe.setId(resultSet.getInt("id"));
            recipe.setName(resultSet.getString("name"));
            recipe.setDate(resultSet.getDate("creation"));
            recipe.setDeleted(resultSet.getBoolean("deleted"));
            return recipe;
        }
    }
}
