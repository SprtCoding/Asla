package com.sprtcoding.asla.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.sprtcoding.asla.R;
import com.xeoh.android.texthighlighter.TextHighlighter;

public class Glossary extends AppCompatActivity {
    private ImageView back_btn;
    private PDFView pdfView;
    private SearchView search_btn;
    private int pageNumber = 0;
    private TextHighlighter textHighlighter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossary);
        _init();

        // Load a PDF file from assets folder (change the file path as needed)
        //displayFromAsset("glossary.pdf");
        pdfView.fromAsset("glossary.pdf")
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();

        // Set up search functionality
        //setupSearchView();

        back_btn.setOnClickListener(view -> finish());
    }
    private void _init() {
        //search_btn = findViewById(R.id.search_btn);
        back_btn = findViewById(R.id.back_btn);
        pdfView = findViewById(R.id.pdfView);
        textHighlighter = new TextHighlighter();
    }

    private void displayFromAsset(String assetFileName) {
        pdfView.fromAsset(assetFileName)
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .pageFitPolicy(FitPolicy.WIDTH)
                .onPageChange((page, pageCount) -> pageNumber = page)
                .load();
    }

    private void setupSearchView() {
        search_btn.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission if needed
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle search query changes
                //pdfView.findText(newText);
                // Clear previous highlights
                textHighlighter.resetTargets();

                // Perform search and highlight
                if (!newText.isEmpty()) {
                    textHighlighter
                            .setBackgroundColor(getResources().getColor(R.color.green))
                            .addTarget(pdfView)
                            .highlight(newText, TextHighlighter.BASE_MATCHER);
                }
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}