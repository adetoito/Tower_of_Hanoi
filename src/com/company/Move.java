package com.company;

public class Move {

    public static int [][] moveDisk (int [][] towers, int step) {
        if (step == 1) {
            int diskID = locateTopDiskID(0, towers);
        }

        // Code before returning towers.
        return towers;
    }

    public static int locateTopDiskID (int row, int [][] towers) {
        int diskID = 1;
        for (int i = 0; i < towers[row].length; i++) {
            if (towers[row][i] != -1) {
                diskID = towers[row][i];
            }
        }
    }

}
