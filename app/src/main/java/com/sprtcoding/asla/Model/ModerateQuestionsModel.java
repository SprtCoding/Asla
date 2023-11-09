package com.sprtcoding.asla.Model;

public class ModerateQuestionsModel {
    String Question;
    int answer;

    public ModerateQuestionsModel() {
    }

    public ModerateQuestionsModel(String question, int answer) {
        Question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
