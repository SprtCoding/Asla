package com.sprtcoding.asla.Menu.ModuleLessons.Lesson1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.sprtcoding.asla.Menu.ModuleLessons.Lesson1.QDB1.Lesson1QuizDbHelper;
import com.sprtcoding.asla.Model.EasyQuestionsModel;
import com.sprtcoding.asla.Model.OptionsModel;
import com.sprtcoding.asla.R;
import com.sprtcoding.asla.Sounds.ClickSoundUtils;
import com.sprtcoding.asla.Sounds.FailSound;
import com.sprtcoding.asla.Sounds.HappySound;
import com.sprtcoding.asla.Sounds.LevelCompleteSound;
import com.sprtcoding.asla.Sounds.NextLevelSoundUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PreTest1 extends AppCompatActivity {
    private TextView _item_no, _question, _option_1, _option_2, _option_3, lastBtnClicked = null, previousSelectedButton = null,
            total_score, score_text;
    private ImageView _next_btn, _previous_btn;
    private MaterialButton _ok_btn;
    private LottieAnimationView anim;
    private int currentItemNo, totalItemNo, score = 0;
    private String answer = "";
    private ClickSoundUtils clickSoundUtils;
    private NextLevelSoundUtils nextLevelSoundUtils;
    private LevelCompleteSound levelCompleteSound;
    private HappySound happySound;
    private FailSound failSound;
    private boolean isTestCompleted = false;
    private boolean isAnswered;
    private final List<OptionsModel> options = new ArrayList<>();
    private List<EasyQuestionsModel> questionsList;
    private EasyQuestionsModel currentQuestion;
    private final List<String> selectedAnswers = new ArrayList<>();
    private final List<Integer> selectedAnswersInt = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_test1);
        init();

        clickSoundUtils = new ClickSoundUtils(this);
        nextLevelSoundUtils = new NextLevelSoundUtils(this);
        levelCompleteSound = new LevelCompleteSound(this);
        happySound = new HappySound(this);
        failSound = new FailSound(this);

        Lesson1QuizDbHelper quizDbHelper = new Lesson1QuizDbHelper(this);
        questionsList = quizDbHelper.getAllQuestions();
        totalItemNo = questionsList.size();
        Collections.shuffle(questionsList);

        showNextQuestion();

        setClickListener();
        _previous_btn.setEnabled(false);
        _previous_btn.setImageTintList(ColorStateList.valueOf(Color.rgb(164, 164, 164)));

        _next_btn.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            if(!isAnswered) {
                if(!options.isEmpty()) {
                    checkAnswer();
                }else {
                    Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show();
                }
            }else {
                showNextQuestion();
            }
        });

        _previous_btn.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            currentItemNo--;
            _item_no.setText("Question: " + currentItemNo + "/" + totalItemNo);
            showNextQuestion();
            if (currentItemNo <= selectedAnswers.size()) {
                String previousAnswer = selectedAnswers.get(currentItemNo - 1);
                if (_option_1.getText().toString().equals(previousAnswer)) {
                    changeButtonBackground(_option_1);
                } else if (_option_2.getText().toString().equals(previousAnswer)) {
                    changeButtonBackground(_option_2);
                } else if (_option_3.getText().toString().equals(previousAnswer)) {
                    changeButtonBackground(_option_3);
                }
            }
            if(currentItemNo == 1) {
                _previous_btn.setEnabled(false);
                _previous_btn.setImageTintList(ColorStateList.valueOf(Color.rgb(164, 164, 164)));
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkAnswer() {
        isAnswered = true;
        int answerNr = options.get(0).getSelectedAnswer();

        // Set it to the original color or transparent
        if(answerNr == currentQuestion.getAnswer()) {
            if(!isTestCompleted) {
                score++;
                _previous_btn.setEnabled(true);
                _previous_btn.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                showNextQuestion();
                options.clear();
            }

        }else {
            if(!isTestCompleted) {
                _previous_btn.setEnabled(true);
                _previous_btn.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                showNextQuestion();
                options.clear();
            }
        }if (lastBtnClicked != null) {
            lastBtnClicked.setBackgroundResource(R.drawable.question_background);
            lastBtnClicked.setTextColor(Color.WHITE);// Set it to the original color or transparent
        }

    }

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    private void showCompleteDialog() {
        View testCompleteDialog = LayoutInflater.from(PreTest1.this).inflate(R.layout.prepost_test_complete_dialog, null);
        AlertDialog.Builder testCompleteDialogBuilder = new AlertDialog.Builder(PreTest1.this);

        testCompleteDialogBuilder.setView(testCompleteDialog);

        MaterialButton _done_btn = testCompleteDialog.findViewById(R.id.done_btn);
        TextView _score = testCompleteDialog.findViewById(R.id.score);
        TextView test_title = testCompleteDialog.findViewById(R.id.test_title);

        final AlertDialog testCompletedDialog = testCompleteDialogBuilder.create();

        Objects.requireNonNull(testCompletedDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        testCompletedDialog.setCanceledOnTouchOutside(false);

        _score.setText(score + "/" + totalItemNo);
        test_title.setText("Pre Test");

        _done_btn.setOnClickListener(view -> {
            testCompletedDialog.cancel();
            // Save the score in shared preferences
            saveScoreToSharedPreferences(score);
            Intent i = new Intent(PreTest1.this, Lesson_1.class);
            startActivity(i);
            finish();
        });

        testCompletedDialog.show();
    }

    // Function to save the score in SharedPreferences
    private void saveScoreToSharedPreferences(int score) {
        SharedPreferences sharedPreferences = getSharedPreferences("PreTestPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("pre_test_score", score);
        editor.apply();
    }

    private void setClickListener() {
        _option_1.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            String option = _option_1.getText().toString();
            setAnswer(option, 1);
            changeButtonBackground(_option_1);
        });

        _option_2.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            String option = _option_2.getText().toString();
            setAnswer(option, 2);
            changeButtonBackground(_option_2);
        });

        _option_3.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            String option = _option_3.getText().toString();
            setAnswer(option, 3);
            changeButtonBackground(_option_3);
        });
    }

    private void setAnswer(String option, int answer) {
        selectedAnswers.add(option);
        selectedAnswersInt.add(answer);
        options.clear();
        options.add(new OptionsModel(
                answer
        ));
        // Update the previously selected button's background (if any)
        if (previousSelectedButton != null) {
            previousSelectedButton.setBackgroundResource(R.drawable.question_background);
        }
    }

    private void changeButtonBackground(TextView option_1) {
        // Revert the background color of the previously clicked button (if any)
        if (lastBtnClicked != null) {
            lastBtnClicked.setBackgroundResource(R.drawable.question_background);
            lastBtnClicked.setTextColor(Color.WHITE);// Set it to the original color or transparent
        }

        // Change the background color of the currently clicked button
        option_1.setBackgroundResource(R.drawable.option_selected_background); // You can set any color you want
        option_1.setTextColor(Color.WHITE);

        // Update the lastClickedButton reference to the currently clicked button
        lastBtnClicked = option_1;

        // Update the previousSelectedButton reference
        previousSelectedButton = option_1;
    }

    @SuppressLint("SetTextI18n")
    private void showNextQuestion() {

        if(currentItemNo < totalItemNo) {
            currentQuestion = questionsList.get(currentItemNo);

            _question.setText(currentQuestion.getQuestion());
            _option_1.setText(currentQuestion.getOption1());
            _option_2.setText(currentQuestion.getOption2());
            _option_3.setText(currentQuestion.getOption3());

            currentItemNo++;
            _item_no.setText("Question: " + currentItemNo + "/" + totalItemNo);
            isAnswered = false;
            isTestCompleted = false;
        } else {
            isTestCompleted = true;
            showCompleteDialog();
        }
    }

    private void init() {
        _item_no = findViewById(R.id.item_no);
        _question = findViewById(R.id.question);
        _option_1 = findViewById(R.id.option_1);
        _option_2 = findViewById(R.id.option_2);
        _option_3 = findViewById(R.id.option_3);
        _next_btn = findViewById(R.id.next_btn);
        _previous_btn = findViewById(R.id.previous_btn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}