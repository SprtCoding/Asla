package com.sprtcoding.asla.Model;

public class EasyQuestionsModel {
    String Option1, Option2, Option3, Question;
    int Answer;

    public EasyQuestionsModel() {
    }

    public EasyQuestionsModel(String option1, String option2, String option3, String question, int answer) {
        Option1 = option1;
        Option2 = option2;
        Option3 = option3;
        Question = question;
        Answer = answer;
    }

    public String getOption1() {
        return Option1;
    }

    public void setOption1(String option1) {
        Option1 = option1;
    }

    public String getOption2() {
        return Option2;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public String getOption3() {
        return Option3;
    }

    public void setOption3(String option3) {
        Option3 = option3;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public int getAnswer() {
        return Answer;
    }

    public void setAnswer(int answer) {
        Answer = answer;
    }
}
