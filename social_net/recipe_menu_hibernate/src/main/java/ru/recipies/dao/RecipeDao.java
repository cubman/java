package ru.recipies.dao;

import entity.Recipe;

import java.util.List;

interface RecipeDao {

    List<Recipe> getAll();
    List<Recipe> getByName(String s);
    boolean delete(int id);
}
