package hu.hubasky.gastromanager.control.impl.firebase;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;
import hu.hubasky.gastromanager.entity.recept.EReceptStatus;
import hu.hubasky.gastromanager.entity.recept.Hozzavalo;
import hu.hubasky.gastromanager.entity.recept.Recept;

public class FirebaseReciepe {

    private String userId;
    private HashMap<String, Double> ingredientQuantities;
    // TODO: ajánlott étkezések implementálása
    // TODO: recept státusz implementálása
    private String state;
    private String name;
    private String description;
    // TODO: kép implementálása
    private String imgURL;
    private double portion;

    public FirebaseReciepe() {
    }

    public FirebaseReciepe(Recept reciepe) {
//        this.userId = reciepe.getTulajdonos().getUniqueKey();
        this.state = "PUBLIKUS";
        this.name = reciepe.getNeve();
        this.description = reciepe.getLeirasa();
        this.imgURL = reciepe.getFenykepeURL();
        this.portion = reciepe.getAdag();
        this.ingredientQuantities = new HashMap<String, Double>();
        for (Hozzavalo i : reciepe.getHozzavalok()) {
            this.ingredientQuantities.put(i.getAlapanyag().getUniqueKey(), i.getMennyiseg());
        }
    }

    public Recept convertToReciepe(String reciepeId, Felhasznalo user) {
        Recept result = new Recept(
//                user,
                EReceptStatus.PUBLIKUS,
                name,
                description,
                imgURL, // Image URL
                portion);
        result.setUniqueKey(reciepeId);
        if (ingredientQuantities != null) {
            for (String key : ingredientQuantities.keySet()) {
                Hozzavalo ingredient;
                for (Alapanyag i : FirebaseAccess.getInstance().getIngredients()) {
                    if (key.equals(i.getUniqueKey())) {
                        ingredient = new Hozzavalo(ingredientQuantities.get(key), i);
                        result.addHozzavalo(ingredient);
                        break;
                    }
                }
            }
        }
        return result;
    }

    public void updateReciepe(Recept reciepe) {
        reciepe.setNeve(name);
        reciepe.setAdag(portion);
        reciepe.setFenykepeURL(imgURL);
        reciepe.setLeirasa(description);
        reciepe.setStatus(EReceptStatus.PUBLIKUS); // TODO: recept státusz implementálása

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public HashMap<String, Double> getIngredientQuantities() {
        return ingredientQuantities;
    }

    public void setIngredientQuantities(HashMap<String, Double> ingredientQuantities) {
        this.ingredientQuantities = ingredientQuantities;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public double getPortion() {
        return portion;
    }

    public void setPortion(double portion) {
        this.portion = portion;
    }
}
