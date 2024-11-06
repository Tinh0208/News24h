package edu.abcd.doancnpm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import edu.abcd.doancnpm.Model.GopY;
import edu.abcd.doancnpm.Model.News;
import edu.abcd.doancnpm.R;

public class ChitietGopyActivity extends AppCompatActivity {
    TextView tv_ten, tv_noidung;
    ImageView back;
    GopY gopY = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_gopy);

        tv_ten = findViewById(R.id.chitietgopy_ten);
        tv_noidung = findViewById(R.id.chitietgopy_noidung);
        back = findViewById(R.id.back_chitietgopy);

        final Object object = getIntent().getSerializableExtra("chitietgopy");
        if (object instanceof GopY){
            gopY = (GopY) object;
        }
        if (gopY != null){
            tv_ten.setText(gopY.getTen());
            tv_noidung.setText(gopY.getNoidung());
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChitietGopyActivity.this,GopYActivity2.class));
            }
        });

    }
}