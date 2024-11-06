package edu.abcd.doancnpm.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import edu.abcd.doancnpm.Fragment.Admin.CanhanAdminFragment;
import edu.abcd.doancnpm.Fragment.Admin.KiemduyetFragment;
import edu.abcd.doancnpm.Fragment.CalenderFragment;
import edu.abcd.doancnpm.Fragment.User.CanhanFragment;
import edu.abcd.doancnpm.Fragment.TrangchinhFragment;

import edu.abcd.doancnpm.R;

public class MainActivity2 extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    TrangchinhFragment trangchinhFragment = new TrangchinhFragment();
    CalenderFragment calenderFragment = new CalenderFragment();
    CanhanAdminFragment canhanFragment = new CanhanAdminFragment();
    KiemduyetFragment kiemduyetFragment = new KiemduyetFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bottomNavigationView = findViewById(R.id.bottom_nav_admin);
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
                    case R.id.kiemduyet:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, kiemduyetFragment).commit();
                        return true ;
                }
                return false;
            }
        });

    }

}