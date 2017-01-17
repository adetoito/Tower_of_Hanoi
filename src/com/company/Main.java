package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean looping = true; int disks = 0;
        while (looping) {
            System.out.println("Please input the number of disks to solve for:");
            System.out.println("NOTE: For the sake of the world, the max amount of disks will be capped at 63.");
            System.out.println("NOTE: The minimum cap of disks is set to 3.");
            System.out.println("==============================================");
            try {
                disks = sc.nextInt();
                looping = false;
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input. Type in a number of disks from 3-64 inclusive of both numbers.\n");
            }
        }
        int diskID = disks;
        int towers [][] = new int [3][disks];
        for (int i = 0; i < towers.length; i++) {
            for (int j = 0; j < towers[i].length; j++) {
                towers[i][j] = -1; // -1 is an empty location.
            }
        }
        for (int i = 0; i < towers[0].length; i++) {
            towers[0][i] = diskID;
            diskID--;
        }
        diskID = disks;
        calculate(disks, diskID, towers);
    }

    public static void calculate (int disks, int diskID, int [][] towers) {
        boolean incomplete = true;
        int step = 1;
        while (incomplete) {

            towers = Move.moveDisk(towers, step);

            // Everything before evaluating whether the task is complete or not.

            if (empty(0, towers) && empty(1, towers) && !empty(2, towers)) {
                incomplete = false;
            }
            System.out.println(step + ") ");
            step++;
        }
    }

    public static boolean empty (int row, int [][] towers) {
        int counter = 0;
        for (int i = 0; i < towers[row].length; i++) {
            if (towers[row][i] == -1) {
                counter++;
            }
        }
        if (counter == towers[row].length) {
            return true;
        } else {
            return false;
        }
    }
}
