package com.example.pokemancard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.app.SearchManager;
import android.widget.SearchView.OnQueryTextListener;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.OnItemClickListener {//
    public static final String D_URL="img";//d for detail
    public static final String D_NAME="name";
    public static final String D_BODY="type";
    public static final String HEIGHT="height";
    public static final String WEIGHT="weight";
    public static final String CANDY="candy";
    public static final String WEAKNESS="weaknesses";


    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<CardDisplay> cardDisplays;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cardDisplays=new ArrayList<>();
        requestQueue= Volley.newRequestQueue(this);
        parseJSON();


    }



    private void parseJSON(){
        String url="https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("pokemon");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject pokemon=jsonArray.getJSONObject(i);

                                String title=pokemon.getString("name");
                                String imageUrl=pokemon.getString("img");
                                String body=pokemon.getString("type");
                               String candy=pokemon.getString("candy");
                                String height=pokemon.getString("height");
                                String weight=pokemon.getString("weight");
                               String weakness=pokemon.getString("weaknesses");


                                cardDisplays.add(new CardDisplay(imageUrl,title,body,candy,weakness,height,weight));//,candy,height,weakness,weight
                            }
                            adapter=new Adapter(MainActivity.this,cardDisplays);
                            GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,2,GridLayoutManager.VERTICAL,false);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(MainActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        requestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(this,card_detail.class);
        CardDisplay clickedItem=cardDisplays.get(position);
        intent.putExtra(D_URL,clickedItem.getmImgUrl());
        intent.putExtra(D_NAME,clickedItem.getmName());
        intent.putExtra(D_BODY,clickedItem.getmBody());
        intent.putExtra(HEIGHT,clickedItem.getmHeight());
        intent.putExtra(WEIGHT,clickedItem.getmWeight());
       intent.putExtra(WEAKNESS,clickedItem.getmWeakness());
       intent.putExtra(CANDY,clickedItem.getmCandy());
//
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        MenuItem searchitem=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)searchitem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               adapter.getFilter().filter(newText);
                return true;

            }
        });
        return true;
    }


}