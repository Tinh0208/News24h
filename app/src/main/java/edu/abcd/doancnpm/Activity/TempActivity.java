package edu.abcd.doancnpm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;

import edu.abcd.doancnpm.R;

public class TempActivity extends AppCompatActivity {
    ImageButton backButton;
    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        calendarView = findViewById(R.id.calendar_temp);
        backButton = findViewById(R.id.backButton);
        Intent intent = getIntent();
        if(intent.hasExtra("calendar")) {
            calendarView.setVisibility(View.VISIBLE);
            Log.d("lich",intent.getStringExtra("calendar"));
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}