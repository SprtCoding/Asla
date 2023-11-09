package com.sprtcoding.asla.FragmentMenu;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sprtcoding.asla.Loading.LoadingDialog;
import com.sprtcoding.asla.Menu.Assessment;
import com.sprtcoding.asla.Menu.Glossary;
import com.sprtcoding.asla.Menu.Modules;
import com.sprtcoding.asla.R;
import com.sprtcoding.asla.Sounds.ClickSoundUtils;

public class HomeFragment extends Fragment {
    View view;
    private TextView _btn_exit, _btn_assessment, _btn_lesson, _btn_glossary;
    private AlertDialog.Builder exitAlertBuilder;
    private ClickSoundUtils clickSoundUtils;
    LoadingDialog loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        inits();

        clickSoundUtils = new ClickSoundUtils(getContext());

        loading = new LoadingDialog(getContext());

        //exit alert dialog
        exitAlertBuilder = new AlertDialog.Builder(getContext());

        _btn_exit.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            exitAlertBuilder.setTitle("Exit Application")
                    .setMessage("Are you sure want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        // Finish all activities in the stack and exit the app
                        getActivity().finishAffinity();
                        System.exit(0);
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                        dialogInterface.cancel();
                    })
                    .show();
        });

        _btn_assessment.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            loading.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loading.dismiss();
                Intent i = new Intent(getContext(), Assessment.class);
                startActivity(i);
            };
            handler.postDelayed(runnable, 3000);
        });

        _btn_lesson.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            loading.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loading.dismiss();
                Intent i = new Intent(getContext(), Modules.class);
                startActivity(i);
            };
            handler.postDelayed(runnable, 3000);
        });

        _btn_glossary.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            loading.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loading.dismiss();
                Intent i = new Intent(getContext(), Glossary.class);
                startActivity(i);
            };
            handler.postDelayed(runnable, 3000);
        });

        return view;
    }

    private void inits() {
        _btn_exit = view.findViewById(R.id.btn_exit);
        _btn_assessment = view.findViewById(R.id.btn_assessment);
        _btn_lesson = view.findViewById(R.id.btn_lesson);
        _btn_glossary = view.findViewById(R.id.btn_glossary);
    }
}