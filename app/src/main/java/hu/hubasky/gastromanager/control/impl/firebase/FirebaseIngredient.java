package hu.hubasky.gastromanager.control.impl.firebase;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagJellemzok;

@IgnoreExtraProperties
public class FirebaseIngredient {

    private String name;
    private String unit;
    private double unitInG;

    private double referenceG;
    private double proteinPercent;
    private double fatPercent;
    private double chPercent;
    private double energyPercent;

    public FirebaseIngredient() {
        unitInG = 1;
    }

    public FirebaseIngredient(Alapanyag ingredient) {

        name = ingredient.getNeve();
        unit = ingredient.getMennyisegiEgyseg().toString();
        unitInG = ingredient.getEgysegeGramm();

        referenceG = ingredient.getJellemzok().getVonatkoztatasGramm();
        proteinPercent = ingredient.getJellemzok().getFeherjeSzazalek();
        fatPercent = ingredient.getJellemzok().getZsirSzazalek();
        chPercent = ingredient.getJellemzok().getSzenhidratSzazalek();
        energyPercent = ingredient.getJellemzok().getEnergiaKJ();
    }

    public Alapanyag convertToIngredient(String uniqueKey) {
        Alapanyag result = new Alapanyag(
                convertToUnit(),
                new AlapanyagJellemzok(referenceG, proteinPercent, fatPercent, chPercent, energyPercent),
                name,
                unitInG
        );
        result.setUniqueKey(uniqueKey);
        return result;
    }

    private EMennyisegiEgyseg convertToUnit() {
        switch (this.unit) {
            case "GRAMM" : return EMennyisegiEgyseg.GRAMM;
            case "LITER" : return EMennyisegiEgyseg.LITER;
            case "DARAB" : return EMennyisegiEgyseg.DARAB;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getReferenceG() {
        return referenceG;
    }

    public void setReferenceG(double referenceG) {
        this.referenceG = referenceG;
    }

    public double getProteinPercent() {
        return proteinPercent;
    }

    public void setProteinPercent(double proteinPercent) {
        this.proteinPercent = proteinPercent;
    }

    public double getFatPercent() {
        return fatPercent;
    }

    public void setFatPercent(double fatPercent) {
        this.fatPercent = fatPercent;
    }

    public double getChPercent() {
        return chPercent;
    }

    public void setChPercent(double chPercent) {
        this.chPercent = chPercent;
    }

    public double getEnergyPercent() {
        return energyPercent;
    }

    public void setEnergyPercent(double energyPercent) {
        this.energyPercent = energyPercent;
    }

    public double getUnitInG() {
        return unitInG;
    }

    public void setUnitInG(double unitInG) {
        this.unitInG = unitInG;
    }
}
