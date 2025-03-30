package com.example.fasttrack;

public class QuestionnaireSolver {
    private static int splitDays;
    private static int splitGoal;
    private static int lifterExp;
    
    public QuestionnaireSolver() {

    }
    public static String solve(SplitQuestionnaire quest) {
        switch(quest.getQuestion(1).getFinalAnswer()) {
            case "3":
                splitDays = 3;
                break;
            case "4":
                splitDays = 4;
                break;
            case "5":
                splitDays = 5;
                break;
            case "6":
                splitDays = 6;
                break;
            default:
                splitDays = 0;
        }

        switch(quest.getQuestion(2).getFinalAnswer()) {
            case "Strength":
                splitGoal = 1;
                break;
            case "Build Muscle (Hypertrophy)":
                splitGoal = 2;
                break;
            case "Some of Both":
                splitGoal = 3;
                break;
            default:
                splitGoal = 0;
        }

        switch(quest.getQuestion(3).getFinalAnswer()) {
            case "New Lifter":
                lifterExp = 1;
                break;
            case "Some experience":
                lifterExp = 2;
                break;
            case "Experienced Lifter":
                lifterExp = 3;
                break;
            default:
                lifterExp = 0;
        }

        



        return "";
    }
}
