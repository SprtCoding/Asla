package com.sprtcoding.asla.Menu.EasyCategory.QuestionDB;

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

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        sqLiteDatabase.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
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
        EasyQuestionsModel q12 = new EasyQuestionsModel("Intent Action","Intent Category","Data Type","It is the generic action the receiving activity should perform. The available  actions are defined as constants in the Intent class and begin with the word ACTION_.",1);
        addQuestion(q12);
        EasyQuestionsModel q13 = new EasyQuestionsModel("Intent Action","Intent Category","Data Type","Provides additional information about the category of component that should handle the intent. This category is optional, and you can add more than one category to an intent. This is also defined as constants in the Intent class and begin with the word CATEGORY_.",2);
        addQuestion(q13);
        EasyQuestionsModel q14 = new EasyQuestionsModel("Intent Action","Intent Category","Data Type","It indicates the MIME category of data the activity should operate on. Usually, this is inferred from the URI in the intent data field, but you can also explicitly define this with the setType() method.",3);
        addQuestion(q14);
        EasyQuestionsModel q15 = new EasyQuestionsModel("Extracting","Delivery","Debugging","The process of finding and fixing errors (bugs) or unexpected behavior in your code. All code has bugs, from incorrect behavior in your app, to behavior that excessively consumes memory or network resources, to actual app freezing or crashing.",3);
        addQuestion(q15);
        EasyQuestionsModel q16 = new EasyQuestionsModel("Intent Action","Intent Category","Data Type","It is the generic action the receiving activity should perform. The available actions are defined as constants in the Intent class and begin with the word ACTION_.",1);
        addQuestion(q16);
        EasyQuestionsModel q17 = new EasyQuestionsModel("Intent Action","Intent Category","Data Type","Provides additional information about the category of component that should handle the intent. This category is optional, and you can add more than one category to an intent. This is also defined as constants in the Intent class and begin with the word CATEGORY_.",2);
        addQuestion(q17);
        EasyQuestionsModel q18 = new EasyQuestionsModel("Intent Action","Intent Category","Data Type","It indicates the MIME category of data the activity should operate on. Usually, this is inferred from the URI in the intent data field, but you can also explicitly define this with the setType() method.",3);
        addQuestion(q18);
        EasyQuestionsModel q19 = new EasyQuestionsModel("Implicit intents","Explicit intents","Types of intents","The fully-qualified class name of the receiving activity (or other component) is specified in what intents?",1);
        addQuestion(q19);
        EasyQuestionsModel q20 = new EasyQuestionsModel("Implicit intents","Explicit intents","Types of intents","This intents do not name a particular action or other component that will be used to fulfill the intent. Instead, you state a broad action that will be carried out in the intent",2);
        addQuestion(q20);
        EasyQuestionsModel q21 = new EasyQuestionsModel("Extracting","Delivery","Debugging","The process of finding and fixing errors (bugs) or unexpected behavior in your code. All code has bugs, from incorrect behavior in your app, to behavior that excessively consumes memory or network resources, to actual app freezing or crashing.",3);
        addQuestion(q21);
        EasyQuestionsModel q22 = new EasyQuestionsModel("Variables","Breakpoints","Timeline Panel","It is a place in your code where you want to pause the normal execution of your app to perform other actions such as examining variables or evaluating expressions, or executing your code line by line to determine the causes on runtime errors.",2);
        addQuestion(q22);
        EasyQuestionsModel q23 = new EasyQuestionsModel("Variables","Breakpoints","Timeline Panel","This view of the debugger window allows you to inspect the variables available at the current stack frame when the system stops your app on a breakpoint.",1);
        addQuestion(q23);
        EasyQuestionsModel q24 = new EasyQuestionsModel("System log","Delivery Android Debug Bridge (ADB)","Tracing and Logging","Analyzing traces allows you to see how much time is spent in certain methods, and which ones are taking the longest times.",3);
        addQuestion(q24);
        EasyQuestionsModel q25 = new EasyQuestionsModel("System log","Delivery Android Debug Bridge (ADB)","Tracing and Logging","Is a command-line tool that lets you communicate with an emulator instance or connected Android-powered device.",2);
        addQuestion(q25);
        EasyQuestionsModel q26 = new EasyQuestionsModel("StateListDrawable","Vector drawables","Graphic drawables","It is a drawable object that uses a different image to represent the same object, depending on what state the object is in.",1);
        addQuestion(q26);
        EasyQuestionsModel q27 = new EasyQuestionsModel("StateListDrawable","Vector drawables","Graphic drawables","Are images that are defined by a path. It scale without losing definition and use SVG files, which are plain text files or compressed binary files that include two-dimensional coordinates for how the image is drawn on the screen.",2);
        addQuestion(q27);
        EasyQuestionsModel q28 = new EasyQuestionsModel("By specifying the \"parent\" attribute with the resource ID","By using a predefined keyword","By defining all properties from scratch","How can you create a new style that inherits properties from an existing style in Android?",1);
        addQuestion(q28);
        EasyQuestionsModel q29 = new EasyQuestionsModel("Force Design","Graphic Design","Material Design","Is a unified user experience across platforms and device sizes. It includes a set of guidelines for style, layout, motion, and other aspects of app design.",3);
        addQuestion(q29);
        EasyQuestionsModel q30 = new EasyQuestionsModel("colorPrimary","colorPrimaryDark","colorAccent","Is used by several Views by default. For example, in the  AppTheme  theme,  this  is used as the background color for the action bar.",1);
        addQuestion(q30);
    }

    private void addQuestion(EasyQuestionsModel question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswer());
        this.db.insert(QuizContract.QuestionsTable.TABLE_NAME, null,cv);
    }

    @SuppressLint("Range")
    public List<EasyQuestionsModel> getAllQuestions() {
        List<EasyQuestionsModel> questionList = new ArrayList<>();
        this.db = getReadableDatabase();
        Cursor c = this.db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                EasyQuestionsModel question = new EasyQuestionsModel();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswer(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

}
