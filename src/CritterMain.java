// CSE 142 Homework 9 (Critters)
// Authors: Stuart Reges and Marty Stepp
//          modified by Kyle Thayer
//
// CritterMain provides the main method for a simple simulation program.  Alter
// the number of each critter added to the simulation if you want to experiment
// with different scenarios.  You can also alter the width and height passed to
// the CritterFrame constructor.

import javax.swing.*;
import java.util.Scanner;
import java.awt.*;

public class CritterMain {
    private static Scanner input = new Scanner(System.in);
    private static int viNum = 1;
    private static int huNum = 0;
    private static int deceaseRate;
    private static int recoverRate;
    private static int infector = 1;

    public static int getDeceaseRate() {
        return deceaseRate;
    }

    public static int getRecoverRate() {
        return recoverRate;
    }

    public static int getInfector() {
        return infector;
    }

    public static void setInfector(int infector) {
        CritterMain.infector = infector;
    }


    public static void main(String[] args) {
        CritterFrame frame = new CritterFrame(60, 40);
        Object [] options = {"1", "2", "3", "4", "5"};
        Object [] options2 = {"50", "100", "150", "200", "250", "300"};
        Object [] options3 = {"5", "10", "15", "20", "25", "30"};
        Object [] options4 = {"10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
        Object [] options5 = {"a day", "a week", "two weeks", "a month"};
        int i = JOptionPane.showOptionDialog(frame, ("Before we begin, a few questions:\n"
                + "How infectious is this disease?\n"
                + "(1-5, with 5 being the most infectious)"), "Question One", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[4]);
        infector = i+1;
        while (viNum > huNum) {
            int h = JOptionPane.showOptionDialog(frame, "How many uninfected humans?: ", "Question Two", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[5]);
            huNum = (h+1)*50;
            int v = JOptionPane.showOptionDialog(frame, "How many infected humans?: ", "Question Three", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[5]);
            viNum = (v+1)*5;
            if (viNum > huNum) {
                JOptionPane.showMessageDialog(frame,
                        "Cannot start with more infected than uninfected.",
                        "Visualization Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        int d = JOptionPane.showOptionDialog(frame, "How deadly is this disease(CFR)?", "Question Four", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options4, options4[9]);
        int r = JOptionPane.showOptionDialog(frame, "How fast do people recover?", "Question Two", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options5, options5[3]);
        deceaseRate = (d+1)*10;
        switch (r) {
            case 0 -> recoverRate = 1;
            case 1 -> recoverRate = 7;
            case 2 -> recoverRate = 14;
            case 3 -> recoverRate = 30;
            default -> System.out.println("This shouldn't have happened");
        }

        frame.add(viNum, Virus.class);
        frame.add(huNum, Human.class);
//        frame.add(200, Human.class);
//        frame.add(50, Virus.class);
        frame.add(0, Recovered.class);
        frame.add(0, Deceased.class);
        frame.start();
    }

    public static void intro() {
        System.out.println("Before we begin, a few questions:");
        System.out.print("How infectious is this disease? (1-5, with 5 being the most infectious): ");
        infector = getResponse(input, 5);
        while (huNum == 0) {
            System.out.print("How many humans total?: ");
            huNum = getResponse(input, 300);
            System.out.print("And how many infected?: ");
            viNum = getResponse(input, 300);
            if (viNum > huNum) {
                System.out.println("Cannot have more infected than total. Please try again.");
                huNum = 0;
            }
            else if (viNum == huNum) {
                System.out.println("Can't start with all infected. Please try again.");
            }
            else {
                huNum -= viNum;
            }
        }
        while (deceaseRate == 0) {
            System.out.print("How deadly is this disease(CFR)? (1-100): ");
            deceaseRate = getResponse(input, 100);
        }
        while (recoverRate == 0) {
            System.out.print("How fast do people recover (in days)?: ");
            recoverRate = getResponse(input, 365);
        }
    }

    private static int getResponse(Scanner input, int max) {
        String response = null;
        int answer = 0;
        while (response == null) {
            response = input.nextLine();
            if (isNumeric(response)) {
                if (response.contains(".")) {
                    System.out.print("Please enter an integer. No decimal points: ");
                    response = null;
                    continue;
                } else {
                    answer = Integer.parseInt(response);
                    if (answer < 1 || answer > max) {
                        System.out.println("Please only enter 1-" + max + ":");
                        response = null;
                        continue;
                    } else {
                        return answer;
                    }
                }
            } else {
                System.out.println("Please only enter 1-" + max + ":");
                response = null;
                continue;
            }
        }
        return answer;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
