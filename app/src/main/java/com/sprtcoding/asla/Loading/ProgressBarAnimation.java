package com.sprtcoding.asla.Loading;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.sprtcoding.asla.HomeActivity;

public class ProgressBarAnimation extends Animation {
    private Context context;
    private ProgressBar progressIndicator;
    private TextView value;
    private float from;
    private float to;
    public static float values;

    public ProgressBarAnimation(Context context, ProgressBar progressIndicator, TextView value, float from, float to) {
        this.context = context;
        this.progressIndicator = progressIndicator;
        this.value = value;
        this.from = from;
        this.to = to;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        values = from + (to - from) * interpolatedTime;
        progressIndicator.setProgress((int) values);
        value.setText((int) values+" %");

        if(values == to) {
            context.startActivity(new Intent(context, HomeActivity.class));
        }
    }
}
