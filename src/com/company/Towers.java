package com.company;

public class Towers {

    public Towers (int num, int diskID) {
        numDisks = num;
        for (int i = 0; i < towers.length; i++) {
            for (int j = 0; j < towers[i].length; j++) {
                towers[i][j] = -1; // -1 is an empty location.
            }
        }
        for (int i = 0; i < towers[0].length; i++) {
            towers[0][i] = diskID;
            diskID--;
        }
    }

    public int numDisks;
    public int [][] towers;

    // 0 - Tower A
    // 1 - Tower B
    // 2 - Tower C

    public void solve (int step, int cycle) {
        int diskID;
        if (step == 1) {
            if (numDisks % 2 == 0) { // if even
                diskID = locateTopDiskID(0);
                moveDisk(diskID, 0, 1);
                System.out.println("(1) Move one disk from Tower 1 to Tower 2.");
            } else {
                diskID = locateTopDiskID(0);
                moveDisk(diskID, 0, 2);
                System.out.println("(1) Move one disk from Tower 1 to Tower 3.");
            }
            step++;
            solve(step, cycle);
        } else {
            if (!empty(0) && !empty(1) && empty(2)) {
                if (numDisks % 2 == 0) { // if even
                    if (step - (cycle * 3) == 1) {
                        if (legal(0, 1)) {
                            diskID = locateTopDiskID(0);
                            moveDisk(diskID, 0, 1);
                            System.out.println("(" + step + ") Move one disk from Tower 1 to Tower 2.");
                        } else {
                            diskID = locateTopDiskID(1);
                            moveDisk(diskID, 1, 0);
                            System.out.println("(" + step + ") Move one disk from Tower 2 to Tower 1.");
                        }
                    } else if (step - (cycle * 3) == 2) {
                        if (legal(0, 2)) {
                            diskID = locateTopDiskID(0);
                            moveDisk(diskID, 0, 2);
                            System.out.println("(" + step + ") Move one disk from Tower 1 to Tower 3.");
                        } else {
                            diskID = locateTopDiskID(2);
                            moveDisk(diskID, 2, 0);
                            System.out.println("(" + step + ") Move one disk from Tower 3 to Tower 1.");
                        }
                    } else {
                        if (legal(1, 2)) {
                            diskID = locateTopDiskID(1);
                            moveDisk(diskID, 1, 2);
                            System.out.println("(" + step + ") Move one disk from Tower 2 to Tower 3.");
                        } else {
                            diskID = locateTopDiskID(2);
                            moveDisk(diskID, 2, 1);
                            System.out.println("(" + step + ") Move one disk from Tower 3 to Tower 2.");
                        }
                        cycle++;
                    }
                } else { // if odd
                    if (step - (cycle * 3) == 1) {
                        if (legal(0, 2)) {
                            diskID = locateTopDiskID(0);
                            moveDisk(diskID, 0, 2);
                            System.out.println("(" + step + ") Move one disk from Tower 1 to Tower 3.");
                        } else {
                            diskID = locateTopDiskID(2);
                            moveDisk(diskID, 2, 0);
                            System.out.println("(" + step + ") Move one disk from Tower 3 to Tower 1.");
                        }
                    } else if (step - (cycle * 3) == 2) {
                        if (legal(0, 1)) {
                            diskID = locateTopDiskID(0);
                            moveDisk(diskID, 0, 1);
                            System.out.println("(" + step + ") Move one disk from Tower 1 to Tower 2.");
                        } else {
                            diskID = locateTopDiskID(1);
                            moveDisk(diskID, 1, 0);
                            System.out.println("(" + step + ") Move one disk from Tower 2 to Tower 1.");
                        }
                    } else {
                        if (legal(2, 1)) {
                            diskID = locateTopDiskID(2);
                            moveDisk(diskID, 2, 1);
                            System.out.println("(" + step + ") Move one disk from Tower 3 to Tower 2.");
                        } else {
                            diskID = locateTopDiskID(1);
                            moveDisk(diskID, 1, 2);
                            System.out.println("(" + step + ") Move one disk from Tower 2 to Tower 3.");
                        }
                        cycle++;
                    }
                }
                step++;
                solve(step, cycle);
            }
        }
    }

    /*
    For an even number of disks:

    1. make the legal move between pegs A and B (in either direction)
    2. make the legal move between pegs A and C (in either direction)
    3. make the legal move between pegs B and C (in either direction)
    repeat until complete

    For an odd number of disks:

    1. make the legal move between pegs A and C (in either direction)
    2. make the legal move between pegs A and B (in either direction)
    3. make the legal move between pegs C and B (in either direction)
    repeat until complete
     */

    public boolean empty (int row) {
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

    public boolean legal (int fromRow, int toRow) {
        int fromRowID = locateTopDiskID(fromRow);
        int toRowID = locateTopDiskID(toRow);
        if (fromRowID > toRowID) {
            return false;
        } else {
            return true;
        }
    }

    public void moveDisk (int diskID, int row, int newRow) {
        for (int i = 0; i < towers[row].length; i++) {
            if (towers[row][i] != diskID) {
                towers[row][i] = -1;
                break;
            }
        }
        for (int i = 0; i < towers[newRow].length; i++) {
            if (towers[newRow][i] != -1) {
                towers[newRow][i] = diskID;
                break;
            }
        }
    }

    public int locateTopDiskID (int row) {
        int diskID = 1;
        for (int i = 0; i < towers[row].length; i++) {
            if (towers[row][i] != -1) {
                diskID = towers[row][i];
            }
        }
        return diskID;
    }

    /*
    public static void calculate (int diskID, int [][] towers) {
        boolean incomplete = true;
        int step = 1;
        while (incomplete) {

            towers = Towers.moveDisk(towers, step);

            // Everything before evaluating whether the task is complete or not.

            if (empty(0, towers) && empty(1, towers) && !empty(2, towers)) {
                incomplete = false;
            }
            System.out.println(step + ") ");
            step++;
        }
    }
    */
}
