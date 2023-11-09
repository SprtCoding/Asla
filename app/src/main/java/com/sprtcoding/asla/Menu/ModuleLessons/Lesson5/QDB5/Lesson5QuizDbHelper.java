package com.sprtcoding.asla.Menu.ModuleLessons.Lesson5.QDB5;

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

public class Lesson5QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Lesson5DB.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public Lesson5QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                Lesson5QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                Lesson5QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Lesson5QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                Lesson5QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                Lesson5QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                Lesson5QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                Lesson5QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        sqLiteDatabase.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Lesson5QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void fillQuestionsTable() {
        EasyQuestionsModel q1 = new EasyQuestionsModel("GIF and BMP","WebP and PNG","JPG and BMP","What image formats are preferred for Android applications?",2);
        addQuestion(q1);
        EasyQuestionsModel q2 = new EasyQuestionsModel("Android 4.2","Android 2.3","Android 5.0","Which Android version introduced full support for the WebP image format?",1);
        addQuestion(q2);
        EasyQuestionsModel q3 = new EasyQuestionsModel("actions","pictures","drawable","It is a graphic that can be drawn to the screen which are precompiled images that can be seamlessly integrated into your app.",3);
        addQuestion(q3);
        EasyQuestionsModel q4 = new EasyQuestionsModel("PictureView","ImageView","GraphicView","To display a drawable, use the ________ class to create a View.",2);
        addQuestion(q4);
        EasyQuestionsModel q5 = new EasyQuestionsModel("10-patch","8-patch","9-patch","It is a PNG image in which you define stretchable regions. It is use as the background image for a View to make sure the View looks correct for different screen sizes and orientations.",3);
        addQuestion(q5);
        EasyQuestionsModel q6 = new EasyQuestionsModel("StateListDrawable","Vector drawables","Graphic drawables","It is a drawable object that uses a different image to represent the same object, depending on what state the object is in.",1);
        addQuestion(q6);
        EasyQuestionsModel q7 = new EasyQuestionsModel("StateListDrawable","Vector drawables","Graphic drawables","Are images that are defined by a path. It scale without losing definition and use SVG files, which are plain text files or compressed binary files that include two-dimensional coordinates for how the image is drawn on the screen.",2);
        addQuestion(q7);
        EasyQuestionsModel q8 = new EasyQuestionsModel("By specifying the \"parent\" attribute with the resource ID","By using a predefined keyword","By defining all properties from scratch","How can you create a new style that inherits properties from an existing style in Android?",1);
        addQuestion(q8);
        EasyQuestionsModel q9 = new EasyQuestionsModel("Force Design","Graphic Design","Material Design","Is a unified user experience across platforms and device sizes. It includes a set of guidelines for style, layout, motion, and other aspects of app design.",3);
        addQuestion(q9);
        EasyQuestionsModel q10 = new EasyQuestionsModel("colorPrimary","colorPrimaryDark","colorAccent","Is used by several Views by default. For example, in the  AppTheme  theme,  this  is used as the background color for the action bar.",1);
        addQuestion(q10);
        EasyQuestionsModel q11 = new EasyQuestionsModel("colorPrimary","colorPrimaryDark","colorAccent","Is used in areas that need to slightly contrast with your primary color, for example the status bar.",2);
        addQuestion(q11);
        EasyQuestionsModel q12 = new EasyQuestionsModel("colorPrimary","colorPrimaryDark","colorAccent","Is used as the highlight color for several Views. It's also used for switches in the \"on\" position, floating action buttons, and more.",3);
        addQuestion(q12);
        EasyQuestionsModel q13 = new EasyQuestionsModel("Theme.AppCompat.Light","Theme.AppCompat","Theme.AppCompat.Light.DarkActionBar","If your theme inherits this, the system assumes you are using a dark background. Therefore, all of the text is near white by default.",2);
        addQuestion(q13);
        EasyQuestionsModel q14 = new EasyQuestionsModel("Theme.AppCompat.Light","Theme.AppCompat","Theme.AppCompat.Light.DarkActionBar","Is you used this theme, the text in the action bar is near white, to contrast with the action bar's dark background. The rest of the text in the app is near black, to contrast with the light background.",3);
        addQuestion(q14);
        EasyQuestionsModel q15 = new EasyQuestionsModel("buttonBar","snackBar","menuBar","It provides brief feedback about an operation through a message in a horizontal bar on the screen. It contains a single line of text directly related to the operation performed. This can contain a text action, but no icons.",2);
        addQuestion(q15);
    }

    private void addQuestion(EasyQuestionsModel question) {
        ContentValues cv = new ContentValues();
        cv.put(Lesson5QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(Lesson5QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(Lesson5QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(Lesson5QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(Lesson5QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswer());
        this.db.insert(Lesson5QuizContract.QuestionsTable.TABLE_NAME, null,cv);
    }

    @SuppressLint("Range")
    public List<EasyQuestionsModel> getAllQuestions() {
        List<EasyQuestionsModel> questionList = new ArrayList<>();
        this.db = getReadableDatabase();
        Cursor c = this.db.rawQuery("SELECT * FROM " + Lesson5QuizContract.QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                EasyQuestionsModel question = new EasyQuestionsModel();
                question.setQuestion(c.getString(c.getColumnIndex(Lesson5QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(Lesson5QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(Lesson5QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(Lesson5QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswer(c.getInt(c.getColumnIndex(Lesson5QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
