package fr.univartois.butinfo.ihm;
import java.util.Random;

public class GameGrid {
    private boolean[][] lights = new boolean[5][5];

    public void init() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                lights[i][j] = false;
            }
        }
    }

    public void switchAt(int row, int column) {
        toggle(row, column);
        toggle(row - 1, column);
        toggle(row + 1, column);
        toggle(row, column - 1);
        toggle(row, column + 1);
    }

    private void toggle(int row, int column) {
        if (row >= 0 && row < 5 && column >= 0 && column < 5) {
            lights[row][column] = !lights[row][column];
        }
    }

    public boolean isOn(int row, int column) {
        return lights[row][column];
    }

    public boolean isOff() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (lights[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void randomInit() {
        Random random = new Random();
        init(); 
        for (int i = 0; i < 15; i++) { 
            int row = random.nextInt(5);
            int col = random.nextInt(5);
            switchAt(row, col);
        }
    }
}