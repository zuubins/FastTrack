package com.example.fasttrack;

public class Question {
    private String questionText;
    private String[] options;
    private int[] resultScores; // Each answer gives a different score towards a result

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

    public int[] getResultScores() {
        return resultScores;
    }
}
