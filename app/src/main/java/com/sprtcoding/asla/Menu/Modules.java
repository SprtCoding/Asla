package com.sprtcoding.asla.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sprtcoding.asla.Menu.ModuleLessons.Introduction;
import com.sprtcoding.asla.Menu.ModuleLessons.Lesson1.Lesson_1_PreTest;
import com.sprtcoding.asla.Menu.ModuleLessons.Lesson2.Lesson_2_PreTest;
import com.sprtcoding.asla.Menu.ModuleLessons.Lesson3.Lesson_3_PreTest;
import com.sprtcoding.asla.Menu.ModuleLessons.Lesson4.Lesson_4_PreTest;
import com.sprtcoding.asla.Menu.ModuleLessons.Lesson5.Lesson_5_PreTest;
import com.sprtcoding.asla.Menu.ModuleLessons.Lesson6.Lesson_6_PreTest;
import com.sprtcoding.asla.Menu.ModuleLessons.Lesson7.Lesson_7_PreTest;
import com.sprtcoding.asla.R;

public class Modules extends AppCompatActivity {
    private TextView lesson_1, lesson_2, lesson_3, lesson_4, lesson_5
            , lesson_6, lesson_7, introduction;
    private ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);
        _init();

        //setUnderline();
        setClickListener();

    }

    private void setClickListener() {
        back_btn.setOnClickListener(view -> finish());

        introduction.setOnClickListener(view -> {
            Intent i = new Intent(this, Introduction.class);
            startActivity(i);
        });

        lesson_1.setOnClickListener(view -> {
            Intent i = new Intent(this, Lesson_1_PreTest.class);
            startActivity(i);
        });

        lesson_2.setOnClickListener(view -> {
            Intent i = new Intent(this, Lesson_2_PreTest.class);
            startActivity(i);
        });

        lesson_3.setOnClickListener(view -> {
            Intent i = new Intent(this, Lesson_3_PreTest.class);
            startActivity(i);
        });

        lesson_4.setOnClickListener(view -> {
            Intent i = new Intent(this, Lesson_4_PreTest.class);
            startActivity(i);
        });

        lesson_5.setOnClickListener(view -> {
            Intent i = new Intent(this, Lesson_5_PreTest.class);
            startActivity(i);
        });

        lesson_6.setOnClickListener(view -> {
            Intent i = new Intent(this, Lesson_6_PreTest.class);
            startActivity(i);
        });

        lesson_7.setOnClickListener(view -> {
            Intent i = new Intent(this, Lesson_7_PreTest.class);
            startActivity(i);
        });
    }

//    private void setUnderline() {
//        lesson_1.setPaintFlags(lesson_1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//        lesson_2.setPaintFlags(lesson_2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//        lesson_3.setPaintFlags(lesson_3.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//        lesson_4.setPaintFlags(lesson_4.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//        lesson_5.setPaintFlags(lesson_5.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//        lesson_6.setPaintFlags(lesson_6.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//        lesson_7.setPaintFlags(lesson_7.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//        introduction.setPaintFlags(introduction.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//    }

    private void _init() {
        back_btn = findViewById(R.id.back_btn);

        lesson_1 = findViewById(R.id.lesson_1);
        lesson_2 = findViewById(R.id.lesson_2);
        lesson_3 = findViewById(R.id.lesson_3);
        lesson_4 = findViewById(R.id.lesson_4);
        lesson_5 = findViewById(R.id.lesson_5);
        lesson_6 = findViewById(R.id.lesson_6);
        lesson_7 = findViewById(R.id.lesson_7);
        introduction = findViewById(R.id.introduction);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}