class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            if (backtrack(board)) {
                                return true;
                            }
                            // backtrack
                            board[row][col] = '.';
                        }
                    }
                    return false; // no valid number -> backtrack
                }
            }
        }
        return true; // solved
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        // check row & col
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
        }

        // check 3x3 subgrid
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[startRow + r][startCol + c] == num) return false;
            }
        }

        return true;
    }
}
