package edu.abcd.doancnpm.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.abcd.doancnpm.Adapter.GopYAdapter;
import edu.abcd.doancnpm.Adapter.TintucAdapter;
import edu.abcd.doancnpm.Model.GopY;
import edu.abcd.doancnpm.Model.News;
import edu.abcd.doancnpm.R;

public class GopYActivity2 extends AppCompatActivity {
    RecyclerView recyclerView;
    List<GopY> list;
    DatabaseReference reference;
    FirebaseDatabase database;
    GopYAdapter adapter;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gop_yactivity2);

        back = findViewById(R.id.back_gopy);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("GopY");

        recyclerView = findViewById(R.id.rcv_gopyadmin);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));


        list = new ArrayList<>();
        adapter = new GopYAdapter(this,list);
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    GopY gopY = dataSnapshot.getValue(GopY.class);
                    list.add(gopY);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GopYActivity2.this,MainActivity2.class));
            }
        });
    }

}