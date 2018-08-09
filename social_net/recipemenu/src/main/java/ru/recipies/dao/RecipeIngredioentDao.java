package ru.recipies.dao;

import ru.recipies.entity.RecipeIngredient;

import java.util.List;

public interface RecipeIngredioentDao {
    List<RecipeIngredient> getAll();
    void add(int recipeId, int ingredientId, float amount);
    void deleteRecipe(int recipeId);
}
