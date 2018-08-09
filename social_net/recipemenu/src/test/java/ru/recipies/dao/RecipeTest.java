package ru.recipies.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.recipies.config.TestConfig;
import ru.recipies.entity.Recipe;

import java.util.List;

@ContextConfiguration(classes = TestConfig.class)
public class RecipeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RecipeDaoImpl recipeDao;

    @Test
    public void testFindRecipe() {
        List<Recipe> recipeList = recipeDao.getByName("вермишель");
        Assert.assertNotNull(recipeList);
        Assert.assertTrue(recipeList.size() > 0);
    }

    @Test
    public void removeRecipe() {
        List<Recipe> recipeList = recipeDao.getByName("вермишель");
        Assert.assertNotNull(recipeList);
        Assert.assertTrue(recipeList.size() == 1 && "вермишель".equals(recipeList.get(0).getName()));

        recipeDao.delete(2);

        recipeList = recipeDao.getByName("вермишель");
        Assert.assertNotNull(recipeList);
        Assert.assertTrue(recipeList.size() == 0 && "вермишель".equals(recipeList.get(0).getName()));
    }
}