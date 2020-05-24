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
    private static Scanner input = new Scanner(System.in);
    private static int viNum;
    private static int huNum;
    private static int infector = 5;


    public static int getInfector() {
        return infector;
    }

    public static void setInfector(int infector) {
        CritterMain.infector = infector;
    }


    public static void main(String[] args) {
//        intro();
        CritterFrame frame = new CritterFrame(60, 40);

        // uncomment each of these lines as you complete these classes
//        frame.add(30, Tiger.class);
//        frame.add(viNum, Virus.class);
//        frame.add(huNum, Human.class);
        frame.add(200, Human.class);
        frame.add(50, Virus.class);
        frame.add(0, Recovered.class);
        frame.add(0, Deceased.class);
//
//        frame.add(30, FlyTrap.class);
//        frame.add(30, Food.class);

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
            else {
                huNum -= viNum;
            }
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
