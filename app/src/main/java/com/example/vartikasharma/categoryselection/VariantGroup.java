package com.example.vartikasharma.categoryselection;


import java.util.ArrayList;

public class VariantGroup {
    private int group_id;
    private String name;
    private ArrayList<Variation> variations = new ArrayList<>();

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Variation> getVariations() {
        return variations;
    }

    public void setVariations(ArrayList<Variation> variations) {
        this.variations = variations;
    }
}
