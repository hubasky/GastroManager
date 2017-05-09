package hu.hubasky.gastromanager.viewmodel;

import java.util.List;

/**
 * Created by Peet on 2017. 04. 27..
 */

public class ReciepeVM {

    private String name;
    private String description;
    private List<IngredientVM> ingredients;

    public ReciepeVM(String name, String description, List<IngredientVM> ingredients) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<IngredientVM> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientVM> ingredients) {
        this.ingredients = ingredients;
    }
}
