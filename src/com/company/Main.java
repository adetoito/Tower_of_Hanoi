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
        Towers game = new Towers(disks, diskID);
        game.solve(1, 1);
    }
}
