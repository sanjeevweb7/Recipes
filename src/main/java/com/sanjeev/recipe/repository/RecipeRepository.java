package com.sanjeev.recipe.repository;

import com.sanjeev.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe,Integer> {

}
