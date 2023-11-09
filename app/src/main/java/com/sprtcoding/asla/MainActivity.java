package com.sprtcoding.asla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.sprtcoding.asla.Loading.ProgressBarAnimation;

public class MainActivity extends AppCompatActivity {
    private ProgressBar _progress;
    private TextView _loadingValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        inits();

        _progress.setMax(100);
        _progress.setScaleY(3f);

        progressAnimation();
    }

    public void progressAnimation() {
        ProgressBarAnimation anim = new ProgressBarAnimation(this, _progress, _loadingValue, 0f, 100f);
        anim.setDuration(5000);
        _progress.setAnimation(anim);

        if(ProgressBarAnimation.values == 100f) {
            finish();
        }
    }

    private void inits() {
        _progress = findViewById(R.id.progress);
        _loadingValue = findViewById(R.id.loadingValue);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}