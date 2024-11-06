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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import edu.abcd.doancnpm.Activity.ChitietNewsActivity;
import edu.abcd.doancnpm.Model.News;
import edu.abcd.doancnpm.R;

public class TintucAdapter extends RecyclerView.Adapter<TintucAdapter.ViewHolder> {

    Context context;
    List<News> list;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    public TintucAdapter(Context context, List<News> list) {
        this.context = context;
        this.list = list;
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("LuuTin");
        firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public TintucAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TintucAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        News news = list.get(position);
        holder.title.setText(news.getTitle());
        holder.content.setText(news.getContent());
        Glide.with(context).load(list.get(position).getImage()).into(holder.anh);
        holder.category.setText(news.getCategory());

        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> map =new HashMap<>();
                map.put("title",news.getTitle());
                map.put("image",news.getImage());
                map.put("content",news.getContent());
                map.put("category",news.getCategory());

                reference.child(auth.getCurrentUser().getUid()).push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, "Đã lưu", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChitietNewsActivity.class);
                intent.putExtra("chitietnews",list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView anh,save;
        TextView title,content,category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_NewsTitle);
            content = itemView.findViewById(R.id.txt_NewsContent);
            anh = itemView.findViewById(R.id.imgNews);
            save = itemView.findViewById(R.id.img_save);
            category = itemView.findViewById(R.id.txt_NewsCategory);
        }
    }
}
