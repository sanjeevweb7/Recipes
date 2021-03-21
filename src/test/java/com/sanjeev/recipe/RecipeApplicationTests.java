package com.sanjeev.recipe;

import com.sanjeev.recipe.controller.RecipeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RecipeApplicationTests {

	@Autowired
	private RecipeController recipeController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(recipeController).isNotNull();
	}
}
