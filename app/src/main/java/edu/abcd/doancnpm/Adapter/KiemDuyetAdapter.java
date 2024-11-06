package edu.abcd.doancnpm.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;

import edu.abcd.doancnpm.Activity.ChitietNewsActivity;
import edu.abcd.doancnpm.Model.Approved;
import edu.abcd.doancnpm.Model.News;
import edu.abcd.doancnpm.R;

public class KiemDuyetAdapter extends RecyclerView.Adapter<KiemDuyetAdapter.ViewHolder> {

    Context context;
    List<News> list;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    DatabaseReference reference1;


    public KiemDuyetAdapter(Context context, List<News> list) {
        this.context = context;
        this.list = list;
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("News");
        reference1 = database.getReference("Approved");
        firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public KiemDuyetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_kiemduyet,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KiemDuyetAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        News news = list.get(position);
        holder.title.setText(news.getTitle());
        holder.content.setText(news.getContent());
        Glide.with(context).load(list.get(position).getImage()).into(holder.anh);
        holder.category.setText(news.getCategory());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChitietNewsActivity.class);
                intent.putExtra("chitietnews",list.get(position));
                context.startActivity(intent);
            }
        });
        holder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference1.getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        list.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Đã từ chối", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        holder.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> map =new HashMap<>();
                map.put("title",news.getTitle());
                map.put("image",news.getImage());
                map.put("content",news.getContent());
                map.put("category",news.getCategory());

                reference.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, "Đã duyệt", Toast.LENGTH_SHORT).show();
                    }
                });
                reference1.getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        list.remove(position);
                        notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView anh,yes,no;
        TextView title,content,category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.kiemduyet_title);
            content = itemView.findViewById(R.id.kiemduyet_content);
            anh = itemView.findViewById(R.id.kiemduyet_anh);
            category = itemView.findViewById(R.id.kiemduyet_category);
            yes = itemView.findViewById(R.id.img_yes);
            no = itemView.findViewById(R.id.img_no);
        }
    }
}
