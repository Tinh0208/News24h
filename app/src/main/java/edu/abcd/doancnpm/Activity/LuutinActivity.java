package edu.abcd.doancnpm.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import edu.abcd.doancnpm.Adapter.LuuTinAdapter;
import edu.abcd.doancnpm.Fragment.User.CanhanFragment;
import edu.abcd.doancnpm.Model.LuuNews;
import edu.abcd.doancnpm.Model.User;
import edu.abcd.doancnpm.R;

public class LuutinActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LuuTinAdapter adapter;
    List<LuuNews> list;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseFirestore firestore;
    DatabaseReference myRef;
    Toolbar toolbar;
    FirebaseAuth auth;

    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luutin);
        auth = FirebaseAuth.getInstance();
        searchView = findViewById(R.id.search);

        searchView.clearFocus();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("LuuTin");
        firestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.rcv_luutin);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new LuuTinAdapter(this, list);
        recyclerView.setAdapter(adapter);

        reference.child(auth.getCurrentUser().getUid()).getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    LuuNews news = dataSnapshot.getValue(LuuNews.class);
                    list.add(news);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return false;
            }
        });



        ImageView img_back = findViewById(R.id.img_back);
        myRef = database.getReference("Users");

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser fireUser = auth.getCurrentUser();
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                User user = dataSnapshot.getValue(User.class);
                                if(user.getEmail().equals(fireUser.getEmail())){
                                    if(user.getRole() == 0){
                                        startActivity(new Intent(LuutinActivity.this,MainActivity.class));
                                        Toast.makeText(LuutinActivity.this, "", Toast.LENGTH_SHORT).show();
                                    }else{
                                        startActivity(new Intent(LuutinActivity.this,MainActivity2.class));
                                        Toast.makeText(LuutinActivity.this, "", Toast.LENGTH_SHORT).show();
                                    }
                                    finish();
                                    return;
                                }
                            }
                        }
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
    public void searchList(String text){
        List<LuuNews> searchList = new ArrayList<>();
        for(LuuNews luuNews: list){
            if(luuNews.getTitle().toLowerCase().contains(text.toLowerCase())){
                searchList.add(luuNews);
            }
        }
        adapter.searchDataList(searchList);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Go back to the previous activity.
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

}