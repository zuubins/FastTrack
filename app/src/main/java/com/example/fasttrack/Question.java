package com.example.fasttrack;

import android.widget.TextView;

public class Question {
    private String questionText;
    private String[] options;
    private int[] resultScores; // Each answer gives a different score towards a result
    private String finalAnswer;
    private TextView questionTV;

    public Question(String questionText, String[] options, int[] resultScores) {
        this.questionText = questionText;
        this.options = options;
        this.resultScores = resultScores;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public String getSingleOption(int index) {
        return options[index];
    }

    public int[] getResultScores() {
        return resultScores;
    }

    public void setFinalAnswer(String s) {
        finalAnswer = s;

    }

    public String getFinalAnswer() {
        return finalAnswer;
    }
}
