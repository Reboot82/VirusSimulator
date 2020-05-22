// CSE 142 Homework 9 (Critters)
// Authors: Stuart Reges and Marty Stepp
//          modified by Kyle Thayer
//
// CritterMain provides the main method for a simple simulation program.  Alter
// the number of each critter added to the simulation if you want to experiment
// with different scenarios.  You can also alter the width and height passed to
// the CritterFrame constructor.

import java.util.Scanner;

public class CritterMain {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        intro();
        CritterFrame frame = new CritterFrame(60, 40);

        // uncomment each of these lines as you complete these classes
         frame.add(30, Virus.class);
//         frame.add(30, Tiger.class);
//         frame.add(30, WhiteTiger.class);
         frame.add(30, Human.class);
//         frame.add(30, BFCCat.class);
//
//        frame.add(30, FlyTrap.class);
//        frame.add(30, Food.class);

        frame.start();
    }

    public static void intro() {
        System.out.println("Before we begin, a few questions:");
        System.out.println("How infectious is this disease? (1-5, with 5 being the most infectious): ");
        int response = 0;
        while (response == 0) {
            response = input.nextInt();
            if (response < 1 || response > 5) {
                System.out.println("");
            }
        }
    }
}
