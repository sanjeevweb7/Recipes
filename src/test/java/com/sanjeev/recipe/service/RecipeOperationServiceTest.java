package com.sanjeev.recipe.service;

import com.sanjeev.recipe.model.Recipe;
import com.sanjeev.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RecipeOperationServiceTest {

    @Mock
    private RecipeOperationService recipeOperationService;

    @Mock
    private RecipeRepository repo;

    @Test
    public void testGetListAll_returnResult(){
        when(repo.findAll()).thenReturn(getRecipeList());
        assertNotNull(recipeOperationService.listAll());
    }

    @Test
    public void testGetListAll_returnNoResult(){
        when(repo.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0,recipeOperationService.listAll().size());
    }

    @Test
    public void testGet_returnResult(){
        Recipe recipe = new Recipe();
        recipe.setRecipeName("Matar Paneer");
        when(repo.findById(1)).thenReturn(null);
        assertEquals(null,recipeOperationService.get(1));
    }

    @Test
    public void testGet_returnNoResult(){
        assertNull(recipeOperationService.get(1));
    }

    private List<Recipe> getRecipeList(){
        List<Recipe> list = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setUpdatedBy("Sanjeev");
        recipe.setId(1);
        recipe.setRecipeName("Kadhai Paneer");
        list.add(recipe);

        return list;
    }
}