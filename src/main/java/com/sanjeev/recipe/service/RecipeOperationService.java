package com.sanjeev.recipe.service;

import com.sanjeev.recipe.model.Recipe;
import com.sanjeev.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RecipeOperationService {

    @Autowired
    private RecipeRepository repo;

    public List<Recipe> listAll() {
        return repo.findAll();
    }

    public void save(Recipe recipe) {
        repo.save(recipe);
    }

    public Recipe get(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}