package com.sprtcoding.asla.Menu.ModuleLessons.Lesson4.QDB4;

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

public class Lesson4QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Lesson4DB.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public Lesson4QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                Lesson4QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                Lesson4QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Lesson4QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                Lesson4QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                Lesson4QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                Lesson4QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                Lesson4QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        sqLiteDatabase.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Lesson4QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void fillQuestionsTable() {
        EasyQuestionsModel q1 = new EasyQuestionsModel("Flat Buttons","Back Buttons","Shift Buttons"," It resemble basic buttons except that they have no borders or background, but still change appearance during different states.",1);
        addQuestion(q1);
        EasyQuestionsModel q2 = new EasyQuestionsModel("onLongClick()","onKey()","onClick()","Handles a click event in which the user touches and then releases an area of the device display occupied by a view. This callback has no return value.",3);
        addQuestion(q2);
        EasyQuestionsModel q3 = new EasyQuestionsModel("onLongClick()","onKey()","onFocusChange()","Handles an event in which the user maintains the touch over a view for an extended period. This returns a boolean to indicate whether you have consumed the event and it should not be carried further.",1);
        addQuestion(q3);
        EasyQuestionsModel q4 = new EasyQuestionsModel("onLongClick()","onTouch()","onFocusChange()","Any form of touch contact with the screen including individual or multiple touches and gesture motions, including a press, a release, or any movement gesture on the screen (within the bounds of the UI element).",2);
        addQuestion(q4);
        EasyQuestionsModel q5 = new EasyQuestionsModel("onLongClick()","onFocusChange()","onKey()","Handles when focus moves away from the current view as the result of interaction with a trackball or navigation key.",2);
        addQuestion(q5);
        EasyQuestionsModel q6 = new EasyQuestionsModel("onLongClick()","onFocusChange()","onKey()","Handles when focus moves away from the current view as the result of interaction with a trackball or navigation key.",3);
        addQuestion(q6);
        EasyQuestionsModel q7 = new EasyQuestionsModel("Radio Buttons","Toggle Button","Spinner","Select only one value from a set of values by clicking the value's circular \"radio\" button. If you are providing only two or three choices, you might want to use this buttons for the choices if you have room in your layout.",1);
        addQuestion(q7);
        EasyQuestionsModel q8 = new EasyQuestionsModel("Radio Button","Toggle Button","Static Button","Select one state out of two or more states. This buttons usually offer two visible states, such as \"on\" and \"off\".",2);
        addQuestion(q8);
        EasyQuestionsModel q9 = new EasyQuestionsModel("Action","Spinner","Radio Button","Select one value from a set of values in a drop-down menu. Only one value can be selected. This are useful for three or more choices, and takes up little room in your layout.",2);
        addQuestion(q9);
        EasyQuestionsModel q10 = new EasyQuestionsModel("textCapSentences","textPassword","textAutoCorrect","Set the keyboard to capital letters at the beginning of a sentence.",1);
        addQuestion(q10);
        EasyQuestionsModel q11 = new EasyQuestionsModel("textCapSentences","textPassword","textAutoCorrect","Turn each character the user enters into a dot to conceal an entered password.",2);
        addQuestion(q11);
        EasyQuestionsModel q12 = new EasyQuestionsModel("Moving Code","Tap Code","Action Code","Specifies the state change that occurred, such as a finger tapping down or lifting up.",3);
        addQuestion(q12);
        EasyQuestionsModel q13 = new EasyQuestionsModel("Options menu","Context menu","Popup menu","Appears in the app bar and provides the primary options that affect using the app itself.",3);
        addQuestion(q13);
        EasyQuestionsModel q14 = new EasyQuestionsModel("Options menu","Context menu","Popup menu","Appears as a floating list of choices when the user performs a long tap on an element on the screen.",2);
        addQuestion(q14);
        EasyQuestionsModel q15 = new EasyQuestionsModel("Options menu","Popup menu","Contextual action bar","Appears at the top of the screen overlaying the app bar, with action items that affect the selected element(s).",3);
        addQuestion(q15);
    }

    private void addQuestion(EasyQuestionsModel question) {
        ContentValues cv = new ContentValues();
        cv.put(Lesson4QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(Lesson4QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(Lesson4QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(Lesson4QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(Lesson4QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswer());
        this.db.insert(Lesson4QuizContract.QuestionsTable.TABLE_NAME, null,cv);
    }

    @SuppressLint("Range")
    public List<EasyQuestionsModel> getAllQuestions() {
        List<EasyQuestionsModel> questionList = new ArrayList<>();
        this.db = getReadableDatabase();
        Cursor c = this.db.rawQuery("SELECT * FROM " + Lesson4QuizContract.QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                EasyQuestionsModel question = new EasyQuestionsModel();
                question.setQuestion(c.getString(c.getColumnIndex(Lesson4QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(Lesson4QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(Lesson4QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(Lesson4QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswer(c.getInt(c.getColumnIndex(Lesson4QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
