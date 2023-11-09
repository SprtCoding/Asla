package com.sprtcoding.asla.Menu.ModuleLessons.Lesson2.QDB2;

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

public class Lesson2QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Lesson2DB.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public Lesson2QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                Lesson2QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                Lesson2QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Lesson2QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                Lesson2QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                Lesson2QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                Lesson2QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                Lesson2QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        sqLiteDatabase.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Lesson2QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void fillQuestionsTable() {
        EasyQuestionsModel q1 = new EasyQuestionsModel("Intent Action","Intent Category","Data Type","It is the generic action the receiving activity should perform. The available actions are defined as constants in the Intent class and begin with the word ACTION_.",1);
        addQuestion(q1);
        EasyQuestionsModel q2 = new EasyQuestionsModel("Intent Action","Intent Category","Data Type","Provides additional information about the category of component that should handle the intent. This category is optional, and you can add more than one category to an intent. This is also defined as constants in the Intent class and begin with the word CATEGORY_.",2);
        addQuestion(q2);
        EasyQuestionsModel q3 = new EasyQuestionsModel("Intent Action","Intent Category","Data Type","It indicates the MIME category of data the activity should operate on. Usually, this is inferred from the URI in the intent data field, but you can also explicitly define this with the setType() method.",3);
        addQuestion(q3);
        EasyQuestionsModel q4 = new EasyQuestionsModel("Implicit intents","Explicit intents","Types of intents","The fully-qualified class name of the receiving activity (or other component) is specified in what intents?",1);
        addQuestion(q4);
        EasyQuestionsModel q5 = new EasyQuestionsModel("Implicit intents","Explicit intents","Types of intents","This intents do not name a particular action or other component that will be used to fulfill the intent. Instead, you state a broad action that will be carried out in the intent",2);
        addQuestion(q5);
        EasyQuestionsModel q6 = new EasyQuestionsModel("onResult()","onActivityResult()","onReturnData()","Which method should be implemented in the originating activity to handle the data returned by a launched activity when using startActivityForResult()?",2);
        addQuestion(q6);
        EasyQuestionsModel q7 = new EasyQuestionsModel("Back stack","Forward stack","Action stack","What is the term for the set of activities that users can access by pressing the device's back button, in reverse chronological order?",1);
        addQuestion(q7);
        EasyQuestionsModel q8 = new EasyQuestionsModel("Using the home button","Using the up button in the app's action bar","Using the device's back button","In Android Stuio, what is the primary way to implement back navigation between activities?",3);
        addQuestion(q8);
        EasyQuestionsModel q9 = new EasyQuestionsModel("Activity's label","Activity's icon","Activity's class name","When starting an activity with an explicit intent, what is the required information to specify the activity to be launched?",3);
        addQuestion(q9);
        EasyQuestionsModel q10 = new EasyQuestionsModel("Up navigation","Down navigation","Back navigation","Which form of navigation in Android allows users to return to the previous activity by tapping the device's back button?",3);
        addQuestion(q10);
        EasyQuestionsModel q11 = new EasyQuestionsModel("To store user data","To maintain activity history","To manage app permissions","What is the primary purpose of the back stack in Android?",2);
        addQuestion(q11);
        EasyQuestionsModel q12 = new EasyQuestionsModel("Temporal navigation","Ancestral navigation","Reverse navigation","Up navigation is also known as:",2);
        addQuestion(q12);
        EasyQuestionsModel q13 = new EasyQuestionsModel("Return to the Home screen","Return to the previous activityb","Navigate to the main activity","In Up navigation, what does the left-facing arrow in the action bar indicate?",2);
        addQuestion(q13);
        EasyQuestionsModel q14 = new EasyQuestionsModel("The most recently launched activity","The main activity","The root activity","When using Up navigation, what is the topmost activity in the hierarchy typically?",2);
        addQuestion(q14);
        EasyQuestionsModel q15 = new EasyQuestionsModel("Up navigation","Down navigation","Forward navigation","Which form of navigation is used to navigate within an app based on explicit hierarchical relationships between screens?",1);
        addQuestion(q15);
    }

    private void addQuestion(EasyQuestionsModel question) {
        ContentValues cv = new ContentValues();
        cv.put(Lesson2QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(Lesson2QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(Lesson2QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(Lesson2QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(Lesson2QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswer());
        this.db.insert(Lesson2QuizContract.QuestionsTable.TABLE_NAME, null,cv);
    }

    @SuppressLint("Range")
    public List<EasyQuestionsModel> getAllQuestions() {
        List<EasyQuestionsModel> questionList = new ArrayList<>();
        this.db = getReadableDatabase();
        Cursor c = this.db.rawQuery("SELECT * FROM " + Lesson2QuizContract.QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                EasyQuestionsModel question = new EasyQuestionsModel();
                question.setQuestion(c.getString(c.getColumnIndex(Lesson2QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(Lesson2QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(Lesson2QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(Lesson2QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswer(c.getInt(c.getColumnIndex(Lesson2QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
