package com.sprtcoding.asla.Menu.ModuleLessons;

import static com.sprtcoding.asla.HomeActivity.musicService;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.sprtcoding.asla.R;

public class Introduction extends AppCompatActivity {
    private ImageView back_btn;
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        _init();

        if (musicService != null) {
            musicService.pauseMusic();
        }

        // Load a PDF file from assets folder (change the file path as needed)
        pdfView.fromAsset("intro.pdf")
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();

        back_btn.setOnClickListener(view -> finish());
    }

    private void _init() {
        back_btn = findViewById(R.id.back_btn);
        pdfView = findViewById(R.id.pdfView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}