package com.sprtcoding.asla.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sprtcoding.asla.Menu.EasyCategory.ReviewMe;
import com.sprtcoding.asla.Menu.HardCategory.FixMe;
import com.sprtcoding.asla.Menu.ModerateCategory.GuessMe;
import com.sprtcoding.asla.R;
import com.sprtcoding.asla.Sounds.ClickSoundUtils;

public class Assessment extends AppCompatActivity {
    private ImageView _back_btn;
    private TextView _btn_easy, _btn_moderate, _btn_hard;
    private ClickSoundUtils clickSoundUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        inits();

        clickSoundUtils = new ClickSoundUtils(this);

        _back_btn.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            finish();
        });

        _btn_easy.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            Intent i = new Intent(this, ReviewMe.class);
            startActivity(i);
        });

        _btn_moderate.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            Intent i = new Intent(this, GuessMe.class);
            startActivity(i);
        });

        _btn_hard.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            Intent i = new Intent(this, FixMe.class);
            startActivity(i);
        });

    }
    private void inits() {
        _back_btn = findViewById(R.id.back_btn);
        _btn_easy = findViewById(R.id.btn_easy);
        _btn_moderate = findViewById(R.id.btn_moderate);
        _btn_hard = findViewById(R.id.btn_hard);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}