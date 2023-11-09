package com.sprtcoding.asla.Menu.ModuleLessons.Lesson1.QDB1;

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

public class Lesson1QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Lesson1DB.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public Lesson1QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                Lesson1QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                Lesson1QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Lesson1QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                Lesson1QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                Lesson1QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                Lesson1QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                Lesson1QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        sqLiteDatabase.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Lesson1QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void fillQuestionsTable() {
        EasyQuestionsModel q1 = new EasyQuestionsModel("Navigation Bar","Tool Bar","Editor Pane","IT carries out a wide range of actions, including running the Android app and launching Android tools.",2);
        addQuestion(q1);
        EasyQuestionsModel q2 = new EasyQuestionsModel("Navigation Bar","Monitor Pane","Project Pane","It allows navigation through the project and open files for editing. It provides a more compact view of the project structure.",1);
        addQuestion(q2);
        EasyQuestionsModel q3 = new EasyQuestionsModel("Monitor Pane","Editor Pane","Project Pane","This pane shows the contents of a selected file in the project. For example, after selecting a layout (as shown in the figure), this pane shows the layout editor with tools to edit the layout. After selecting a Java code file, this pane shows the code with tools for editing the code.",2);
        addQuestion(q3);
        EasyQuestionsModel q4 = new EasyQuestionsModel("Navigation Bar","Tool Bar","Status Bar","It displays the progress of the project and Android Studio itself, as well as any warnings or messages. You can watch the build progress in the status bar.",3);
        addQuestion(q4);
        EasyQuestionsModel q5 = new EasyQuestionsModel("Navigation Bar","Monitor Pane","Project Pane","Offers access to the TODO list for managing tasks, the Android Monitor for monitoring app execution (shown in the figure), the logcat for viewing log messages, and the Terminal application for performing Terminal activities.",2);
        addQuestion(q5);
        EasyQuestionsModel q6 = new EasyQuestionsModel("Views","Presenters","Model","Are user interface elements that display data and respond to user actions.",1);
        addQuestion(q6);
        EasyQuestionsModel q7 = new EasyQuestionsModel("Views","Presenters","Model","It connect the application's views to the model. They supply the views with data as specified by the model, and also provide the model with user input from the view.",2);
        addQuestion(q7);
        EasyQuestionsModel q8 = new EasyQuestionsModel("Views","Presenters","Model","It specifies the structure of the app's data and the code to access and manipulate the data. Some of the apps you create in the lessons work with models for accessing dataViews",3);
        addQuestion(q8);
        EasyQuestionsModel q9 = new EasyQuestionsModel("RelativeLayout","ConstraintLayout","ConstantLayout","A group of child views in which each view is positioned and aligned relative to other views within the view group. In other words, the positions of the child views can be described in relation to each other or to the parent.",1);
        addQuestion(q9);
        EasyQuestionsModel q10 = new EasyQuestionsModel("RelativeLayout","ConstraintLayout","ConstantLayout","A group of child views using anchor points, edges, and guidelines to control how views are positioned relative to other elements in the layout. It was designed to make it easy to drag and drop.",2);
        addQuestion(q10);
        EasyQuestionsModel q11 = new EasyQuestionsModel("RelativeLayout","ConstraintLayout","ConstantLayout","A group that lets you specify exact locations (x/y coordinates) of its child views. Absolute layouts are less flexible and harder to maintain than other types of layouts without absolute positioning.",3);
        addQuestion(q11);
        EasyQuestionsModel q12 = new EasyQuestionsModel("Android Monitor","Monitors tab","Logcat tab","It displays log messages about the app as it is running. If you add logging statements to the app, your log messages from these statements appear with the other log messages under this tab.",3);
        addQuestion(q12);
        EasyQuestionsModel q13 = new EasyQuestionsModel("RecycleView","Monitors tab","ScrollView","A group that allows the child view to be scrolled and contains just one other child view",3);
        addQuestion(q13);
        EasyQuestionsModel q14 = new EasyQuestionsModel("Attribute control","Design toolbar","ScrollView","It provides buttons to configure your layout appearance in the design pane and to edit the layout properties. See the figure below for details.",2);
        addQuestion(q14);
        EasyQuestionsModel q15 = new EasyQuestionsModel("Attribute control","Design pane","Component Tree","This shows the view hierarchy. Click a view or view group in this pane to select it. The figure shows the TextView selected.",3);
        addQuestion(q15);
    }

    private void addQuestion(EasyQuestionsModel question) {
        ContentValues cv = new ContentValues();
        cv.put(Lesson1QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(Lesson1QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(Lesson1QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(Lesson1QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(Lesson1QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswer());
        this.db.insert(Lesson1QuizContract.QuestionsTable.TABLE_NAME, null,cv);
    }

    @SuppressLint("Range")
    public List<EasyQuestionsModel> getAllQuestions() {
        List<EasyQuestionsModel> questionList = new ArrayList<>();
        this.db = getReadableDatabase();
        Cursor c = this.db.rawQuery("SELECT * FROM " + Lesson1QuizContract.QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                EasyQuestionsModel question = new EasyQuestionsModel();
                question.setQuestion(c.getString(c.getColumnIndex(Lesson1QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(Lesson1QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(Lesson1QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(Lesson1QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswer(c.getInt(c.getColumnIndex(Lesson1QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
