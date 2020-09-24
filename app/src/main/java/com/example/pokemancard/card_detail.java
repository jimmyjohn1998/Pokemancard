package com.example.pokemancard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import static com.example.pokemancard.MainActivity.D_BODY;
import static com.example.pokemancard.MainActivity.D_NAME;
import static com.example.pokemancard.MainActivity.D_URL;
import static com.example.pokemancard.MainActivity.HEIGHT;
import static com.example.pokemancard.MainActivity.WEAKNESS;
import static com.example.pokemancard.MainActivity.WEIGHT;
import static com.example.pokemancard.MainActivity.CANDY;

public class card_detail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
       // this.setContentView(R.layout.activity_card_detail);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);




        Intent intent=getIntent();
        String img=intent.getStringExtra(D_URL);
        String name=intent.getStringExtra(D_NAME);
        String body=intent.getStringExtra(D_BODY);
        String height=intent.getStringExtra(HEIGHT);
        String weight=intent.getStringExtra(WEIGHT);
        String weakness=intent.getStringExtra(WEAKNESS);
        String candy=intent.getStringExtra(CANDY);
//
        ImageView imageView=findViewById(R.id.dimg);
        TextView textname=findViewById(R.id.dtitle);
        TextView textbody=findViewById(R.id.dbody);
        TextView textheight=findViewById(R.id.height);
        TextView textweight=findViewById(R.id.weight);
        TextView textcandy=findViewById(R.id.candy);
        TextView textweakness=findViewById(R.id.weakness);
//
        Picasso.get().load(img).fit().centerInside().into(imageView);
        textname.setText(name);
        textbody.setText(body);
       textcandy.setText(candy);
        textweakness.setText(weakness);
        textheight.setText("Height:"+height);
        textweight.setText("Weight:"+weight);
//



    }
}