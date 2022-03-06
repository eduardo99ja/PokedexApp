package com.apodaca.pokedexapp.models;

public class Sprites {
    private String front_default;


    public String getFront_default() {
        return front_default;
    }

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }

    @Override
    public String toString() {
        return "Sprites{" +
                "frontDefault='" + front_default + '\'' +
                '}';
    }
}
