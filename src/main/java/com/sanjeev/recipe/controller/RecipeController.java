package com.sanjeev.recipe.controller;

import com.sanjeev.recipe.model.Recipe;
import com.sanjeev.recipe.service.RecipeOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RecipeController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeOperationService recipeOperationService;

    /*This is RecipeController class which handles all the requests.
    /loadHomePage : This method handles "/" requests
    /@param : Model
    /Method : GET
    */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadHomePage(Model model) {
        model.addAttribute("name", getLoggedInUserName());
        logger.debug("model::"+model);
        return "home";
    }

    /*getAllRecipe : This loads all the list of recipe requests
    /@param : Model
    /Method : GET
     */
    @RequestMapping("/manageRecipe")
    public String getAllRecipe(Model model){
        List<Recipe> recipesList = recipeOperationService.listAll();
        model.addAttribute("recipesList", changeDateFormat(recipesList));
        logger.debug("recipesList::"+recipesList);
        return "displayRecipe";
    }

    /*addRecipe : This method loads the add recipe page
    /@param : Model
    /Method : Get
     */
    @RequestMapping(value = "/addRecipe", method = RequestMethod.GET)
    public String addRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        logger.debug("model::"+model);
        return "addRecipe";
    }

    /*addRecipe : This method insert recipe to DB
    /@param : Recipe,BindingResult
    /Method : POST
     */
    @RequestMapping(value = "/addRecipe", method = RequestMethod.POST)
    public String addRecipe(Recipe recipe, BindingResult result) {

        if (result.hasErrors()) {
            return "addRecipe";
        }
        recipe.setCreationDate(Timestamp.from(Instant.now()).toString());
        recipe.setUpdatedBy(getLoggedInUserName());

        logger.debug("recipe::"+recipe);
        logger.debug("updated by::"+recipe.getUpdatedBy());

        recipeOperationService.save(recipe);

        return "redirect:/manageRecipe";
    }

    /*updateRecipe : This method loads the modify recipe page
    /@param : Model,Id
    /Method : GET
     */
    @RequestMapping(value = "/updateRecipe", method = RequestMethod.GET)
    public String updateRecipe(@RequestParam int id, Model model) {
        Recipe recipe = recipeOperationService.get(id);
        model.addAttribute("recipe", recipe);
        return "addRecipe";
    }

    /*updateRecipe : This method is to update the recipe into DB.
    /@param : Recipe,BindingResult
    /Method : POST
     */
    @RequestMapping(value = "/updateRecipe", method = RequestMethod.POST)
    public String updateRecipe(Recipe recipe, BindingResult result) {

        if (result.hasErrors()) {
            return "addRecipe";
        }
        recipe.setCreationDate(Timestamp.from(Instant.now()).toString());
        recipe.setUpdatedBy(getLoggedInUserName());
        logger.debug("recipe::"+recipe);
        logger.debug("updated by::"+recipe.getUpdatedBy());
        logger.debug("updation time by::"+recipe.getCreationDate());
        recipeOperationService.save(recipe);
        return "redirect:/manageRecipe";
    }

    /*deleteRecipe : This deletes the recipe from DB
    /@param : id
   //Method : GET
     */
    @RequestMapping(value = "/deleteRecipe", method = RequestMethod.GET)
    public String deleteRecipe(@RequestParam int id) {
        recipeOperationService.delete(id);
        logger.debug("recipe delete::"+id);

        return "redirect:/manageRecipe";
    }

     /*getLoggedInUserName : This method is to fetch logged in user
     */
    public String getLoggedInUserName() {
        Object userLogged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.debug("userLogged::"+userLogged);

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
        logger.debug("User logout:::"+authentication.getPrincipal());

        return "redirect:/user";
    }

    public List<Recipe> changeDateFormat(List<Recipe> recipeList) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        DateTimeFormatter newDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for (Recipe recipe : recipeList) {
            LocalDateTime localDate = LocalDateTime.parse(recipe.getCreationDate(), dateFormatter);
            recipe.setCreationDate(newDateFormatter.format(localDate));
            if(recipe.getVegIndicator().equalsIgnoreCase("0"))
                recipe.setVegIndicator("Veg");
            else
                recipe.setVegIndicator("Non-Veg");
        }
        return recipeList;
    }
}