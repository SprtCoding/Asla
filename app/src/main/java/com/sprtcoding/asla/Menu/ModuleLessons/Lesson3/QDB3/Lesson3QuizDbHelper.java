package com.sprtcoding.asla.Menu.ModuleLessons.Lesson3.QDB3;

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

public class Lesson3QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Lesson3DB.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public Lesson3QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                Lesson3QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                Lesson3QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Lesson3QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                Lesson3QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                Lesson3QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                Lesson3QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                Lesson3QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        sqLiteDatabase.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Lesson3QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void fillQuestionsTable() {
        EasyQuestionsModel q1 = new EasyQuestionsModel("Extracting","Delivery","Debugging","The process of finding and fixing errors (bugs) or unexpected behavior in your code. All code has bugs, from incorrect behavior in your app, to behavior that excessively consumes memory or network resources, to actual app freezing or crashing.",3);
        addQuestion(q1);
        EasyQuestionsModel q2 = new EasyQuestionsModel("Variables","Breakpoints","Timeline Panel","It is a place in your code where you want to pause the normal execution of your app to perform other actions such as examining variables or evaluating expressions, or executing your code line by line to determine the causes on runtime errors.",2);
        addQuestion(q2);
        EasyQuestionsModel q3 = new EasyQuestionsModel("Variables","Breakpoints","Timeline Panel","This view of the debugger window allows you to inspect the variables available at the current stack frame when the system stops your app on a breakpoint.",1);
        addQuestion(q3);
        EasyQuestionsModel q4 = new EasyQuestionsModel("System log","Delivery Android Debug Bridge (ADB)","Tracing and Logging","Analyzing traces allows you to see how much time is spent in certain methods, and which ones are taking the longest times.",3);
        addQuestion(q4);
        EasyQuestionsModel q5 = new EasyQuestionsModel("System log","Delivery Android Debug Bridge (ADB)","Tracing and Logging","Is a command-line tool that lets you communicate with an emulator instance or connected Android-powered device.",2);
        addQuestion(q5);
        EasyQuestionsModel q6 = new EasyQuestionsModel("Profile Panel","Timeline Panel","Setting Panel","Describes when each thread and method started and stopped.",2);
        addQuestion(q6);
        EasyQuestionsModel q7 = new EasyQuestionsModel("Profile Panel","Timeline Panel","Setting Panel","It provides a summary of what happened inside a method",1);
        addQuestion(q7);
        EasyQuestionsModel q8 = new EasyQuestionsModel("Result Code","Intent Data","Request code","This happens when you set launched the activity with startActivityForResult(). If you launch different activities to accomplish different operations, use this code to identify the specific data you're getting back.",3);
        addQuestion(q8);
        EasyQuestionsModel q9 = new EasyQuestionsModel("singleTask","Standard","singleTop","New activities are launched and added to the back stack for the current task. An activity can be instantiated multiple times, a single task can have multiple instances of the same activity, and multiple instances can belong to different tasks.",3);
        addQuestion(q9);
        EasyQuestionsModel q10 = new EasyQuestionsModel("singleTask","Standard","singleTop","If an instance of an activity exists at the top of the back stack for the current task and an intent request for that activity arrives, Android routes that intent to the existing activity instance rather than creating a new instance.",2);
        addQuestion(q10);
        EasyQuestionsModel q11 = new EasyQuestionsModel("singleTask","Standard","singleTop","When the activity is launched the system creates a new task for that activity. If another task already exists with an instance of that activity, the system routes the intent to that activity instead.",1);
        addQuestion(q11);
        EasyQuestionsModel q12 = new EasyQuestionsModel("Testing","Debugging","Profiling","What is the process of finding and repairing mistakes (bugs) or unexpected behavior in your code known as?",2);
        addQuestion(q12);
        EasyQuestionsModel q13 = new EasyQuestionsModel("Android framework limitations (or bugs)","Design or implementation errors","Device limitations (or bugs)","Bugs in software can be caused by which of the following?",2);
        addQuestion(q13);
        EasyQuestionsModel q14 = new EasyQuestionsModel("Android monitor (logcat)","Debugger","Dalvik Debug Monitor Server (DDMS)","Which tool or feature in Android Studio is used to track resource usage during debugging?",3);
        addQuestion(q14);
        EasyQuestionsModel q15 = new EasyQuestionsModel("Click Run > Attach debugger","Click Debug in the toolbar","Click Resume in the toolbar"," How can you start debugging in Android Studio?",2);
        addQuestion(q15);
    }

    private void addQuestion(EasyQuestionsModel question) {
        ContentValues cv = new ContentValues();
        cv.put(Lesson3QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(Lesson3QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(Lesson3QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(Lesson3QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(Lesson3QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswer());
        this.db.insert(Lesson3QuizContract.QuestionsTable.TABLE_NAME, null,cv);
    }

    @SuppressLint("Range")
    public List<EasyQuestionsModel> getAllQuestions() {
        List<EasyQuestionsModel> questionList = new ArrayList<>();
        this.db = getReadableDatabase();
        Cursor c = this.db.rawQuery("SELECT * FROM " + Lesson3QuizContract.QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                EasyQuestionsModel question = new EasyQuestionsModel();
                question.setQuestion(c.getString(c.getColumnIndex(Lesson3QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(Lesson3QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(Lesson3QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(Lesson3QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswer(c.getInt(c.getColumnIndex(Lesson3QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
