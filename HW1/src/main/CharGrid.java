package main;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class CharGrid {
    private static char [] [] grid = new char [] [] {
            {'c', 'a', 'x'},
            {'b', ' ', 'b'},
            {' ', ' ', 'a'}
    } ;

    public int charArea(char ch) {
        int minRow = 3, maxRow = -1, minCol = 3, maxCol = -1;
        boolean isFind = false;
        final int rowNum = grid.length;
        final int colNum = grid[0].length;

        for (int i = 0; i < rowNum; ++i) {
            for (int j = 0; j < colNum; ++j) {
                if (grid[i][j] == ch) {
                    minRow = min(minRow, i);
                    maxRow = max(maxRow, i);
                    minCol = min(minCol, j);
                    maxCol = max(maxCol, j);
                    isFind = true;
                }
            }
        }

        return isFind ? (maxRow - minRow + 1) * (maxCol - minCol + 1) : 0;
    }

    private static char [] [] charMap = new char[][] {
            {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', 'p', ' ', ' ', ' ', ' ', 'x', ' '},
            {'p', 'p', 'p', 'p', 'p', ' ', 'x', 'x', 'x'},
            {' ', ' ', 'p', ' ', ' ', 'y', ' ', 'x', ' '},
            {' ', ' ', 'p', ' ', 'y', 'y', 'y', ' ', ' '},
            {'z', 'z', 'z', 'z', 'z', 'y', 'z', 'z', 'z'},
            {' ', ' ', 'x', 'x', ' ', 'y', ' ', ' ', ' '}
    };

    private static final int MIN_ARM_LENGHT = 2;

    public int countPlus() {
        int res = 0;
        final int rowNum = charMap.length;
        final int colNum = charMap[0].length;

        for (int i = 0; i < rowNum; ++i) {
            for (int j = 0; j < colNum; ++j) {
                if (charMap[i][j] != ' ') {
                    res = max(res, calculateArmLength(i, j));
                }
            }
        }

        return res < 2 ? 0 : res;
    }

    private int calculateArmLength(int rowPos, int colPos) {
        char ch = charMap[rowPos][colPos];
        int up = countNum(ch, rowPos, colPos, -1, 0);
        int down = countNum(ch, rowPos, colPos, 1, 0);
        int left = countNum(ch, rowPos, colPos, 0, -1);
        int right = countNum(ch, rowPos, colPos, 0, 1);

        return min(up, min(down, min(left, right)));
    }

    private int countNum(char ch, int rowPos, int colPos, int rowOffset, int colOffset) {
        int nextRow = rowPos + rowOffset;
        int nextCol = colPos + colOffset;

        if (0 <= nextRow && nextRow < charMap.length && 0 <= nextCol
                && nextCol < charMap[0].length && charMap[nextRow][nextCol] == ch) {
            return 1 + countNum(ch, nextRow, nextCol, rowOffset, colOffset);
        }

        return 0;
    }

}
