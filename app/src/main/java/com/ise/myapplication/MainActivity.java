package com.ise.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    ImageButton searchbutton;
    ChatFragment chatfragment;
    ProfileFragment profileFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatfragment = new ChatFragment();
        profileFragment= new ProfileFragment();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        searchbutton = findViewById(R.id.main_search_btn);

        searchbutton.setOnClickListener((v -> {
            startActivity(new Intent(MainActivity.this,SearchActivity.class));
        }));

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.menu_chat)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,chatfragment).commit();
                }
                if(item.getItemId()==R.id.menu_profile)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,profileFragment).commit();
                }
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.menu_chat);
    }
}