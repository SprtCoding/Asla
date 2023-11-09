package com.sprtcoding.asla.Menu.ModerateCategory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.sprtcoding.asla.Adapter.OptionAdapter;
import com.sprtcoding.asla.Menu.EasyCategory.ReviewMePlay;
import com.sprtcoding.asla.Model.EasyQuestionsModel;
import com.sprtcoding.asla.Model.ModerateOptionsModel;
import com.sprtcoding.asla.Model.ModerateQuestionsModel;
import com.sprtcoding.asla.Model.OptionsModel;
import com.sprtcoding.asla.R;
import com.sprtcoding.asla.Sounds.ClickSoundUtils;
import com.sprtcoding.asla.Sounds.FailSound;
import com.sprtcoding.asla.Sounds.HappySound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import io.github.kbiakov.codeview.CodeView;
import io.github.kbiakov.codeview.adapters.Format;
import io.github.kbiakov.codeview.highlight.ColorTheme;
import io.github.kbiakov.codeview.highlight.ColorThemeData;
import io.github.kbiakov.codeview.highlight.Font;

public class GuessMePlay extends AppCompatActivity {
    private CodeView codeView;
    private ImageView next_btn, previous_btn;
    private LottieAnimationView anim;
    private MaterialButton _ok_btn;
    private GridView gridView;
    private TextView item_no,total_score,score_text;
    private ClickSoundUtils clickSoundUtils;
    private boolean isTestCompleted = false;
    private final List<ModerateOptionsModel> moderateOptionsModels = new ArrayList<>();
    private final List<ModerateQuestionsModel> moderateQuestionsModels = new ArrayList<>();
    ModerateQuestionsModel currentQuestion;
    private HappySound happySound;
    private FailSound failSound;
    public static List<Integer> selectedAnswers = new ArrayList<>();
    public static int score = 0, currentItemNo, totalItemNo, selected_option = 0, answer = 0;
    private int[] imageIds_1, imageIds_2, imageIds_3, imageIds_4, imageIds_5, imageIds_6, imageIds_7
            , imageIds_8, imageIds_9, imageIds_10;
    private String code_q_1, code_q_2, code_q_3, code_q_4, code_q_5, code_q_6, code_q_7, code_q_8
            , code_q_9, code_q_10;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_me_play);
        init();

        clickSoundUtils = new ClickSoundUtils(this);
        happySound = new HappySound(this);
        failSound = new FailSound(this);

        getCodeQuestions();

        addQuestions();

        totalItemNo = moderateQuestionsModels.size();
        //Collections.shuffle(moderateQuestionsModels);

        showNextQuestion();

        previous_btn.setVisibility(View.GONE);

        next_btn.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            if (currentItemNo <= selectedAnswers.size()) {
                int previousAnswer = selectedAnswers.get(currentItemNo - 1);
                // Display the previous answer in a TextView or another UI element
                // For example:
                // _previousAnswerTextView.setText("Previous Answer: " + previousAnswer);
                moderateOptionsModels.clear();
                moderateOptionsModels.add(new ModerateOptionsModel(
                        previousAnswer
                ));
            }
            getAnswer();
        });

        previous_btn.setOnClickListener(view -> {
            clickSoundUtils.playClickSound();
            currentItemNo--;
            if(currentItemNo >= 1) {
                showNextQuestion();
                item_no.setText("Item " + currentItemNo + "/" + totalItemNo);
                // Get the previous answer and display it
                if (currentItemNo <= selectedAnswers.size()) {
                    int previousAnswer = selectedAnswers.get(currentItemNo - 1);
                    // Display the previous answer in a TextView or another UI element
                    // For example:
                    // _previousAnswerTextView.setText("Previous Answer: " + previousAnswer);
                    // Find the button corresponding to the previous answer

                }
                if(currentItemNo == 1) {
                    previous_btn.setVisibility(View.GONE);
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void showNextQuestion() {
        if(currentItemNo < totalItemNo) {
            currentQuestion = moderateQuestionsModels.get(currentItemNo);
            // Set code content
            codeView.setCode(currentQuestion.getQuestion());
            codeView.setPadding(20,50,20,50);
            Objects.requireNonNull(codeView.getOptions()).withFont(Font.Consolas);

            ColorThemeData myTheme = ColorTheme.MONOKAI.theme()
                    .withBgContent(android.R.color.background_dark)
                    .withNoteColor(android.R.color.white);

            codeView.getOptions().setTheme(myTheme);

            // Replace these image resource IDs with your own images
            if(currentItemNo == 0) {
                imageIds_1 = new int[]{R.drawable.option_1, R.drawable.option_2, R.drawable.option_3};

                OptionAdapter adapter = new OptionAdapter(this, imageIds_1, moderateQuestionsModels);
                gridView.setAdapter(adapter);
            }else if(currentItemNo == 1) {
                imageIds_2 = new int[]{R.drawable.option_2, R.drawable.option_3, R.drawable.option_1};

                OptionAdapter adapter = new OptionAdapter(this, imageIds_2, moderateQuestionsModels);
                gridView.setAdapter(adapter);
            }else if(currentItemNo == 2) {
                imageIds_3 = new int[]{R.drawable.option_2, R.drawable.option_1, R.drawable.option_3};

                OptionAdapter adapter = new OptionAdapter(this, imageIds_3, moderateQuestionsModels);
                gridView.setAdapter(adapter);
            }else if(currentItemNo == 3) {
                imageIds_4 = new int[]{R.drawable.navigation, R.drawable.tab_layout, R.drawable.toogle_button};

                OptionAdapter adapter = new OptionAdapter(this, imageIds_4, moderateQuestionsModels);
                gridView.setAdapter(adapter);
            }else if(currentItemNo == 4) {
                imageIds_5 = new int[]{R.drawable.navigation, R.drawable.audio_recording, R.drawable.clipboard};

                OptionAdapter adapter = new OptionAdapter(this, imageIds_5, moderateQuestionsModels);
                gridView.setAdapter(adapter);
            }else if(currentItemNo == 5) {
                imageIds_6 = new int[]{R.drawable.audio_recording, R.drawable.navigation, R.drawable.clipboard};

                OptionAdapter adapter = new OptionAdapter(this, imageIds_6, moderateQuestionsModels);
                gridView.setAdapter(adapter);
            }else if(currentItemNo == 6) {
                imageIds_7 = new int[]{R.drawable.progress_circle, R.drawable.file_saving, R.drawable.auto_complete};

                OptionAdapter adapter = new OptionAdapter(this, imageIds_7, moderateQuestionsModels);
                gridView.setAdapter(adapter);
            }else if(currentItemNo == 7) {
                imageIds_8 = new int[]{R.drawable.auto_complete, R.drawable.progress_circle, R.drawable.bluetooth};

                OptionAdapter adapter = new OptionAdapter(this, imageIds_8, moderateQuestionsModels);
                gridView.setAdapter(adapter);
            }else if(currentItemNo == 8) {
                imageIds_9 = new int[]{R.drawable.auto_complete, R.drawable.clipboard, R.drawable.bluetooth};

                OptionAdapter adapter = new OptionAdapter(this, imageIds_9, moderateQuestionsModels);
                gridView.setAdapter(adapter);
            }else if(currentItemNo == 9) {
                imageIds_9 = new int[]{R.drawable.file_saving, R.drawable.auto_complete, R.drawable.clipboard};

                OptionAdapter adapter = new OptionAdapter(this, imageIds_9, moderateQuestionsModels);
                gridView.setAdapter(adapter);
            }

            currentItemNo++;
            item_no.setText("Item " + currentItemNo + "/" + totalItemNo);
        } else {
            showCompleteDialog();
        }
    }

    private void addQuestions() {
        moderateQuestionsModels.add(new ModerateQuestionsModel(
                code_q_1,
                1
        ));

        moderateQuestionsModels.add(new ModerateQuestionsModel(
                code_q_2,
                1
        ));

        moderateQuestionsModels.add(new ModerateQuestionsModel(
                code_q_3,
                3
        ));

        moderateQuestionsModels.add(new ModerateQuestionsModel(
                code_q_4,
                2
        ));

        moderateQuestionsModels.add(new ModerateQuestionsModel(
                code_q_5,
                1
        ));

        moderateQuestionsModels.add(new ModerateQuestionsModel(
                code_q_6,
                1
        ));

        moderateQuestionsModels.add(new ModerateQuestionsModel(
                code_q_7,
                3
        ));

        moderateQuestionsModels.add(new ModerateQuestionsModel(
                code_q_8,
                3
        ));

        moderateQuestionsModels.add(new ModerateQuestionsModel(
                code_q_9,
                2
        ));

        moderateQuestionsModels.add(new ModerateQuestionsModel(
                code_q_10,
                1
        ));
    }

    private void getCodeQuestions() {
        code_q_1 = "import android.content.DialogInterface;\nimport android.support.v7.app.AlertDialog;\n\n" +
                "AlertDialog.Builder myAlertBuilder = new" +
                "AlertDialog.Builder(MainActivity.this);\n\nmyAlertBuilder.setTitle(R.string.alert_title);\nmyAlertBuilder.setMessage(R.string.alert_message);\n\n" +
                "myAlertBuilder.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() { public void onClick(DialogInterface dialog, int which) {\n" +
                "\t\t\t\t\t\t// User clicked OK button.\n" +
                "}\n";

        code_q_2 = "<RadioGroup\n" +
                "android:layout_width=\"match_parent\" android:layout_height=\"wrap_content\" android:orientation=\"vertical\" android:layout_below=\"@id/orderintrotext\">\n" +
                "<RadioButton\n" +
                "android:id=\"@+id/sameday\" android:layout_width=\"wrap_content\" android:layout_height=\"wrap_content\" android:text=\"@string/same_day_messenger_service\" android:onClick=\"onRadioButtonClicked\"/>\n" +
                "<RadioButton\n" +
                "android:id=\"@+id/nextday\" android:layout_width=\"wrap_content\" android:layout_height=\"wrap_content\" android:text=\"@string/next_day_ground_delivery\" android:onClick=\"onRadioButtonClicked\"/>\n" +
                "<RadioButton\n" +
                "android:id=\"@+id/pickup\" android:layout_width=\"wrap_content\" android:layout_height=\"wrap_content\" android:text=\"@string/pick_up\" android:onClick=\"onRadioButtonClicked\"/>\n" +
                "</RadioGroup>\n";

        code_q_3 = "public void onToggleClick(View view) {\n" +
                "ToggleButton toggle = (ToggleButton) findViewById(R.id.my_toggle); toggle.setOnCheckedChangeListener(new\n" +
                "CompoundButton.OnCheckedChangeListener() { public void onCheckedChanged(CompoundButton buttonView,\n" +
                "boolean isChecked) {\n" +
                "StringBuffer onOff = new StringBuffer().append(\"On or off? \"); if (isChecked) { // The toggle is enabled\n" +
                "onOff.append(\"ON \");\n" +
                "} else { // The toggle is disabled onOff.append(\"OFF \");\n" +
                "}\n" +
                "Toast.makeText(getApplicationContext(), onOff.toString(),\n" +
                "Toast.LENGTH_SHORT).show();\n" +
                "}\n" +
                "});\n" +
                "}\n";

        code_q_4 = "protected void onCreate(Bundle savedInstanceState) {\n" +
                "TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);\n" +
                "tabLayout.addTab(tabLayout.newTab().setText(\"Top Stories\"));\n" +
                "tabLayout.addTab(tabLayout.newTab().setText(\"Tech News\"));\n" +
                "tabLayout.addTab(tabLayout.newTab().setText(\"Cooking\"));\n" +
                "tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);\n" +
                "...\n" +
                "}\n";

        code_q_5 = "import android.app.Activity;\n" +
                "import android.content.Intent;\n" +
                "import android.os.Bundle;\n" +
                "import android.view.View;\n" +
                "import android.widget.Button;\n" +
                "public class MainActivity extends Activity  {\n" +
                "   Button b1;\n" +
                "   @Override\n" +
                "   protected void onCreate(Bundle savedInstanceState) {\n" +
                "      super.onCreate(savedInstanceState);\n" +
                "      setContentView(R.layout.activity_main);\n" +
                "\n" +
                "      b1 = (Button) findViewById(R.id.button);\n" +
                "      b1.setOnClickListener(new View.OnClickListener() {\n" +
                "         @Override\n" +
                "         public void onClick(View v) {\n" +
                "            Intent in=new Intent(MainActivity.this,second_main.class);\n" +
                "            startActivity(in);\n" +
                "         }\n";

        code_q_6 = "public class MainActivity extends AppCompatActivity {\n" +
                "\n" +
                "   Button buttonStart, buttonStop, buttonPlayLastRecordAudio, \n" +
                "      buttonStopPlayingRecording ;\n" +
                "   String AudioSavePathInDevice = null;\n" +
                "   MediaRecorder mediaRecorder ;\n" +
                "   Random random ;\n" +
                "   String RandomAudioFileName = \"ABCDEFGHIJKLMNOP\";\n" +
                "   public static final int RequestPermissionCode = 1;\n" +
                "   MediaPlayer mediaPlayer ;\n" +
                "   @Override\n" +
                "   protected void onCreate(Bundle savedInstanceState) {\n" +
                "      super.onCreate(savedInstanceState);\n" +
                "      setContentView(R.layout.activity_main);\n" +
                "      buttonStart = (Button) findViewById(R.id.button);\n" +
                "      buttonStop = (Button) findViewById(R.id.button2);\n" +
                "      buttonPlayLastRecordAudio = (Button) findViewById(R.id.button3);\n" +
                "      buttonStopPlayingRecording = (Button)findViewById(R.id.button4);\n" +
                "      buttonStop.setEnabled(false);\n" +
                "      buttonPlayLastRecordAudio.setEnabled(false);\n" +
                "      buttonStopPlayingRecording.setEnabled(false);\n" +
                "      random = new Random();\n" +
                "      buttonStart.setOnClickListener(new View.OnClickListener() {\n";

        code_q_7 = "public class MainActivity extends Activity {\n" +
                "   AutoCompleteTextView text;\n" +
                "   MultiAutoCompleteTextView text1;\n" +
                "   String[] languages={\"Android \",\"java\",\"IOS\",\"SQL\",\"JDBC\",\"Web services\"};\n" +
                "   \n" +
                "   @Override\n" +
                "   protected void onCreate(Bundle savedInstanceState) {\n" +
                "      super.onCreate(savedInstanceState);\n" +
                "      setContentView(R.layout.activity_main);\n" +
                "      \n" +
                "      text=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);\n" +
                "      text1=(MultiAutoCompleteTextView)findViewById(R.id.multiAutoCompleteTextView1);\n" +
                "      \n" +
                "      ArrayAdapter adapter = new \n" +
                "         ArrayAdapter(this,android.R.layout.simple_list_item_1,languages);\n" +
                "      \n" +
                "      text.setAdapter(adapter);\n" +
                "      text.setThreshold(1);\n" +
                "      \n" +
                "      text1.setAdapter(adapter);\n" +
                "      text1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());\n" +
                "   }\n" +
                "}\n";

        code_q_8 = "public class MainActivity extends Activity  {\n" +
                "   Button b1,b2,b3,b4;\n" +
                "   private BluetoothAdapter BA;\n" +
                "   private Set<BluetoothDevice>pairedDevices;\n" +
                "   ListView lv;\n" +
                "   @Override\n" +
                "   protected void onCreate(Bundle savedInstanceState) {\n" +
                "      super.onCreate(savedInstanceState);\n" +
                "      setContentView(R.layout.activity_main);\n" +
                "      b1 = (Button) findViewById(R.id.button);\n" +
                "      b2=(Button)findViewById(R.id.button2);\n" +
                "      b3=(Button)findViewById(R.id.button3);\n" +
                "      b4=(Button)findViewById(R.id.button4);\n" +
                "      BA = BluetoothAdapter.getDefaultAdapter();\n" +
                "      lv = (ListView)findViewById(R.id.listView);\n" +
                "   }\n" +
                "   public void on(View v){\n" +
                "      if (!BA.isEnabled()) {\n" +
                "         Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);\n" +
                "         startActivityForResult(turnOn, 0);\n" +
                "         Toast.makeText(getApplicationContext(), \"Turned on\",Toast.LENGTH_LONG).show();\n" +
                "      } else {\n" +
                "         Toast.makeText(getApplicationContext(), \"Already on\", Toast.LENGTH_LONG).show();\n" +
                "      }\n";

        code_q_9 = "public class MainActivity extends Activity  {\n" +
                "   Button b1,b2,b3,b4;\n" +
                "   private BluetoothAdapter BA;\n" +
                "   private Set<BluetoothDevice>pairedDevices;\n" +
                "   ListView lv;\n" +
                "   @Override\n" +
                "   protected void onCreate(Bundle savedInstanceState) {\n" +
                "      super.onCreate(savedInstanceState);\n" +
                "      setContentView(R.layout.activity_main);\n" +
                "      b1 = (Button) findViewById(R.id.button);\n" +
                "      b2=(Button)findViewById(R.id.button2);\n" +
                "      b3=(Button)findViewById(R.id.button3);\n" +
                "      b4=(Button)findViewById(R.id.button4);\n" +
                "      BA = BluetoothAdapter.getDefaultAdapter();\n" +
                "      lv = (ListView)findViewById(R.id.listView);\n" +
                "   }\n" +
                "   public void on(View v){\n" +
                "      if (!BA.isEnabled()) {\n" +
                "         Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);\n" +
                "         startActivityForResult(turnOn, 0);\n" +
                "         Toast.makeText(getApplicationContext(), \"Turned on\",Toast.LENGTH_LONG).show();\n" +
                "      } else {\n" +
                "         Toast.makeText(getApplicationContext(), \"Already on\", Toast.LENGTH_LONG).show();\n" +
                "      }\n";

        code_q_10 = "public class MainActivity extends Activity  {\n" +
                "   Button b1,b2;\n" +
                "   TextView tv;\n" +
                "   EditText ed1;\n" +
                "   String data;\n" +
                "   private String file = \"mydata\";\n" +
                "   @Override\n" +
                "   protected void onCreate(Bundle savedInstanceState) {\n" +
                "      super.onCreate(savedInstanceState);\n" +
                "      setContentView(R.layout.activity_main);\n" +
                "      b1=(Button)findViewById(R.id.button);\n" +
                "      b2=(Button)findViewById(R.id.button2);\n" +
                "      ed1=(EditText)findViewById(R.id.editText);\n" +
                "      tv=(TextView)findViewById(R.id.textView2);\n" +
                "      b1.setOnClickListener(new View.OnClickListener() {\n" +
                "         @Override\n" +
                "         public void onClick(View v) {\n" +
                "            data=ed1.getText().toString();\n" +
                "            try {\n" +
                "               FileOutputStream fOut = openFileOutput(file,MODE_WORLD_READABLE);\n" +
                "               fOut.write(data.getBytes());\n" +
                "               fOut.close();\n" +
                "               Toast.makeText(getBaseContext(),\"file saved\",Toast.LENGTH_SHORT).show();\n" +
                "            }\n";
    }

    @SuppressLint("SetTextI18n")
    private void getAnswer() {
        if(selected_option == 0) {
            Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show();
        }else if (selected_option == answer) {
            if(!isTestCompleted) {
                score++;
                previous_btn.setVisibility(View.VISIBLE);
                showNextQuestion();
            }
        }else {
            if(!isTestCompleted) {
                showNextQuestion();
            }
        }
    }

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    private void showCompleteDialog() {
        View testCompleteDialog = LayoutInflater.from(GuessMePlay.this).inflate(R.layout.test_complete_dialog, null);
        AlertDialog.Builder testCompleteDialogBuilder = new AlertDialog.Builder(GuessMePlay.this);

        testCompleteDialogBuilder.setView(testCompleteDialog);

        CardView complete_card = testCompleteDialog.findViewById(R.id.complete_card);
        _ok_btn = testCompleteDialog.findViewById(R.id.ok_btn);
        total_score = testCompleteDialog.findViewById(R.id.total_score);
        score_text = testCompleteDialog.findViewById(R.id.score_text);
        anim = testCompleteDialog.findViewById(R.id.anim);

        final AlertDialog testCompletedDialog = testCompleteDialogBuilder.create();

        Objects.requireNonNull(testCompletedDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        testCompletedDialog.setCanceledOnTouchOutside(false);

        total_score.setText(String.valueOf(score));

        if(score <= 5) {
            failSound.playSound();
            complete_card.setCardBackgroundColor(Color.rgb(191, 64, 64));
            score_text.setText("Oh no! you have a lowest score!");
            anim.setAnimation(R.raw.sad);
        }else {
            happySound.playSound();
            score_text.setText("You have a Highest Score. You are great!");
            anim.setAnimation(R.raw.dancing);
        }
        anim.loop(true);
        anim.playAnimation();

        _ok_btn.setOnClickListener(view -> {
            if(score <= 7) {
                failSound.stopSound();
            }else {
                happySound.stopSound();
            }
            testCompletedDialog.cancel();
            // Save the score in shared preferences
            //saveScoreToSharedPreferences(score);
            finish();
        });

        testCompletedDialog.show();
    }

    private void init() {
        codeView = findViewById(R.id.code_view);
        gridView = findViewById(R.id.gridView);
        next_btn = findViewById(R.id.next_btn);
        item_no = findViewById(R.id.item_no);
        previous_btn = findViewById(R.id.previous_btn);
    }
}