package hu.hubasky.gastromanager.control.impl.firebase;

import java.util.ArrayList;
import java.util.List;

import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.bevlist.BevasarloLista;
import hu.hubasky.gastromanager.entity.recept.Hozzavalo;
import hu.hubasky.gastromanager.entity.recept.Recept;

public class FirebaseAccess {

    private static FirebaseAccess instance = null;

    private List<Alapanyag> ingredients;
    private List<Hozzavalo> ingredientQuantities;
    private List<Recept> reciepes;
    private List<BevasarloLista> shoppingLists;

    public FirebaseAccess() {
        ingredients = new ArrayList<>();
        ingredientQuantities = new ArrayList<>();
        reciepes = new ArrayList<>();
        shoppingLists = new ArrayList<>();
    }

    public List<Alapanyag> getIngredients() {
        return ingredients;
    }

    public List<Hozzavalo> getIngredientQuantities() {
        return ingredientQuantities;
    }

    public List<Recept> getReciepes() {
        return reciepes;
    }

    public List<BevasarloLista> getShoppingLists() {
        return shoppingLists;
    }

    public static FirebaseAccess getInstance() {
        if (FirebaseAccess.instance == null) {
            FirebaseAccess.instance = new FirebaseAccess();
        }
        return FirebaseAccess.instance;
    }

}
