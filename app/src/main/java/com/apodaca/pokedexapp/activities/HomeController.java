package com.apodaca.pokedexapp.activities;


import android.widget.ImageView;
import android.widget.TextView;

import com.apodaca.pokedexapp.Methods;
import com.apodaca.pokedexapp.RetrofitClient;
import com.apodaca.pokedexapp.models.Pokemon;
import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeController {
    TextView mTextName;
    ImageView mImage;
    Pokemon pokemon;
    Response res;
    public HomeController(TextView mTextName,ImageView mImage) {
        this.mTextName = mTextName;
        this.mImage = mImage;
    }

    public  List<String> getPokemon(){
        List<String> data = new ArrayList();


        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        int idRandom = (int) Math.floor(Math.random()*(200-1+1)+1);
        Call<Pokemon> call = methods.getPokemon(Integer.toString(idRandom));
        call.enqueue(new Callback<Pokemon>() {

            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
//                mTextName.setText(response.body().getName());
//
//                Glide.with(cont).load(response.body().getSprites().getFront_default()).into(mImage);
               data.add(response.body().getName());
                System.out.println(data.get(0));

            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }

        });

    return  data;
    }
}
