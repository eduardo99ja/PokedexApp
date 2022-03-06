package com.apodaca.pokedexapp;

import com.apodaca.pokedexapp.models.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Methods {
    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") String id);
}
