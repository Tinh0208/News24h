package edu.abcd.doancnpm.Fragment.Admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import edu.abcd.doancnpm.Adapter.KiemDuyetAdapter;
import edu.abcd.doancnpm.Adapter.TintucAdapter;
import edu.abcd.doancnpm.Model.Approved;
import edu.abcd.doancnpm.Model.LuuNews;
import edu.abcd.doancnpm.Model.News;
import edu.abcd.doancnpm.R;

public class KiemduyetFragment extends Fragment {

    RecyclerView recyclerView;
    List<News> list;
    DatabaseReference reference;
    FirebaseDatabase database;
    KiemDuyetAdapter adapter;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public KiemduyetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kiemduyet, container, false);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Approved");
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = view.findViewById(R.id.rcv_kiemduyet);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        list = new ArrayList<>();
        adapter = new KiemDuyetAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);


        reference.addValueEventListener(new ValueEventListener() {
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

        return view;    }
}