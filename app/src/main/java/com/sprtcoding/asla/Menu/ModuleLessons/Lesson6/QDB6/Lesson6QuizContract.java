package com.sprtcoding.asla.Menu.ModuleLessons.Lesson6.QDB6;

import android.provider.BaseColumns;

public class Lesson6QuizContract {
    private Lesson6QuizContract() {}

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "lesson6_pretest_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }
}