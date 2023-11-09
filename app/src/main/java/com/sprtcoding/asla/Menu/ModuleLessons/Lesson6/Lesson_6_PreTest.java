package com.sprtcoding.asla.Menu.ModuleLessons.Lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.sprtcoding.asla.Menu.ModuleLessons.Lesson5.PreTest5;
import com.sprtcoding.asla.R;

public class Lesson_6_PreTest extends AppCompatActivity {
    private MaterialButton start_pre_test_btn;
    private ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson6_pre_test);
        _init();

        _setClickListener();
    }
    private void _setClickListener() {
        start_pre_test_btn.setOnClickListener(view -> {
            Intent i = new Intent(this, PreTest6.class);
            startActivity(i);
        });

        back_btn.setOnClickListener(view -> finish());
    }

    private void _init() {
        start_pre_test_btn = findViewById(R.id.start_pre_test_btn);
        back_btn = findViewById(R.id.back_btn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}