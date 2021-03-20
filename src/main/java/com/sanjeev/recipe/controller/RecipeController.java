package com.sanjeev.recipe.controller;

import com.sanjeev.recipe.model.Recipe;
import com.sanjeev.recipe.service.RecipeOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Controller
public class RecipeController {

    @Autowired
    private RecipeOperationService recipeOperationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadHomePage(Model model) {
        model.addAttribute("name", getLoggedInUserName());
        return "home";
    }

    @RequestMapping("/manageRecipe")
    public String getAllRecipe(Model model){
        List<Recipe> recipesList = recipeOperationService.listAll();
        model.addAttribute("recipesList", recipesList);
        return "displayRecipe";
    }

    @RequestMapping("/recipe/{id}")
    public String getRecipe(@PathVariable Integer id,Model model){
        Recipe listRecipes = recipeOperationService.get(id);
        model.addAttribute("listRecipes", listRecipes);
        return "displayRecipe";
    }

    @RequestMapping(value = "/addRecipe", method = RequestMethod.GET)
    public String addRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "addRecipe";
    }

    @RequestMapping(value = "/addRecipe", method = RequestMethod.POST)
    public String addRecipe(Recipe recipe, BindingResult result) {

        if (result.hasErrors()) {
            return "addRecipe";
        }
        recipe.setCreationDate(Timestamp.from(Instant.now()));
        recipe.setUpdatedBy(getLoggedInUserName());
        recipeOperationService.save(recipe);
        return "redirect:/manageRecipe";
    }

    @RequestMapping(value = "/updateRecipe", method = RequestMethod.GET)
    public String updateRecipe(@RequestParam int id, Model model) {
        Recipe recipe = recipeOperationService.get(id);
        model.addAttribute("recipe", recipe);
        return "addRecipe";
    }

    @RequestMapping(value = "/updateRecipe", method = RequestMethod.POST)
    public String updateRecipe(Recipe recipe, BindingResult result) {

        if (result.hasErrors()) {
            return "addRecipe";
        }
        recipe.setCreationDate(Timestamp.from(Instant.now()));
        recipe.setUpdatedBy(getLoggedInUserName());
        recipeOperationService.save(recipe);
        return "redirect:/manageRecipe";
    }

    @RequestMapping(value = "/deleteRecipe", method = RequestMethod.GET)
    public String deleteRecipe(@RequestParam int id) {
        recipeOperationService.delete(id);
        return "redirect:/manageRecipe";
    }

    public String getLoggedInUserName() {
        Object userLogged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userLogged instanceof UserDetails) {
            return ((UserDetails) userLogged).getUsername();
        }
        return userLogged.toString();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response,
                    authentication);
        }
        return "redirect:/user";
    }
}