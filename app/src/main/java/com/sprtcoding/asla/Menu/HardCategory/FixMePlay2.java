package com.sprtcoding.asla.Menu.HardCategory;

import static com.sprtcoding.asla.HomeActivity.musicService;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.sprtcoding.asla.Loading.LoadingDialog;
import com.sprtcoding.asla.R;

import java.util.Objects;

public class FixMePlay2 extends AppCompatActivity {
    private TextView target_1, target_2, target_3, target_4, target_5, choices_1, choices_2, choices_3, choices_4, choices_5
            , item_no;
    private MaterialButton debug_code;
    private LoadingDialog loadingDialog;
    int score = 0, current_item_no = 2, total_item_no = 10;
    private boolean isAnswer1Correct = false,
            isAnswer2Correct = false,
            isAnswer3Correct = false,
            isAnswer4Correct = false,
            isAnswer5Correct = false;
    String answer_1, answer_2, answer_3, answer_4, answer_5;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_me_play2);
        _init();
        setAnswer();

        if (musicService != null) {
            musicService.pauseMusic();
        }

        loadingDialog = new LoadingDialog(this);

        score = getIntent().getIntExtra("Score", 0);

        item_no.setText("Item " + current_item_no + "/" + total_item_no);

        choices_1.setOnLongClickListener(longClickListener);
        choices_2.setOnLongClickListener(longClickListener);
        choices_3.setOnLongClickListener(longClickListener);
        choices_4.setOnLongClickListener(longClickListener);
        choices_5.setOnLongClickListener(longClickListener);

        target_1.setOnDragListener(dragListener);
        target_2.setOnDragListener(dragListener);
        target_3.setOnDragListener(dragListener);
        target_4.setOnDragListener(dragListener);
        target_5.setOnDragListener(dragListener);

        debug_code.setOnClickListener(view -> {
            loadingDialog.show();
            if(target_1.getText().toString().equals("_______") || target_2.getText().toString().equals("_______") ||
                    target_3.getText().toString().equals("_______") || target_4.getText().toString().equals("_______")
                    || target_5.getText().toString().equals("_______")) {
                loadingDialog.dismiss();
                Toast.makeText(this, "Please drag code to the blank line.", Toast.LENGTH_SHORT).show();
            } else {
                if(isAnswer1Correct && isAnswer2Correct && isAnswer3Correct && isAnswer4Correct && isAnswer5Correct) {
                    loadingDialog.dismiss();
                    score++;
                    Toast.makeText(this, "Score: " + score, Toast.LENGTH_SHORT).show();
                    showCorrectDialog();
                } else {
                    loadingDialog.dismiss();
                    showWrongDialog();
                }
            }
        });
    }

    private void setAnswer() {
        answer_1 = "layout_width";
        answer_2 = "wrap_content";
        answer_3 = "RadioGroup";
        answer_4 = "orientation";
        answer_5 = "onRadioButtonClicked";

        choices_1.setText(answer_1);
        choices_2.setText(answer_3);
        choices_3.setText(answer_4);
        choices_4.setText(answer_5);
        choices_5.setText(answer_2);

    }

    View.OnLongClickListener longClickListener = view -> {
        ClipData clipData = ClipData.newPlainText("","");
        View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(clipData, myShadowBuilder, view,0);

        return true;
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View view, DragEvent event) {
            int dragEvent = event.getAction();

            switch (dragEvent) {
                case DragEvent.ACTION_DROP:
                    final View v = (View) event.getLocalState();

                    if(v.getId() == R.id.choices_1) {
                        if(view.getId() == R.id.target_1) {
                            target_1.setText(choices_1.getText().toString());
                            target_1.setTextColor(Color.GREEN);
                            isAnswer1Correct = false;
                        } else if (view.getId() == R.id.target_2) {
                            target_2.setText(choices_1.getText().toString());
                            target_2.setTextColor(Color.GREEN);
                            isAnswer1Correct = false;
                        } else if (view.getId() == R.id.target_3) {
                            target_3.setText(choices_1.getText().toString());
                            target_3.setTextColor(Color.GREEN);
                            isAnswer1Correct = false;
                        } else if (view.getId() == R.id.target_4) {
                            target_4.setText(choices_1.getText().toString());
                            target_4.setTextColor(Color.GREEN);
                            isAnswer1Correct = false;
                        } else if (view.getId() == R.id.target_5) {
                            target_5.setText(choices_1.getText().toString());
                            target_5.setTextColor(Color.GREEN);
                            isAnswer1Correct = true;
                        }
                    }else if(v.getId() == R.id.choices_2) {
                        if(view.getId() == R.id.target_1) {
                            target_1.setText(choices_2.getText().toString());
                            target_1.setTextColor(Color.GREEN);
                            isAnswer2Correct = true;
                        } else if (view.getId() == R.id.target_2) {
                            target_2.setText(choices_2.getText().toString());
                            target_2.setTextColor(Color.GREEN);
                            isAnswer2Correct = false;
                        } else if (view.getId() == R.id.target_3) {
                            target_3.setText(choices_2.getText().toString());
                            target_3.setTextColor(Color.GREEN);
                            isAnswer2Correct = false;
                        } else if (view.getId() == R.id.target_4) {
                            target_4.setText(choices_2.getText().toString());
                            target_4.setTextColor(Color.GREEN);
                            isAnswer2Correct = false;
                        } else if (view.getId() == R.id.target_5) {
                            target_5.setText(choices_2.getText().toString());
                            target_5.setTextColor(Color.GREEN);
                            isAnswer2Correct = false;
                        }
                    }else if(v.getId() == R.id.choices_3) {
                        if(view.getId() == R.id.target_1) {
                            target_1.setText(choices_3.getText().toString());
                            target_1.setTextColor(Color.GREEN);
                            isAnswer3Correct = false;
                        } else if (view.getId() == R.id.target_2) {
                            target_2.setText(choices_3.getText().toString());
                            target_2.setTextColor(Color.GREEN);
                            isAnswer3Correct = true;
                        } else if (view.getId() == R.id.target_3) {
                            target_3.setText(choices_3.getText().toString());
                            target_3.setTextColor(Color.GREEN);
                            isAnswer3Correct = false;
                        } else if (view.getId() == R.id.target_4) {
                            target_4.setText(choices_3.getText().toString());
                            target_4.setTextColor(Color.GREEN);
                            isAnswer3Correct = false;
                        } else if (view.getId() == R.id.target_5) {
                            target_5.setText(choices_3.getText().toString());
                            target_5.setTextColor(Color.GREEN);
                            isAnswer3Correct = false;
                        }
                    }else if(v.getId() == R.id.choices_4) {
                        if(view.getId() == R.id.target_1) {
                            target_1.setText(choices_4.getText().toString());
                            target_1.setTextColor(Color.GREEN);
                            isAnswer4Correct = false;
                        } else if (view.getId() == R.id.target_2) {
                            target_2.setText(choices_4.getText().toString());
                            target_2.setTextColor(Color.GREEN);
                            isAnswer4Correct = false;
                        } else if (view.getId() == R.id.target_3) {
                            target_3.setText(choices_4.getText().toString());
                            target_3.setTextColor(Color.GREEN);
                            isAnswer4Correct = true;
                        } else if (view.getId() == R.id.target_4) {
                            target_4.setText(choices_4.getText().toString());
                            target_4.setTextColor(Color.GREEN);
                            isAnswer4Correct = false;
                        } else if (view.getId() == R.id.target_5) {
                            target_5.setText(choices_4.getText().toString());
                            target_5.setTextColor(Color.GREEN);
                            isAnswer4Correct = false;
                        }
                    }else if(v.getId() == R.id.choices_5) {
                        if(view.getId() == R.id.target_1) {
                            target_1.setText(choices_5.getText().toString());
                            target_1.setTextColor(Color.GREEN);
                            isAnswer5Correct = false;
                        } else if (view.getId() == R.id.target_2) {
                            target_2.setText(choices_5.getText().toString());
                            target_2.setTextColor(Color.GREEN);
                            isAnswer5Correct = false;
                        } else if (view.getId() == R.id.target_3) {
                            target_3.setText(choices_5.getText().toString());
                            target_3.setTextColor(Color.GREEN);
                            isAnswer5Correct = false;
                        } else if (view.getId() == R.id.target_4) {
                            target_4.setText(choices_5.getText().toString());
                            target_4.setTextColor(Color.GREEN);
                            isAnswer5Correct = true;
                        } else if (view.getId() == R.id.target_5) {
                            target_5.setText(choices_5.getText().toString());
                            target_5.setTextColor(Color.GREEN);
                            isAnswer5Correct = false;
                        }
                    }
//                    v.animate()
//                         .x(target_1.getX())
//                         .y(target_1.getY())
//                         .setDuration(700)
//                         .start();

                    break;
            }
            return true;
        }
    };

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    private void showCorrectDialog() {
        View correctDialog = LayoutInflater.from(FixMePlay2.this).inflate(R.layout.correct_dialog, null);
        AlertDialog.Builder correctDialogBuilder = new AlertDialog.Builder(FixMePlay2.this);

        correctDialogBuilder.setView(correctDialog);

        ImageView output = correctDialog.findViewById(R.id.pic_output);
        MaterialButton nextBtn = correctDialog.findViewById(R.id.next_btn);
        TextView labelTxt = correctDialog.findViewById(R.id.score_text);

        final AlertDialog correctDialogs = correctDialogBuilder.create();

        Objects.requireNonNull(correctDialogs.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        correctDialogs.setCanceledOnTouchOutside(false);

        output.setImageResource(R.drawable.radio_button);

        labelTxt.setText("Congrats you debug the Radio Button!");

        nextBtn.setOnClickListener(view -> {
            Intent i = new Intent(this, FixMePlay2.class);
            i.putExtra("Score", score);
            startActivity(i);
            correctDialogs.cancel();
            finish();
        });

        correctDialogs.show();
    }

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    private void showWrongDialog() {
        View wrongDialog = LayoutInflater.from(FixMePlay2.this).inflate(R.layout.wrong_dialog, null);
        AlertDialog.Builder wrongDialogBuilder = new AlertDialog.Builder(FixMePlay2.this);

        wrongDialogBuilder.setView(wrongDialog);

        MaterialButton nextBtn = wrongDialog.findViewById(R.id.next_btn);
        MaterialButton retryBtn = wrongDialog.findViewById(R.id.retry_btn);

        final AlertDialog wrongDialogs = wrongDialogBuilder.create();

        Objects.requireNonNull(wrongDialogs.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wrongDialogs.setCanceledOnTouchOutside(false);

        retryBtn.setOnClickListener(view -> {
            resetCode();
            wrongDialogs.cancel();
        });

        nextBtn.setOnClickListener(view -> {

            wrongDialogs.cancel();
            finish();
        });

        wrongDialogs.show();
    }

    @SuppressLint("SetTextI18n")
    private void resetCode() {
        target_1.setText("_______");
        target_2.setText("_______");
        target_3.setText("_______");
        target_4.setText("_______");
        target_5.setText("_______");
    }

    private void _init() {
        target_1 = findViewById(R.id.target_1);
        target_2 = findViewById(R.id.target_2);
        target_3 = findViewById(R.id.target_3);
        target_4 = findViewById(R.id.target_4);
        target_5 = findViewById(R.id.target_5);
        choices_1 = findViewById(R.id.choices_1);
        choices_2 = findViewById(R.id.choices_2);
        choices_3 = findViewById(R.id.choices_3);
        choices_4 = findViewById(R.id.choices_4);
        choices_5 = findViewById(R.id.choices_5);
        item_no = findViewById(R.id.item_no);
        debug_code = findViewById(R.id.debug_code);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}