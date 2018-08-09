package ru.recipies.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.recipies.entity.Recipe;
import ru.recipies.entity.RecipeIngredient;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class RecipeIngredioentDaoImpl implements RecipeIngredioentDao {

    private DataSource dataSource;

    @Autowired
    public RecipeIngredioentDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<RecipeIngredient> getAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("select * from  recipeingredient where deleted = false", new RecipeIngredientRowMapper());
    }

    @Override
    public void add(int recipeId, int ingredientId, float amount) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String request = String.format("INSERT INTO RECIPE_INGREDIENT(RECIPE_ID, INGREDIENT_ID, AMOUNT) VALUES (%d, %d, %f)", recipeId, ingredientId, amount);
        jdbcTemplate.execute(request);
    }

    @Override
    public void deleteRecipe(int recipeId) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("UPDATE RECIPE_INGREDIENT SET deleted = true where RECIPE_ID = ?", recipeId);
    }

    private class RecipeIngredientRowMapper implements RowMapper<RecipeIngredient> {
        public RecipeIngredient mapRow(ResultSet resultSet, int i) throws SQLException {
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setId(resultSet.getInt("id"));
            recipeIngredient.setRecipeId(resultSet.getInt("recipe_id"));
            recipeIngredient.setIngredientId(resultSet.getInt("ingredient_id"));
            recipeIngredient.setAmount(resultSet.getFloat("amount"));
            recipeIngredient.setDeleted(resultSet.getBoolean("deleted"));
            return recipeIngredient;
        }
    }
}
