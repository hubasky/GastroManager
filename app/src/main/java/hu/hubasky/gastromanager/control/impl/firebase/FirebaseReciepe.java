package hu.hubasky.gastromanager.control.impl.firebase;

import java.util.HashMap;

import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;
import hu.hubasky.gastromanager.entity.recept.EReceptStatus;
import hu.hubasky.gastromanager.entity.recept.Hozzavalo;
import hu.hubasky.gastromanager.entity.recept.Recept;

public class FirebaseReciepe {

    private String userId;
    private HashMap<String, Double> ingredientQuantities;
    // TODO: ajánlott étkezések implementálása
    // TODO: recept státusz implementálása
    private String state = "PUBLIKUS";
    private String name;
    private String description;
    // TODO: kép implementálása
    private String imgURL = "";
    private double portion;


    public Recept convertToReciepe(String reciepeId, Felhasznalo user) {
        Recept result = new Recept(
                user,
                EReceptStatus.PUBLIKUS,
                name,
                description,
                imgURL, // Image URL
                portion);
        result.setUniqueKey(reciepeId);
        return result;
    }

    public void updateReciepe(Recept reciepe) {
        reciepe.setNeve(name);
        reciepe.setAdag(portion);
        reciepe.setFenykepeURL(imgURL);
        reciepe.setLeirasa(description);
        reciepe.setStatus(EReceptStatus.PUBLIKUS); // TODO: recept státusz implementálása

    }

}
