package com.example.smart_immunization.Models;

public class Immunizations {
    private int id;
    private String immunizationName, immunizationDosageLevel, immunizationDate, nextImmunizationDate, administeredDate;

    public Immunizations() {
    }

    public Immunizations(int id, String immunizationName, String immunizationDosageLevel, String immunizationDate, String nextImmunizationDate, String administeredDate) {
        this.id = id;
        this.immunizationName = immunizationName;
        this.immunizationDosageLevel = immunizationDosageLevel;
        this.immunizationDate = immunizationDate;
        this.nextImmunizationDate = nextImmunizationDate;
        this.administeredDate = administeredDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImmunizationName() {
        return immunizationName;
    }

    public void setImmunizationName(String immunizationName) {
        this.immunizationName = immunizationName;
    }

    public String getImmunizationDosageLevel() {
        return immunizationDosageLevel;
    }

    public void setImmunizationDosageLevel(String immunizationDosageLevel) {
        this.immunizationDosageLevel = immunizationDosageLevel;
    }

    public String getImmunizationDate() {
        return immunizationDate;
    }

    public void setImmunizationDate(String immunizationDate) {
        this.immunizationDate = immunizationDate;
    }

    public String getNextImmunizationDate() {
        return nextImmunizationDate;
    }

    public void setNextImmunizationDate(String nextImmunizationDate) {
        this.nextImmunizationDate = nextImmunizationDate;
    }

    public String getAdministeredDate() {
        return administeredDate;
    }

    public void setAdministeredDate(String administeredDate) {
        this.administeredDate = administeredDate;
    }
}
