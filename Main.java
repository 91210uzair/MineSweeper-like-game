import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int gridSize = 5;
        int numMines = 5;
        char[][] grid = new char[gridSize][gridSize];
        boolean[][] mines = new boolean[gridSize][gridSize];

        // Initialize grid with empty cells
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = '-';
                mines[i][j] = false;
            }
        }

        // Place mines randomly on the grid
        for (int i = 0; i < numMines; i++) {
            int row = random.nextInt(gridSize);
            int col = random.nextInt(gridSize);
            mines[row][col] = true;
        }

        System.out.println("Welcome to the Minesweeper Game!");
        printGrid(grid);

        while (true) {
            System.out.print("Enter row (0-" + (gridSize - 1) + ") and column (0-" + (gridSize - 1) + ") to reveal: ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (row < 0 || row >= gridSize || col < 0 || col >= gridSize) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            if (mines[row][col]) {
                System.out.println("Oops! You hit a mine. Game over!");
                grid[row][col] = '*';
                printGrid(grid);
                break;
            } else {
                int numMinesAround = countMinesAround(mines, row, col);
                grid[row][col] = (char) ('0' + numMinesAround);
                printGrid(grid);
            }
        }

        scanner.close();
    }

    public static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static int countMinesAround(boolean[][] mines, int row, int col) {
        int count = 0;
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < dr.length; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];
            if (newRow >= 0 && newRow < mines.length && newCol >= 0 && newCol < mines[0].length && mines[newRow][newCol]) {
                count++;
            }
        }

        return count;
    }
}
