package ru.recipies.dao;

import ru.recipies.entity.Recipe;

import java.util.List;

interface RecipeDao {

    List<Recipe> getAll();
    List<Recipe> getByName(String s);
    boolean delete(int id);
}
