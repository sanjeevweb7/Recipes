package com.sanjeev.recipe.model;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String recipeName;
    private int vegIndicator;
    private String personIndicator;
    private String cookingInstruction;
    private String ingredients;
    private Timestamp creationDate;
    private String updatedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getVegIndicator() {
        return vegIndicator;
    }

    public void setVegIndicator(int vegIndicator) {
        this.vegIndicator = vegIndicator;
    }

    public String getPersonIndicator() {
        return personIndicator;
    }

    public void setPersonIndicator(String personIndicator) {
        this.personIndicator = personIndicator;
    }

    public String getCookingInstruction() {
        return cookingInstruction;
    }

    public void setCookingInstruction(String cookingInstruction) {
        this.cookingInstruction = cookingInstruction;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
