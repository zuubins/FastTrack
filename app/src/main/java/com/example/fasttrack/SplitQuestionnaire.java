package com.example.fasttrack;

import java.util.ArrayList;

public class SplitQuestionnaire {
    private ArrayList<Question> questions;
    private int[] scores;
    private String q1FinalAnswer;
    private String q2FinalAnswer;
    private String q3FinalAnswer;


    public SplitQuestionnaire() {
        this.questions = new ArrayList<>();
        this.scores = new int[3];
        createQuestions();

    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    private void createQuestions() {
        String q1Text = "How many days per week are you able to attend the gym?";
        String[] q1Ans = new String[4];
        int[] q1Scores = new int[4];
        q1Ans[0] = "3";
        q1Ans[1] = "4";
        q1Ans[2] = "5";
        q1Ans[3] = "6";
        for(int i = 0; i < q1Scores.length; i++) q1Scores[i] = i;

        Question daysQuestion = new Question(q1Text, q1Ans, q1Scores);
        questions.add(daysQuestion);

        String q2Text = "What is your focus in weightlifting?";
        String[] q2Ans = new String[3];
        int[] q2Scores = new int[3];
        q2Ans[0] = "Strength";
        q2Ans[1] = "Build Muscle (Hypertrophy)";
        q2Ans[2] = "Some of Both";
        for(int i = 0; i < q2Scores.length; i++) q2Scores[i] = i;
        Question goalQuestion = new Question(q2Text, q2Ans, q2Scores);
        questions.add(goalQuestion);

        String q3Text = "What is your focus in weightlifting?";
        String[] q3Ans = new String[3];
        int[] q3Scores = new int[3];
        q3Ans[0] = "New Lifter";
        q3Ans[1] = "Some experience";
        q3Ans[2] = "Experienced Lifter";
        for(int i = 0; i < q3Scores.length; i++) q3Scores[i] = i;
        Question expQuestion = new Question(q3Text, q3Ans, q3Scores);
        questions.add(expQuestion);
    }

    public String solve() {
        QuestionnaireSolver.solve(this);
        return "";
    }

    public Question getQuestion(int index) {
        Question question = questions.get(index);
        return question;
    }

    public void setAnswer(Question q, String s) {
        q.setFinalAnswer(s);

    }


}
