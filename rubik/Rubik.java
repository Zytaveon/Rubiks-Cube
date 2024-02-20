package rubik;

import java.util.*;

public class Rubik {

    /*
     * Things to do:
     * Add user input for block string.
     * Create JUnit test cases for turns.
     */
    public static void main(String[] args) {
        System.out.println("-----------------------\n----- Start Rubik -----\n-----------------------\n");

        // Search search = new Search();
        Block block = new Block();

        block.turnBlock('D', 0);
        block.turnBlock('L', 0);
        block.turnBlock('F', 0);
        block.turnBlock('L', 0);
        block.turnBlock('D', 0);
        // block.turnBlock('R', 0);
        // block.turnBlock('U', 0);
        // block.turnBlock('L', 0);
        // block.turnBlock('D', 0);
        // block.turnBlock('F', 0);
        // block.turnBlock('L', 0);
        // block.turnBlock('R', 0);
        // block.turnBlock('U', 0);
        // block.turnBlock('L', 0);

        // block.randomizeBlock(20);

        System.out.println(Search.BiDirectionalSearch(block.blockString));

        // System.out.println(Search.BiDirectionalSearch(block.getBlockString()));

        System.out.println("\n-----------------------\n------ End Rubik ------\n-----------------------\n");
    }

    /*
     * 3x3 Rubik's Cube object.
     */

    /*
     * 
     * Rubik's Cube 3x3 grid.
     * Goes in order Red, Blue, Orange, Green, Yellow, White.
     * When looking at Red, Blue, Orange, and Green, Yellow will be top.
     * When looking at White and Yellow, Red will be top.
     * 
     * Have that side face the user, and the order is
     * [1, 2, 3]
     * [4, 5, 6]
     * [7, 8, 9]
     * 
     * So for example "RBGRBGRBO"
     * [R, B, G]
     * [R, B, G]
     * [R, B, O]
     * 
     */
    public static class Block {

        private char[] blockString = new char[54];

        public Block() {

            char[] newSolvedBlock = {
                    'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R',
                    'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B',
                    'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O',
                    'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G',
                    'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y',
                    'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'
            };

            blockString = newSolvedBlock;

        }

        /*
         * Need to check to make sure blockString is not null and is only characters
         * Can use BiDirectionalSearch to make sure all pieces are correct
         */

        public Block(char[] blockString) {
            setBlockString(blockString);
        }

        public char[] getBlockString() {
            return blockString;
        }

        public void setBlockString(char[] blockString) {
            this.blockString = blockString;
        }

        public void printBlockString() {
            for (int i = 1; i < blockString.length + 1; i++) {
                System.out.print(blockString[i - 1]);
                if (i % 9 == 0) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        /*
         * turnBlock will use char direction to turn the block that way
         * if direction is '\0' then it will use num 0-11 to turn block
         * if direction is not '\0', then number doesn't matter
         * 
         * Modifies the block
         * If not wanting block to be modified, then use turnBlock"Respective turn"
         * method
         * 
         * 
         */

        public void turnBlock(char direction, int num) {

            if (direction != '\0') {
                switch (direction) {
                    case 'R':
                        blockString = turnBlockRight();
                        break;

                    case 'r':
                        blockString = turnBlockRightPrime();
                        break;

                    case 'L':
                        blockString = turnBlockLeft();
                        break;

                    case 'l':
                        blockString = turnBlockLeftPrime();
                        break;

                    case 'U':
                        blockString = turnBlockUp();
                        break;

                    case 'u':
                        blockString = turnBlockUpPrime();
                        break;

                    case 'D':
                        blockString = turnBlockDown();
                        break;

                    case 'd':
                        blockString = turnBlockDownPrime();
                        break;

                    case 'F':
                        blockString = turnBlockFront();
                        break;

                    case 'f':
                        blockString = turnBlockFrontPrime();
                        break;

                    case 'B':
                        blockString = turnBlockBack();
                        break;

                    case 'b':
                        blockString = turnBlockBackPrime();
                        break;

                    default:
                        // System.out.println("Can't use direction");
                        break;
                }

            }

            else {
                switch (num) {
                    case 0:
                        blockString = turnBlockRight();
                        break;

                    case 1:
                        blockString = turnBlockRightPrime();
                        break;

                    case 2:
                        blockString = turnBlockLeft();
                        break;

                    case 3:
                        blockString = turnBlockLeftPrime();
                        break;

                    case 4:
                        blockString = turnBlockUp();
                        break;

                    case 5:
                        blockString = turnBlockUpPrime();
                        break;

                    case 6:
                        blockString = turnBlockDown();
                        break;

                    case 7:
                        blockString = turnBlockDownPrime();
                        break;

                    case 8:
                        blockString = turnBlockFront();
                        break;

                    case 9:
                        blockString = turnBlockFrontPrime();
                        break;

                    case 10:
                        blockString = turnBlockBack();
                        break;

                    case 11:
                        blockString = turnBlockBackPrime();
                        break;

                    default:
                        // System.out.println("Invalid Number");
                        break;
                }
            }

        }

        public char[] turnBlockWithString(char direction, int num) {

            if (direction != '\0') {
                switch (direction) {
                    case 'R':
                        return turnBlockRight();

                    case 'r':
                        return turnBlockRightPrime();

                    case 'L':
                        return turnBlockLeft();

                    case 'l':
                        return turnBlockLeftPrime();

                    case 'U':
                        return turnBlockUp();

                    case 'u':
                        return turnBlockUpPrime();

                    case 'D':
                        return turnBlockDown();

                    case 'd':
                        return turnBlockDownPrime();

                    case 'F':
                        return turnBlockFront();

                    case 'f':
                        return turnBlockFrontPrime();

                    case 'B':
                        return turnBlockBack();

                    case 'b':
                        return turnBlockBackPrime();

                    // Should probably throw error
                    default:
                        // System.out.println("Invalid direction");
                        return null;
                }

            }

            else {
                switch (num) {
                    case 0:
                        return turnBlockRight();

                    case 1:
                        return turnBlockRightPrime();

                    case 2:
                        return turnBlockLeft();

                    case 3:
                        return turnBlockLeftPrime();

                    case 4:
                        return turnBlockUp();

                    case 5:
                        return turnBlockUpPrime();

                    case 6:
                        return turnBlockDown();

                    case 7:
                        return turnBlockDownPrime();

                    case 8:
                        return turnBlockFront();

                    case 9:
                        return turnBlockFrontPrime();

                    case 10:
                        return turnBlockBack();

                    case 11:
                        return turnBlockBackPrime();

                    // Should probably throw error
                    default:
                        // System.out.println("Invalid direction");
                        return null;
                }
            }

        }

        /*
         * Block Turns
         * --------------------------------
         * When moving in that direction,
         * look at the block and turn clockwise
         * --------------------------------
         * When moving in the "Prime" direction,
         * look at the block and turn counter-clockwise
         */

        public char[] turnBlockRight() {
            char[] turnedBlock = {
                    // RedSide
                    blockString[0], blockString[1], blockString[2 + 45],
                    blockString[3], blockString[4], blockString[5 + 45],
                    blockString[6], blockString[7], blockString[8 + 45],
                    // BlueSide
                    blockString[9], blockString[10], blockString[11],
                    blockString[12], blockString[13], blockString[14],
                    blockString[15], blockString[16], blockString[17],
                    // OrangeSide
                    blockString[18 + 18], blockString[19], blockString[20],
                    blockString[21 + 18], blockString[22], blockString[23],
                    blockString[24 + 18], blockString[25], blockString[26],
                    // GreenSide
                    blockString[27 + 6], blockString[28 + 2], blockString[29 - 2],
                    blockString[30 + 4], blockString[31], blockString[32 - 4],
                    blockString[33 + 2], blockString[34 - 2], blockString[35 - 6],
                    // YellowSide
                    blockString[36 - 28], blockString[37], blockString[38],
                    blockString[39 - 34], blockString[40], blockString[41],
                    blockString[42 - 40], blockString[43], blockString[44],
                    // WhiteSide
                    blockString[45], blockString[46], blockString[47 - 23],
                    blockString[48], blockString[49], blockString[50 - 29],
                    blockString[51], blockString[52], blockString[53 - 35]
            };

            return turnedBlock;

        }

        public char[] turnBlockRightPrime() {
            char[] turnedBlock = {
                    // RedSide
                    blockString[0], blockString[1], blockString[2 + 40],
                    blockString[3], blockString[4], blockString[5 + 34],
                    blockString[6], blockString[7], blockString[8 + 28],
                    // BlueSide
                    blockString[9], blockString[10], blockString[11],
                    blockString[12], blockString[13], blockString[14],
                    blockString[15], blockString[16], blockString[17],
                    // OrangeSide
                    blockString[18 + 35], blockString[19], blockString[20],
                    blockString[21 + 29], blockString[22], blockString[23],
                    blockString[24 + 23], blockString[25], blockString[26],
                    // GreenSide
                    blockString[27 + 2], blockString[28 + 4], blockString[29 + 6],
                    blockString[30 - 2], blockString[31], blockString[32 + 2],
                    blockString[33 - 6], blockString[34 - 4], blockString[35 - 2],
                    // YellowSide
                    blockString[36 - 18], blockString[37], blockString[38],
                    blockString[39 - 18], blockString[40], blockString[41],
                    blockString[42 - 18], blockString[43], blockString[44],
                    // WhiteSide
                    blockString[45], blockString[46], blockString[47 - 45],
                    blockString[48], blockString[49], blockString[50 - 45],
                    blockString[51], blockString[52], blockString[53 - 45]
            };

            return turnedBlock;

        }

        public char[] turnBlockLeft() {
            char[] turnedBlock = {
                    // RedSide
                    blockString[0 + 44], blockString[1], blockString[2],
                    blockString[3 + 38], blockString[4], blockString[5],
                    blockString[6 + 32], blockString[7], blockString[8],
                    // BlueSide
                    blockString[9 + 6], blockString[10 + 2], blockString[11 - 2],
                    blockString[12 + 4], blockString[13], blockString[14 - 4],
                    blockString[15 + 2], blockString[16 - 2], blockString[17 - 6],
                    // OrangeSide
                    blockString[18], blockString[19], blockString[20 + 31],
                    blockString[21], blockString[22], blockString[23 + 25],
                    blockString[24], blockString[25], blockString[26 + 19],
                    // GreenSide
                    blockString[27], blockString[28], blockString[29],
                    blockString[30], blockString[31], blockString[32],
                    blockString[33], blockString[34], blockString[35],
                    // YellowSide
                    blockString[36], blockString[37], blockString[38 - 18],
                    blockString[39], blockString[40], blockString[41 - 18],
                    blockString[42], blockString[43], blockString[44 - 18],
                    // WhiteSide
                    blockString[45 - 45], blockString[46], blockString[47],
                    blockString[48 - 45], blockString[49], blockString[50],
                    blockString[51 - 45], blockString[52], blockString[53]
            };

            return turnedBlock;
        }

        public char[] turnBlockLeftPrime() {
            char[] turnedBlock = {
                    // RedSide
                    blockString[0 + 45], blockString[1], blockString[2],
                    blockString[3 + 45], blockString[4], blockString[5],
                    blockString[6 + 45], blockString[7], blockString[8],
                    // BlueSide
                    blockString[9 + 2], blockString[10 + 4], blockString[11 + 6],
                    blockString[12 - 2], blockString[13], blockString[14 + 2],
                    blockString[15 - 6], blockString[16 - 4], blockString[17 - 2],
                    // OrangeSide
                    blockString[18], blockString[19], blockString[20 + 18],
                    blockString[21], blockString[22], blockString[23 + 18],
                    blockString[24], blockString[25], blockString[26 + 18],
                    // GreenSide
                    blockString[27], blockString[28], blockString[29],
                    blockString[30], blockString[31], blockString[32],
                    blockString[33], blockString[34], blockString[35],
                    // YellowSide
                    blockString[36], blockString[37], blockString[38 - 32],
                    blockString[39], blockString[40], blockString[41 - 38],
                    blockString[42], blockString[43], blockString[44 - 44],
                    // WhiteSide
                    blockString[45 - 19], blockString[46], blockString[47],
                    blockString[48 - 25], blockString[49], blockString[50],
                    blockString[51 - 31], blockString[52], blockString[53]
            };

            return turnedBlock;
        }

        public char[] turnBlockUp() {
            char[] turnedBlock = {
                    // RedSide
                    blockString[0 + 27], blockString[1 + 27], blockString[2 + 27],
                    blockString[3], blockString[4], blockString[5],
                    blockString[6], blockString[7], blockString[8],
                    // BlueSide
                    blockString[9 - 9], blockString[10 - 9], blockString[11 - 9],
                    blockString[12], blockString[13], blockString[14],
                    blockString[15], blockString[16], blockString[17],
                    // OrangeSide
                    blockString[18 - 9], blockString[19 - 9], blockString[20 - 9],
                    blockString[21], blockString[22], blockString[23],
                    blockString[24], blockString[25], blockString[26],
                    // GreenSide
                    blockString[27 - 9], blockString[28 - 9], blockString[29 - 9],
                    blockString[30], blockString[31], blockString[32],
                    blockString[33], blockString[34], blockString[35],
                    // YellowSide
                    blockString[36 + 6], blockString[37 + 2], blockString[38 - 2],
                    blockString[39 + 4], blockString[40], blockString[41 - 4],
                    blockString[42 + 2], blockString[43 - 2], blockString[44 - 6],
                    // WhiteSide
                    blockString[45], blockString[46], blockString[47],
                    blockString[48], blockString[49], blockString[50],
                    blockString[51], blockString[52], blockString[53]
            };

            return turnedBlock;

        }

        public char[] turnBlockUpPrime() {
            char[] turnedBlock = {
                    // RedSide
                    blockString[0 + 9], blockString[1 + 9], blockString[2 + 9],
                    blockString[3], blockString[4], blockString[5],
                    blockString[6], blockString[7], blockString[8],
                    // BlueSide
                    blockString[9 + 9], blockString[10 + 9], blockString[11 + 9],
                    blockString[12], blockString[13], blockString[14],
                    blockString[15], blockString[16], blockString[17],
                    // OrangeSide
                    blockString[18 + 9], blockString[19 + 9], blockString[20 + 9],
                    blockString[21], blockString[22], blockString[23],
                    blockString[24], blockString[25], blockString[26],
                    // GreenSide
                    blockString[27 - 27], blockString[28 - 27], blockString[29 - 27],
                    blockString[30], blockString[31], blockString[32],
                    blockString[33], blockString[34], blockString[35],
                    // YellowSide
                    blockString[36 + 2], blockString[37 + 4], blockString[38 + 6],
                    blockString[39 - 2], blockString[40], blockString[41 + 2],
                    blockString[42 - 6], blockString[43 - 4], blockString[44 - 2],
                    // WhiteSide
                    blockString[45], blockString[46], blockString[47],
                    blockString[48], blockString[49], blockString[50],
                    blockString[51], blockString[52], blockString[53]
            };

            return turnedBlock;

        }

        public char[] turnBlockDown() {
            char[] turnedBlock = {
                    // RedSide
                    blockString[0], blockString[1], blockString[2],
                    blockString[3], blockString[4], blockString[5],
                    blockString[6 + 9], blockString[7 + 9], blockString[8 + 9],
                    // BlueSide
                    blockString[9], blockString[10], blockString[11],
                    blockString[12], blockString[13], blockString[14],
                    blockString[15 + 9], blockString[16 + 9], blockString[17 + 9],
                    // OrangeSide
                    blockString[18], blockString[19], blockString[20],
                    blockString[21], blockString[22], blockString[23],
                    blockString[24 + 9], blockString[25 + 9], blockString[26 + 9],
                    // GreenSide
                    blockString[27], blockString[28], blockString[29],
                    blockString[30], blockString[31], blockString[32],
                    blockString[33 - 27], blockString[34 - 27], blockString[35 - 27],
                    // YellowSide
                    blockString[36], blockString[37], blockString[38],
                    blockString[39], blockString[40], blockString[41],
                    blockString[42], blockString[43], blockString[44],
                    // WhiteSide
                    blockString[45 + 6], blockString[46 + 2], blockString[47 - 2],
                    blockString[48 + 4], blockString[49], blockString[50 - 4],
                    blockString[51 + 2], blockString[52 - 2], blockString[53 - 6]
            };

            return turnedBlock;
        }

        public char[] turnBlockDownPrime() {
            char[] turnedBlock = {
                    // RedSide
                    blockString[0], blockString[1], blockString[2],
                    blockString[3], blockString[4], blockString[5],
                    blockString[6 + 27], blockString[7 + 27], blockString[8 + 27],
                    // BlueSide
                    blockString[9], blockString[10], blockString[11],
                    blockString[12], blockString[13], blockString[14],
                    blockString[15 - 9], blockString[16 - 9], blockString[17 - 9],
                    // OrangeSide
                    blockString[18], blockString[19], blockString[20],
                    blockString[21], blockString[22], blockString[23],
                    blockString[24 - 9], blockString[25 - 9], blockString[26 - 9],
                    // GreenSide
                    blockString[27], blockString[28], blockString[29],
                    blockString[30], blockString[31], blockString[32],
                    blockString[33 - 9], blockString[34 - 9], blockString[35 - 9],
                    // YellowSide
                    blockString[36], blockString[37], blockString[38],
                    blockString[39], blockString[40], blockString[41],
                    blockString[42], blockString[43], blockString[44],
                    // WhiteSide
                    blockString[45 + 2], blockString[46 + 4], blockString[47 + 6],
                    blockString[48 - 2], blockString[49], blockString[50 + 2],
                    blockString[51 - 6], blockString[52 - 4], blockString[53 - 2]
            };

            return turnedBlock;
        }

        public char[] turnBlockFront() {
            char[] turnedBlock = {
                    // RedSide
                    blockString[0 + 6], blockString[1 + 2], blockString[2 - 2],
                    blockString[3 + 4], blockString[4], blockString[5 - 4],
                    blockString[6 + 2], blockString[7 - 2], blockString[8 - 6],
                    // BlueSide
                    blockString[9], blockString[10], blockString[11 + 34],
                    blockString[12], blockString[13], blockString[14 + 32],
                    blockString[15], blockString[16], blockString[17 + 30],
                    // OrangeSide
                    blockString[18], blockString[19], blockString[20],
                    blockString[21], blockString[22], blockString[23],
                    blockString[24], blockString[25], blockString[26],
                    // GreenSide
                    blockString[27 + 11], blockString[28], blockString[29],
                    blockString[30 + 7], blockString[31], blockString[32],
                    blockString[33 + 3], blockString[34], blockString[35],
                    // YellowSide
                    blockString[36 - 25], blockString[37 - 23], blockString[38 - 21],
                    blockString[39], blockString[40], blockString[41],
                    blockString[42], blockString[43], blockString[44],
                    // WhiteSide
                    blockString[45 - 12], blockString[46 - 16], blockString[47 - 20],
                    blockString[48], blockString[49], blockString[50],
                    blockString[51], blockString[52], blockString[53]
            };

            return turnedBlock;
        }

        public char[] turnBlockFrontPrime() {
            char[] turnedBlock = {
                    // RedSide
                    blockString[0 + 2], blockString[1 + 4], blockString[2 + 6],
                    blockString[3 - 2], blockString[4], blockString[5 + 2],
                    blockString[6 - 6], blockString[7 - 4], blockString[8 - 2],
                    // BlueSide
                    blockString[9], blockString[10], blockString[11 + 25],
                    blockString[12], blockString[13], blockString[14 + 23],
                    blockString[15], blockString[16], blockString[17 + 21],
                    // OrangeSide
                    blockString[18], blockString[19], blockString[20],
                    blockString[21], blockString[22], blockString[23],
                    blockString[24], blockString[25], blockString[26],
                    // GreenSide
                    blockString[27 + 20], blockString[28], blockString[29],
                    blockString[30 + 16], blockString[31], blockString[32],
                    blockString[33 + 12], blockString[34], blockString[35],
                    // YellowSide
                    blockString[36 - 3], blockString[37 - 7], blockString[38 - 11],
                    blockString[39], blockString[40], blockString[41],
                    blockString[42], blockString[43], blockString[44],
                    // WhiteSide
                    blockString[45 - 34], blockString[46 - 32], blockString[47 - 30],
                    blockString[48], blockString[49], blockString[50],
                    blockString[51], blockString[52], blockString[53]
            };

            return turnedBlock;
        }

        public char[] turnBlockBack() {
            char[] turnedBlock = {
                    // RedSide
                    blockString[0], blockString[1], blockString[2],
                    blockString[3], blockString[4], blockString[5],
                    blockString[6], blockString[7], blockString[8],
                    // BlueSide
                    blockString[9 + 33], blockString[10], blockString[11],
                    blockString[12 + 31], blockString[13], blockString[14],
                    blockString[15 + 29], blockString[16], blockString[17],
                    // OrangeSide
                    blockString[18 + 6], blockString[19 + 2], blockString[20 - 2],
                    blockString[21 + 4], blockString[22], blockString[23 - 4],
                    blockString[24 + 2], blockString[25 - 2], blockString[26 - 6],
                    // GreenSide
                    blockString[27], blockString[28], blockString[29 + 24],
                    blockString[30], blockString[31], blockString[32 + 20],
                    blockString[33], blockString[34], blockString[35 + 16],
                    // YellowSide
                    blockString[36], blockString[37], blockString[38],
                    blockString[39], blockString[40], blockString[41],
                    blockString[42 - 7], blockString[43 - 11], blockString[44 - 15],
                    // WhiteSide
                    blockString[45], blockString[46], blockString[47],
                    blockString[48], blockString[49], blockString[50],
                    blockString[51 - 42], blockString[52 - 40], blockString[53 - 38]
            };

            return turnedBlock;
        }

        public char[] turnBlockBackPrime() {
            char[] turnedBlock = {
                    // RedSide
                    blockString[0], blockString[1], blockString[2],
                    blockString[3], blockString[4], blockString[5],
                    blockString[6], blockString[7], blockString[8],
                    // BlueSide
                    blockString[9 + 42], blockString[10], blockString[11],
                    blockString[12 + 40], blockString[13], blockString[14],
                    blockString[15 + 38], blockString[16], blockString[17],
                    // OrangeSide
                    blockString[18 + 2], blockString[19 + 4], blockString[20 + 6],
                    blockString[21 - 2], blockString[22], blockString[23 + 2],
                    blockString[24 - 6], blockString[25 - 4], blockString[26 - 2],
                    // GreenSide
                    blockString[27], blockString[28], blockString[29 + 15],
                    blockString[30], blockString[31], blockString[32 + 11],
                    blockString[33], blockString[34], blockString[35 + 7],
                    // YellowSide
                    blockString[36], blockString[37], blockString[38],
                    blockString[39], blockString[40], blockString[41],
                    blockString[42 - 33], blockString[43 - 31], blockString[44 - 29],
                    // WhiteSide
                    blockString[45], blockString[46], blockString[47],
                    blockString[48], blockString[49], blockString[50],
                    blockString[51 - 16], blockString[52 - 20], blockString[53 - 24]
            };

            return turnedBlock;
        }

        /*
         * Randomize Block
         * ------------------------
         * User gives the number of "random" turns
         * Function will RNG 1 of 12 directions and turn it that way
         */

        public void randomizeBlock(int numTurns) {
            Random rn = new Random(System.currentTimeMillis());
            int r = 0;
            for (int i = 0; i < numTurns; i++) {
                r = rn.nextInt(12);
                switch (r) {
                    case 0:
                        turnBlock('R', 0);
                        break;

                    case 1:
                        turnBlock('r', 0);
                        break;

                    case 2:
                        turnBlock('L', 0);
                        break;

                    case 3:
                        turnBlock('l', 0);
                        break;

                    case 4:
                        turnBlock('U', 0);
                        break;

                    case 5:
                        turnBlock('u', 0);
                        break;

                    case 6:
                        turnBlock('D', 0);
                        break;

                    case 7:
                        turnBlock('d', 0);
                        break;

                    case 8:
                        turnBlock('F', 0);
                        break;

                    case 9:
                        turnBlock('f', 0);
                        break;

                    case 10:
                        turnBlock('B', 0);
                        break;

                    case 11:
                        turnBlock('b', 0);
                        break;

                    default:
                        System.out.println("Random out of bounds");
                        break;
                }
            }
        }
    }
}
