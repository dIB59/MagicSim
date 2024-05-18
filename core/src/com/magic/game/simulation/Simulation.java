package com.magic.game.simulation;

import com.magic.game.physics.MovableSpatialElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Simulation implements CollisionHandler {

    private final HashMap<Integer, ArrayList<MovableSpatialElement>> cells;
    private final Map<Integer, List<ArrayList<MovableSpatialElement>>> surroundingCellsCache;
    private final int gridWidth;
    private final int gridHeight;
    private final int cellSize;
    private final ArrayList<MovableSpatialElement> elements;

    public Simulation(ArrayList<MovableSpatialElement> elements, int gridWidth, int gridHeight, int numOfCells) {
        this.elements = elements;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.cellSize = Math.max(1, gridWidth / numOfCells); // Ensure at least 1 cell per dimension

        // Initialize divisions with empty lists for each cell
        cells = new HashMap<>();
        for (int y = 0; y < gridHeight; y += cellSize) {
            for (int x = 0; x < gridWidth; x += cellSize) {
                cells.put(getKey(x, y), new ArrayList<>());
            }
        }

        // Assign particles to their corresponding cells
        for (MovableSpatialElement particle : elements) {
            int cellX = (int) (particle.getX() / cellSize);
            int cellY = (int) (particle.getY() / cellSize);
            cells.get(getKey(cellX * cellSize, cellY * cellSize)).add(particle);
        }

        this.surroundingCellsCache = new HashMap<>();
        for (int y = 0; y < gridHeight; y += cellSize) {
            for (int x = 0; x < gridWidth; x += cellSize) {
                int key = getKey(x, y);
                this.surroundingCellsCache.put(key, computeSurroundingCells(x / cellSize, y / cellSize));
            }
        }
    }

    public void run() {

    }

    public ArrayList<MovableSpatialElement> getElements() {
        return elements;
    }

    public void removeElement(MovableSpatialElement particle) {
        this.elements.removeIf(p -> p.getId() == particle.getId());
    }

    public List<ArrayList<MovableSpatialElement>> getSurroundingCells(int cellX, int cellY) {
        int key = getKey(cellX * cellSize, cellY * cellSize);
        return surroundingCellsCache.get(key);
    }

    private List<ArrayList<MovableSpatialElement>> computeSurroundingCells(int cellX, int cellY) {
        List<ArrayList<MovableSpatialElement>> surroundingCells = new ArrayList<>();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int neighborX = cellX + dx;
                int neighborY = cellY + dy;

                if (dx == 0 && dy == 0) {
                    continue;
                }

                // Check if neighbor cell is within grid bounds
                if (neighborX >= 0 && neighborX < gridWidth / cellSize &&
                        neighborY >= 0 && neighborY < gridHeight / cellSize) {
                    surroundingCells.add(this.cells.get(this.getKey(neighborX * cellSize, neighborY * cellSize)));
                }
            }
        }

        return surroundingCells;
    }

    private int getKey(int x, int y) {
        return y * gridWidth + x;
    }

}
