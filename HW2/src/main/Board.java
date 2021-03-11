package main;

import java.util.Arrays;

/**
 CS108 Tetris Board.
 Represents a Tetris board -- essentially a 2-d grid
 of booleans. Supports tetris pieces and row clearing.
 Has an "undo" feature that allows clients to add and remove pieces efficiently.
 Does not do any drawing or have any idea of pixels. Instead,
 just represents the abstract 2-d board.
 */
public class Board {
    /**
     * Some ivars are stubbed out for you:
     */
    private int width;
    private int height;
    private boolean[][] grid;
    private boolean DEBUG = true;
    boolean committed;
    private int[] widths;
    private int[] heights;
    private int maxHeight;
    private int[] backupWidths;
    private int[] backupHeights;
    private int backupMaxHeight;
    private boolean[][] backupGrid;

    /**
     Creates an empty board of the given width and height
     measured in blocks.
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new boolean[width][height];
        committed = true;
        widths = new int[height];
        heights = new int[width];
        maxHeight = 0;

        backupGrid = new boolean[width][height];
        backupWidths = new int[height];
        backupHeights = new int[width];
        backupMaxHeight = 0;
    }


    /**
     Returns the width of the board in blocks.
     */
    public int getWidth() {
        return width;
    }


    /**
     Returns the height of the board in blocks.
     */
    public int getHeight() {
        return height;
    }


    /**
     Returns the max column height present in the board.
     For an empty board this is 0.
     */
    public int getMaxHeight() {
        return maxHeight;
    }


    /**
     * Checks the board for internal consistency -- used
     * for debugging.
     */
    public void sanityCheck() {
        if (DEBUG) {
            int [] verifyHeights = new int[width];
            int [] verifyWidths = new int[height];
            int verifyMaxHeight = 0;
            for (int x = 0; x < width; ++x) {
                for (int y = 0; y < height; ++y) {
                    if (grid[x][y]) {
                        verifyHeights[x] = Math.max(verifyHeights[x], y + 1);
                        ++verifyWidths[y];
                        verifyMaxHeight = Math.max(verifyMaxHeight, verifyHeights[x]);
                    }
                }
            }

            assert(maxHeight == verifyMaxHeight);
            assert(Arrays.equals(heights, verifyHeights));
            assert(Arrays.equals(widths, verifyWidths));
        }
    }

    /**
     Given a piece and an x, returns the y
     value where the piece would come to rest
     if it were dropped straight down at that x.

     <p>
     Implementation: use the skirt and the col heights
     to compute this fast -- O(skirt length).
     */
    public int dropHeight(Piece piece, int x) {
        int w = piece.getWidth();
        int res = 0;
        int [] skirt = piece.getSkirt();

        for (int i = 0; i < w; ++i) {
            int col = i + x;
            int offset = getColumnHeight(col) - skirt[i];
            if (offset > 0 && res < offset) {
                res = offset;
            }
        }

        sanityCheck();
        return res;
    }


    /**
     Returns the height of the given column --
     i.e. the y value of the highest block + 1.
     The height is 0 if the column contains no blocks.
     */
    public int getColumnHeight(int x) {
        if (0 <= x && x < width) {
            return heights[x];
        }
        return 0;
    }


    /**
     Returns the number of filled blocks in
     the given row.
     */
    public int getRowWidth(int y) {
        if (0 <= y && y < height) {
            return widths[y];
        }
        return 0;
    }


    /**
     Returns true if the given block is filled in the board.
     Blocks outside of the valid width/height area
     always return true.
     */
    public boolean getGrid(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height || grid[x][y];
    }


    public static final int PLACE_OK = 0;
    public static final int PLACE_ROW_FILLED = 1;
    public static final int PLACE_OUT_BOUNDS = 2;
    public static final int PLACE_BAD = 3;

    /**
     Attempts to add the body of a piece to the board.
     Copies the piece blocks into the board grid.
     Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
     for a regular placement that causes at least one row to be filled.

     <p>Error cases:
     A placement may fail in two ways. First, if part of the piece may falls out
     of bounds of the board, PLACE_OUT_BOUNDS is returned.
     Or the placement may collide with existing blocks in the grid
     in which case PLACE_BAD is returned.
     In both error cases, the board may be left in an invalid
     state. The client can use undo(), to recover the valid, pre-place state.
     */
    public int place(Piece piece, int x, int y) {
        // flag !committed problem
        if (!committed) {
            throw new RuntimeException("place commit problem");
        }
        committed = false;
        makeBackup();
        int result = PLACE_OK;

        for (TPoint p : piece.getBody()) {
            int nextX = p.x + x;
            int nextY = p.y + y;

            if (nextX < 0 || nextX >= width || nextY < 0 || nextY >= height) {
                return PLACE_OUT_BOUNDS;
            }
            if (grid[nextX][nextY]) {
                return PLACE_BAD;
            }

            grid[nextX][nextY] = true;
            heights[nextX] = Math.max(heights[nextX], nextY + 1);
            maxHeight = Math.max(maxHeight, heights[nextX]);
            if (++widths[nextY] == width) {
                result = PLACE_ROW_FILLED;
            }
        }

        sanityCheck();
        return result;
    }

    private void makeBackup() {
        for (int i = 0; i < width; ++i) {
            System.arraycopy(grid[i], 0, backupGrid[i], 0, height);
        }
        System.arraycopy(widths, 0, backupWidths, 0, height);
        System.arraycopy(heights, 0, backupHeights, 0, width);
        backupMaxHeight = maxHeight;
    }


    /**
     Deletes rows that are filled all the way across, moving
     things above down. Returns the number of rows cleared.
     */
    public int clearRows() {
        int rowsCleared = 0;
        committed = false;
        int pre = 0;
        for (int y = 0; y < height; ++y) {
            if (widths[y] == width) {
                ++rowsCleared;
                for (int x = 0; x < width; ++x) {
                    --heights[x];
                }
                --maxHeight;
            }
            else if (widths[y] == 0) {
                break;
            }
            else {
                for (int x = 0; x < width; ++x) {
                    grid[x][pre] = grid[x][y];
                }
                widths[pre] = widths[y];
                ++pre;
            }
        }

        for (int x = 0; x < width; ++x) {
            for (int y = pre; y < height; ++y) {
                widths[y] = 0;
                grid[x][y] = false;
            }
        }

        sanityCheck();
        return rowsCleared;
    }



    /**
     Reverts the board to its state before up to one place
     and one clearRows();
     If the conditions for undo() are not met, such as
     calling undo() twice in a row, then the second undo() does nothing.
     See the overview docs.
     */
    public void undo() {
        if (!committed) {
            committed = true;
            boolean[][] tmpGrid = grid;
            grid = backupGrid;
            backupGrid = tmpGrid;

            int[] tmpWidths = widths;
            widths = backupWidths;
            backupWidths = tmpWidths;

            int[] tmpHeights = heights;
            heights = backupHeights;
            backupHeights = tmpHeights;

            int tmpHeight = maxHeight;
            maxHeight = backupMaxHeight;
            backupMaxHeight = tmpHeight;

            sanityCheck();
        }
    }


    /**
     Puts the board in the committed state.
     */
    public void commit() {
        committed = true;
    }


    /**
     * Renders the board state as a big String, suitable for printing.
     * This is the sort of print-obj-state utility that can help see complex
     * state change over time.
     * (provided debugging utility)
     */
    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();
        for (int y = height - 1; y >= 0; --y) {
            buff.append('|');
            for (int x = 0; x < width; ++x) {
                if (getGrid(x,y)) {
                    buff.append('+');
                }
                else {
                    buff.append(' ');
                }
            }
            buff.append("|\n");
        }
        for (int x = 0; x < width + 2; ++x) {
            buff.append('-');
        }
        return (buff.toString());
    }
}
