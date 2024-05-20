package com.magic.game.simulation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    @Getter
    private final int width;
    @Getter
    private final int height;
    @Getter
    private final List<Cell> cells;
//    private final Map<Integer, List<Cell>> surroundingCellsCache;

    public Grid(int gridWidth, int gridHeight, int numOfCells) {
        this.width = gridWidth;
        this.height = gridHeight;
        this.cells = new ArrayList<>();
//        this.surroundingCellsCache = surroundingCellsCache;
    }

    public int getCellSize(){
        return this.cells.size();
    }

//    public void addElement(MovableSpatialElement element) {
//        int cellX = (int) (element.getX() / cellSize);
//        int cellY = (int) (element.getY() / cellSize);
//    }

//    public List<List<MovableSpatialElement>> getSurroundingCells(int cellX, int cellY) {
//        int key = getKey(cellX * cellSize, cellY * cellSize);
//        return surroundingCellsCache.get(key);
//    }

//    private List<List<MovableSpatialElement>> computeSurroundingCells(int cellX, int cellY) {
//
//    }

//    private int getKey(int x, int y) {
//        return y * gridWidth + x;
//    }
}
