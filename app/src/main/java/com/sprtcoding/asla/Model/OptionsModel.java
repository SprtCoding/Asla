package com.sprtcoding.asla.Model;

public class OptionsModel {
    int selectedAnswer;

    public OptionsModel() {
    }

    public OptionsModel(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
