package com.sprtcoding.asla.Menu.ModuleLessons.Lesson6.QDB6;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.sprtcoding.asla.Model.EasyQuestionsModel;

import java.util.ArrayList;
import java.util.List;

public class Lesson6QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Lesson6DB.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public Lesson6QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                Lesson6QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                Lesson6QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Lesson6QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                Lesson6QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                Lesson6QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                Lesson6QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                Lesson6QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        sqLiteDatabase.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Lesson6QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void fillQuestionsTable() {
        EasyQuestionsModel q1 = new EasyQuestionsModel("User Interface","Graphics","Models","This is comprising of various views housing graphical elements like buttons, menus, and text fields, each possessing a distinct set of properties.",1);
        addQuestion(q1);
        EasyQuestionsModel q2 = new EasyQuestionsModel("UI Automator","Manual testing","User interface (UI) testing","It concentrates on assessing elements of the user interface and user interactions. Prioritizing the recognition and response to user input is paramount in UI testing and validation.",3);
        addQuestion(q2);
        EasyQuestionsModel q3 = new EasyQuestionsModel("UI Automator","Manual testing","User interface (UI) testing","As the development process advances, one conventional approach to UI testing involves employing human testers to execute a series of user operations within the target app, subsequently validating its correct functionality.",2);
        addQuestion(q3);
        EasyQuestionsModel q4 = new EasyQuestionsModel("UI Automator","Manual testing","User interface (UI) testing","This testing framework enables you to send an Intent or launch an Activity without using shell commands, by getting a Context object through the getContext() method",1);
        addQuestion(q4);
        EasyQuestionsModel q5 = new EasyQuestionsModel("swipeUp()","click()","dragTo()","In UI automation tests with Espresso and UI Automator, which method is used to perform a swipe gesture that scrolls the content of a scrollable UI element vertically upwards?",1);
        addQuestion(q5);
        EasyQuestionsModel q6 = new EasyQuestionsModel("Domain size","Sequences","Framework","A user interface (UI) involves a multitude of operations that necessitate testing. Even a seemingly modest app can encompass hundreds of potential UI operations.",1);
        addQuestion(q6);
        EasyQuestionsModel q7 = new EasyQuestionsModel("Domain size","Sequences","Framework","Certain app functionalities may require a sequence of user interface (UI) events to achieve. For instance, adding an image to a message about to be sent may involve a series of actions like tapping a camera button, capturing a picture with the camera.",2);
        addQuestion(q7);
        EasyQuestionsModel q8 = new EasyQuestionsModel("Automating tests","Manual testing","Expresso testing","This tests are for user interactions liberates your time and resources for more productive tasks. Test designers aim to create a comprehensive suite of test cases that encompasses the system's entire functionality and rigorously exercises the user interface (UI).",1);
        addQuestion(q8);
        EasyQuestionsModel q9 = new EasyQuestionsModel("Automating tests","Manual testing","Expresso testing","This testing framework in the Android Testing Support Library provides APIs for writing UI tests to simulate user interactions within a single app. It runs on actual device or emulator and behave as if an actual user is using the app.",3);
        addQuestion(q9);
        EasyQuestionsModel q10 = new EasyQuestionsModel("Verifying UI output and navigation","Simulating external server interactions","Testing responses with mocked-up dependencies","What is Espresso used for in Android app development?",1);
        addQuestion(q10);
        EasyQuestionsModel q11 = new EasyQuestionsModel("Android navigation","Android interactions","Android instrumentation","It of comprises a collection of control methods, often referred to as hooks, embedded within the Android system. These hooks govern the behavior of Android components and dictate how the Android system loads apps.",3);
        addQuestion(q11);
        EasyQuestionsModel q12 = new EasyQuestionsModel("TestNG","JUnit 4","Appium","What is the recommended testing framework for writing tests using Espresso and UI Automator in Android?",2);
        addQuestion(q12);
        EasyQuestionsModel q13 = new EasyQuestionsModel("@Rule","@LargeTest","@RunWith","To create an instrumented JUnit 4 test class, add the annotation at the beginning of your test class definition, which indicates the runner that will be used to run the tests in this class.",3);
        addQuestion(q13);
        EasyQuestionsModel q14 = new EasyQuestionsModel("@Rule","@LargeTest","@RunWith","Before declaring the test methods, use this annotation, such as ActivityTestRule or ServiceTestRule",1);
        addQuestion(q14);
        EasyQuestionsModel q15 = new EasyQuestionsModel("@Rule","@LargeTest","@RunWith","This method begins with this annotation and contains the code to exercise and verify a single function in the component that you want to examine.",2);
        addQuestion(q15);
    }

    private void addQuestion(EasyQuestionsModel question) {
        ContentValues cv = new ContentValues();
        cv.put(Lesson6QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(Lesson6QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(Lesson6QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(Lesson6QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(Lesson6QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswer());
        this.db.insert(Lesson6QuizContract.QuestionsTable.TABLE_NAME, null,cv);
    }

    @SuppressLint("Range")
    public List<EasyQuestionsModel> getAllQuestions() {
        List<EasyQuestionsModel> questionList = new ArrayList<>();
        this.db = getReadableDatabase();
        Cursor c = this.db.rawQuery("SELECT * FROM " + Lesson6QuizContract.QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                EasyQuestionsModel question = new EasyQuestionsModel();
                question.setQuestion(c.getString(c.getColumnIndex(Lesson6QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(Lesson6QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(Lesson6QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(Lesson6QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswer(c.getInt(c.getColumnIndex(Lesson6QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
