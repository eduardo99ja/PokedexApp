package com.apodaca.pokedexapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apodaca.pokedexapp.Methods;
import com.apodaca.pokedexapp.R;
import com.apodaca.pokedexapp.RetrofitClient;
import com.apodaca.pokedexapp.models.Pokemon;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    TextView mTextName, mTextBase, mTextPeso, mTextAltura;
    ImageView mImage;
    FloatingActionButton mButton;
    Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mTextName = findViewById(R.id.idName);
        mImage = findViewById(R.id.imageView);
        mTextBase = findViewById(R.id.textSubtitle);
        mTextPeso = findViewById(R.id.txtPeso);
        mTextAltura = findViewById(R.id.txtAltura);
        mButton = findViewById(R.id.fabRefresh);

        TooltipCompat.setTooltipText(mButton, "Mostrar Pokemón");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPokemon();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        t= new Timer();


        Timer t = new Timer();
        TimerTask task = new TimerTask(){
            public void run()
            {
                getPokemon();
            }
        };

        t.scheduleAtFixedRate(task,0,30000);

    }

    void getPokemon(){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        int idRandom = (int) Math.floor(Math.random()*(200-1+1)+1);
        Call<Pokemon> call = methods.getPokemon(Integer.toString(idRandom));
        call.enqueue(new Callback<Pokemon>() {

            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.body() != null){
                    mTextName.setText(response.body().getName());
                    mTextBase.setText("Experiencia base: "+ response.body().getBase_experience());
                    mTextPeso.setText(String.valueOf( Double.parseDouble(response.body().getWeight())/10));
                    mTextAltura.setText(String.valueOf(Double.parseDouble(response.body().getHeight())/10));
                }

                Glide.with(HomeActivity.this).load(response.body().getSprites().getFront_default()).into(mImage);
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        t.cancel();
    }



}