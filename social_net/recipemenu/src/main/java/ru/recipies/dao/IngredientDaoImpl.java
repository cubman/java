package ru.recipies.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class IngredientDaoImpl implements IngredientDao {

    private DataSource dataSource;

    @Autowired
    public IngredientDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
