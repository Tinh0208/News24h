package edu.abcd.doancnpm.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.abcd.doancnpm.Model.LuuNews;
import edu.abcd.doancnpm.Model.News;
import edu.abcd.doancnpm.Model.User;
import edu.abcd.doancnpm.R;

public class ChitietNewsActivity extends AppCompatActivity {

    ImageView image ;
    TextView title, content;
    News news = null;
    LuuNews luuNews = null;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_news);
        auth = FirebaseAuth.getInstance();

        image = findViewById(R.id.chitiet_image);
        title = findViewById(R.id.chitiet_title);
        content = findViewById(R.id.chitiet_content);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");


        final Object object = getIntent().getSerializableExtra("chitietnews");
        if (object instanceof News){
            news = (News)object;
        }
        if (news != null){
            Glide.with(getApplicationContext()).load(news.getImage()).into(image);
            title.setText(news.getTitle());
            content.setText(news.getContent());
        }

        final Object object1 = getIntent().getSerializableExtra("chitietnews");
        if (object1 instanceof LuuNews){
            luuNews = (LuuNews) object1;
        }
        if (luuNews != null){
            Glide.with(getApplicationContext()).load(luuNews.getImage()).into(image);
            title.setText(luuNews.getTitle());
            content.setText(luuNews .getContent());
        }


        ImageView img_backchitietnews = findViewById(R.id.img_backchitietnews);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");
        img_backchitietnews.setOnClickListener(new View.OnClickListener() {
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
                                        startActivity(new Intent(ChitietNewsActivity.this,MainActivity.class));
                                    }else{
                                        startActivity(new Intent(ChitietNewsActivity.this,MainActivity2.class));
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
    public void onBackPressed() {
        super.onBackPressed();
        // Go back to the previous activity.
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}