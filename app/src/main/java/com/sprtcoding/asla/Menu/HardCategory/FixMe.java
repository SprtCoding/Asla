package com.sprtcoding.asla.Menu.HardCategory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.sprtcoding.asla.Loading.LoadingDialog;
import com.sprtcoding.asla.Menu.ModerateCategory.GuessMe;
import com.sprtcoding.asla.Menu.ModerateCategory.GuessMePlay;
import com.sprtcoding.asla.R;
import com.sprtcoding.asla.Sounds.ClickSoundUtils;

import java.util.Objects;

public class FixMe extends AppCompatActivity {
    private CardView _close_btn;
    private MaterialButton _btn_play;
    private ImageView _back_btn;
    private TextView _how_btn, _how_to_play_info;
    private ClickSoundUtils clickSoundUtils;
    private LoadingDialog loadingDialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_me);
        init();

        clickSoundUtils = new ClickSoundUtils(this);

        loadingDialog = new LoadingDialog(this);

        //how to play dialog
        View how_to_PlayDialog = LayoutInflater.from(FixMe.this).inflate(R.layout.how_to_play_dialog, null);
        AlertDialog.Builder how_to_PlayDialogBuilder = new AlertDialog.Builder(FixMe.this);

        how_to_PlayDialogBuilder.setView(how_to_PlayDialog);

        _close_btn = how_to_PlayDialog.findViewById(R.id.close_btn);
        _how_to_play_info = how_to_PlayDialog.findViewById(R.id.how_to_play_info);

        final AlertDialog how_to_playDialog = how_to_PlayDialogBuilder.create();

        Objects.requireNonNull(how_to_playDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        how_to_playDialog.setCanceledOnTouchOutside(false);
        //end of how to play dialog

        _how_to_play_info.setText("Drag the following codes to the right position to debug it. You will not able to move to the next item if you did not debug it successfully.");

        _how_btn.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            how_to_playDialog.show();
        });

        _close_btn.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            how_to_playDialog.dismiss();
        });

        _btn_play.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                Intent i = new Intent(this, FixMePlay.class);
                startActivity(i);
            };
            handler.postDelayed(runnable, 1000);
        });

        _back_btn.setOnClickListener(view -> finish());
    }

    private void init() {
        _how_btn = findViewById(R.id.how_btn);
        _btn_play = findViewById(R.id.btn_play);
        _back_btn = findViewById(R.id.back_btn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}