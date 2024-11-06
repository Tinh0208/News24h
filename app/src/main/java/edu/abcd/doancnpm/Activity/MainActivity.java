package edu.abcd.doancnpm.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import edu.abcd.doancnpm.Fragment.User.CanhanFragment;
import edu.abcd.doancnpm.Fragment.TrangchinhFragment;
import edu.abcd.doancnpm.Fragment.CalenderFragment;
import edu.abcd.doancnpm.R;
import edu.abcd.doancnpm.glide.photo;
import edu.abcd.doancnpm.glide.photoapdapter;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {



    private BottomNavigationView bottomNavigationView;
    TrangchinhFragment trangchinhFragment = new TrangchinhFragment();
    CalenderFragment calenderFragment = new CalenderFragment();
    CanhanFragment canhanFragment = new CanhanFragment();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        bottomNavigationView = findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, trangchinhFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.trangchinh:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, trangchinhFragment).commit();
                        return true ;
                    case R.id.video:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, calenderFragment).commit();
                        return true ;
                    case R.id.canhan:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, canhanFragment).commit();
                        return true ;
                }
                return false;
            }
        });

    }
}