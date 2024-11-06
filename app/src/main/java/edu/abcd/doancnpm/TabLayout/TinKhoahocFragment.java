package edu.abcd.doancnpm.TabLayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.abcd.doancnpm.Adapter.TintucAdapter;
import edu.abcd.doancnpm.Model.News;
import edu.abcd.doancnpm.R;


public class    TinKhoahocFragment extends Fragment {

    RecyclerView recyclerView;
    List<News> list;
    DatabaseReference reference;
    FirebaseDatabase database;
    TintucAdapter adapter;
    public TinKhoahocFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tin_khoahoc, container, false);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("News");

        recyclerView = view.findViewById(R.id.rcv_tinkhoahoc);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));


        list = new ArrayList<>();
        adapter = new TintucAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        reference.orderByChild("category").equalTo("khoahoc").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    News news = dataSnapshot.getValue(News.class);
                    list.add(news);

                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }
}