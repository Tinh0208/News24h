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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import edu.abcd.doancnpm.Activity.ChitietNewsActivity;
import edu.abcd.doancnpm.Model.LuuNews;
import edu.abcd.doancnpm.Model.News;
import edu.abcd.doancnpm.R;

public class LuuTinAdapter extends RecyclerView.Adapter<LuuTinAdapter.ViewHolder> {

    Context context;
    List<LuuNews> list;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;

    public LuuTinAdapter(Context context, List<LuuNews> list) {
        this.context = context;
        this.list = list;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("LuuTin");
    }

    @NonNull
    @Override
    public LuuTinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_luutin,parent,false));
    }



    @Override
    public void onBindViewHolder(@NonNull LuuTinAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.content.setText(list.get(position).getTitle());
        holder.category.setText(list.get(position).getCategory());
        Glide.with(context).load(list.get(position).getImage()).into(holder.anh);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChitietNewsActivity.class);
                intent.putExtra("chitietnews",list.get(position));
                context.startActivity(intent);
            }
        });
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child(auth.getCurrentUser().getUid()).getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        list.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Đã hủy lưu", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void searchDataList(List<LuuNews> searchList){
        list = searchList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView anh,xoa;
        TextView title,content,category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_NewsTitle);
            content = itemView.findViewById(R.id.txt_NewsContent);
            anh = itemView.findViewById(R.id.imgNews);
            xoa = itemView.findViewById(R.id.img_delete);
            category = itemView.findViewById(R.id.txt_NewsCategory);
        }
    }
}
