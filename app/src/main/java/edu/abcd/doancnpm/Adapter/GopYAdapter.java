package edu.abcd.doancnpm.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;

import edu.abcd.doancnpm.Activity.ChitietGopyActivity;
import edu.abcd.doancnpm.Model.GopY;
import edu.abcd.doancnpm.R;

public class GopYAdapter extends RecyclerView.Adapter<GopYAdapter.ViewHolder> {
    Context context;
    List<GopY> list;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;

    public GopYAdapter(Context context, List<GopY> list) {
        this.context = context;
        this.list = list;
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("GopY");
    }

    @NonNull
    @Override
    public GopYAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gop,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GopYAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        GopY gopY = list.get(position);
        holder.ten.setText(gopY.getTen());
        holder.noidung.setText(gopY.getNoidung());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChitietGopyActivity.class);
                intent.putExtra("chitietgopy",list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ten,noidung;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ten = itemView.findViewById(R.id.itemgopy_ten);
            noidung = itemView.findViewById(R.id.itemgopy_noidung);
        }
    }
}
