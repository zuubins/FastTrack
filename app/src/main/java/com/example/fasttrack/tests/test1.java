package com.example.fasttrack.tests;

import com.example.fasttrack.Movement;
import com.example.fasttrack.Routine;

public class test1 {
    public static void Main(String[] args) {
        Movement testMovement = new Movement("1");
        Routine testRoutine = new Routine();
        testRoutine.addMovement(testMovement);

        System.out.println(testRoutine);
    }

}
