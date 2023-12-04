package com.sprtcoding.asla.Menu.ModuleLessons.Lesson5;

import static com.sprtcoding.asla.HomeActivity.musicService;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.android.material.button.MaterialButton;
import com.sprtcoding.asla.Menu.ModuleLessons.Lesson4.PostTest4;
import com.sprtcoding.asla.R;

public class Lesson_5 extends AppCompatActivity {
    private ImageView back_btn;
    private PDFView pdfView;
    private MaterialButton start_post_test_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson5);
        _init();

        if (musicService != null) {
            musicService.pauseMusic();
        }

        // Load a PDF file from assets folder (change the file path as needed)
        pdfView.fromAsset("lesson_5.pdf")
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        // Check if the current page is the last page
                        if (page == pageCount - 1) {
                            // If it is the last page, show the button
                            start_post_test_btn.setVisibility(View.VISIBLE);
                        } else {
                            // If not the last page, hide the button
                            start_post_test_btn.setVisibility(View.GONE);
                        }
                    }
                })
                .load();

        back_btn.setOnClickListener(view -> finish());

        start_post_test_btn.setOnClickListener(view -> {
            Intent i = new Intent(this, PostTest5.class);
            startActivity(i);
        });
    }
    private void _init() {
        back_btn = findViewById(R.id.back_btn);
        pdfView = findViewById(R.id.pdfView);
        start_post_test_btn = findViewById(R.id.start_post_test_btn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}