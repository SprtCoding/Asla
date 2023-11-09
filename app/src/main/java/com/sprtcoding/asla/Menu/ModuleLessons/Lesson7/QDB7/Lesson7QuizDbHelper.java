package com.sprtcoding.asla.Menu.ModuleLessons.Lesson7.QDB7;

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

public class Lesson7QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Lesson7DB.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public Lesson7QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                Lesson7QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                Lesson7QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Lesson7QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                Lesson7QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                Lesson7QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                Lesson7QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                Lesson7QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        sqLiteDatabase.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Lesson7QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void fillQuestionsTable() {
        EasyQuestionsModel q1 = new EasyQuestionsModel("Shared preferences","SQLite databases","Cloud Backup","In Android, which storage method is primarily used for storing private primitive data in key-value pairs, and is commonly employed for user preferences and settings?",1);
        addQuestion(q1);
        EasyQuestionsModel q2 = new EasyQuestionsModel("External storage","Content providers","Internal storage","On an Android device, where is private data typically stored in the internal storage that is inaccessible to other apps and users?",3);
        addQuestion(q2);
        EasyQuestionsModel q3 = new EasyQuestionsModel("Internal storage","External storage","Network connection","In Android, where is public data usually stored, making it accessible to other apps and users?",2);
        addQuestion(q3);
        EasyQuestionsModel q4 = new EasyQuestionsModel("SQLite databases","Cloud Backup","Shared preferences","When storing structured data in a private database on an Android device, what method is commonly used to define the database schema and manage data operations?",1);
        addQuestion(q4);
        EasyQuestionsModel q5 = new EasyQuestionsModel("Content providers","External storage","Network connection","Android, which method involves storing data on a remote server accessible through the internet, allowing data synchronization between the device and the server?",3);
        addQuestion(q5);
        EasyQuestionsModel q6 = new EasyQuestionsModel("To store private primitive data","To backup app and user data in the cloud","To enable data sharing among apps","What is the primary purpose of Cloud Backup in Android app development?",2);
        addQuestion(q6);
        EasyQuestionsModel q7 = new EasyQuestionsModel("Content providers","Shared preferences","SQLite databases","In Android, which method is used to store data privately and make them available publicly, as discussed in a chapter following the next one?",1);
        addQuestion(q7);
        EasyQuestionsModel q8 = new EasyQuestionsModel("Cloud Backup","Firebase","SQLite databases","It is a mobile platform that helps you develop apps, grow your user base, and earn more money. This is made up of complementary features that you can mix-and-match to fit your needs.",2);
        addQuestion(q8);
        EasyQuestionsModel q9 = new EasyQuestionsModel("CheckBoxPreference","ListPreference","SwitchPreference","Which preference type in Android represents a setting that allows the user to choose from a list of items and typically opens a dialog with a list of radio buttons?",2);
        addQuestion(q9);
        EasyQuestionsModel q10 = new EasyQuestionsModel("Backup SwitchPreference","EditTextPreference","RingtonePreference","In Android app settings, which preference type is used for capturing free-form text input from the user and typically opens a dialog with an EditText widget?",2);
        addQuestion(q10);
        EasyQuestionsModel q11 = new EasyQuestionsModel("CheckBoxPreference","ListPreference","SwitchPreference","Which preference type is used to represent a setting that can be either enabled or disabled and typically displays a checkbox for user interaction?",1);
        addQuestion(q11);
        EasyQuestionsModel q12 = new EasyQuestionsModel("CheckBoxPreference","ListPreference","SwitchPreference","Which preference type is used to represent a setting that can be either enabled or disabled and typically displays a checkbox for user interaction?",3);
        addQuestion(q12);
        EasyQuestionsModel q13 = new EasyQuestionsModel("Simplified UI design","Tablet support","Compatibility with older Android versions","Which preference type is used to represent a setting that can be either enabled or disabled and typically displays a checkbox for user interaction?",3);
        addQuestion(q13);
        EasyQuestionsModel q14 = new EasyQuestionsModel("UI design Header links on the right, settings on the left.","Header links on the left, settings on the right.","All groups and settings in a single view.","In Android tablet layouts, what is the usual arrangement for a master/detail screen?",3);
        addQuestion(q14);
        EasyQuestionsModel q15 = new EasyQuestionsModel("Internal Storage","External Storage","Cloud","This best when you want to be sure that neither the user nor other apps can access your files.",1);
        addQuestion(q15);
    }

    private void addQuestion(EasyQuestionsModel question) {
        ContentValues cv = new ContentValues();
        cv.put(Lesson7QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(Lesson7QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(Lesson7QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(Lesson7QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(Lesson7QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswer());
        this.db.insert(Lesson7QuizContract.QuestionsTable.TABLE_NAME, null,cv);
    }

    @SuppressLint("Range")
    public List<EasyQuestionsModel> getAllQuestions() {
        List<EasyQuestionsModel> questionList = new ArrayList<>();
        this.db = getReadableDatabase();
        Cursor c = this.db.rawQuery("SELECT * FROM " + Lesson7QuizContract.QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                EasyQuestionsModel question = new EasyQuestionsModel();
                question.setQuestion(c.getString(c.getColumnIndex(Lesson7QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(Lesson7QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(Lesson7QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(Lesson7QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswer(c.getInt(c.getColumnIndex(Lesson7QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
