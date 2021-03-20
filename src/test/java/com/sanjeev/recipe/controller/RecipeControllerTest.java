package com.sanjeev.recipe.controller;

import com.sanjeev.recipe.model.Recipe;
import com.sanjeev.recipe.service.RecipeOperationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RecipeControllerTest {

    @Mock
    private RecipeController recipeController;

    @Mock
    private RecipeOperationService recipeOperationService;

    @Test
    public void getLoadHomePage_withValidInput_returnTrue(){
        Model model = new ExtendedModelMap();
        String endUrl = "home.jsp";
        model.addAttribute("url",endUrl);
        when(recipeController.getLoggedInUserName()).thenReturn("sanjeev");
        assertTrue(model.containsAttribute("url"));
    }

    @Test
    public void getLoadHomePage_withInValidInput_returnFalse(){
        Model model = new ExtendedModelMap();
        assertFalse(model.containsAttribute("home.jsp"));
    }

    @Test
    public void getAllRecipe_withValidInput_returnResult(){
        List<Recipe> recipeList = getRecipeList();
        when(recipeOperationService.listAll()).thenReturn(recipeList);
        Model model = new ExtendedModelMap();
        model.addAttribute("recipeList",recipeList);
        assertTrue(model.containsAttribute("recipeList"));
        assertThat(recipeController.getAllRecipe(model)).isNull();
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