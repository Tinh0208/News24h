package edu.abcd.doancnpm.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.abcd.doancnpm.Model.GopY;
import edu.abcd.doancnpm.R;

public class GopYActivity extends AppCompatActivity {

    EditText ten, noidung;
    Button gui,huy;
    FirebaseDatabase database;
    DatabaseReference reference;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gop_yactivity);

        ten = findViewById(R.id.gopy_ten);
        noidung = findViewById(R.id.gopy_noidung);
        back = findViewById(R.id.img_backuser);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("GopY");
        FirebaseAuth auth = FirebaseAuth.getInstance();

        gui = findViewById(R.id.addGopY);

        gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gopyTen = ten.getText().toString();
                String gopyNoidung = noidung.getText().toString();

                if (gopyTen.isEmpty() || gopyNoidung.isEmpty()){
                    Toast.makeText(GopYActivity.this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                }else{
                    GopY gopY = new GopY(gopyTen,gopyNoidung);
                    reference.push().setValue(gopY).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(GopYActivity.this, "Đã gửi ý kiến", Toast.LENGTH_SHORT).show();
                            ten.setText("");
                            noidung.setText("");
                        }
                    });
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GopYActivity.this,MainActivity.class));
            }
        });
    }

}