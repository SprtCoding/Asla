package com.sprtcoding.asla.Menu.ModuleLessons.Lesson5.QDB5;

import android.provider.BaseColumns;

public class Lesson5QuizContract {
    private Lesson5QuizContract() {}

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "lesson5_pretest_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }
}
