package main;

public class TetrisGrid {
    private boolean [][] grid;

    public TetrisGrid(boolean [][] grid) {
        this.grid = grid;
    }

    public void clearRows() {
        int rowNum = grid.length, colNum = grid[0].length;
        int preCol = 0;
        for (int col = 0; col < colNum; ++col) {
            boolean isAllUsed = true;
            for (int row = 0; row < rowNum; ++row) {
                if (grid[row][col] == false) {
                    isAllUsed = false;
                    break;
                }
            }

            if (!isAllUsed) {
                for (int row = 0; row < rowNum; ++row) {
                    grid[row][preCol] = grid[row][col];
                }
                ++preCol;
            }
        }

        for (int row = 0; row < rowNum; ++row) {
            for (int col = preCol; col < colNum; ++col) {
                grid[row][col] = false;
            }
        }
    }

    public boolean [][] getGrid() {
        return grid;
    }
}
