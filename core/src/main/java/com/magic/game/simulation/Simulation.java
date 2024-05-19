package com.magic.game.simulation;

import com.magic.game.physics.MovableSpatialElement;
import com.magic.game.physics.SpatialElement;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class Simulation implements CollisionHandler {

    private final HashMap<Integer, List<MovableSpatialElement>> cells;
    private final Map<Integer, List<List<MovableSpatialElement>>> surroundingCellsCache;
    private final int gridWidth;
    private final int gridHeight;
    private final int cellSize;
    private final float dt;
    private final List<MovableSpatialElement> elements;

    public Simulation(List<MovableSpatialElement> elements, int gridWidth, int gridHeight, int numOfCells, float timeStep) {
        this.elements = elements;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.dt = timeStep;
        this.cellSize = Math.max(1, gridWidth / numOfCells);

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

        for (MovableSpatialElement element: elements) {
            elements.stream().peek(e -> resolveCollision(e, element));
            updatePosition(element);
        }
    }

    public List<MovableSpatialElement> getElements() {
        return elements;
    }

    public void removeElement(MovableSpatialElement particle) {
        this.elements.removeIf(p -> p.getId() == particle.getId());
    }

    private List<List<MovableSpatialElement>> getSurroundingCells(int cellX, int cellY) {
        int key = getKey(cellX * cellSize, cellY * cellSize);
        return surroundingCellsCache.get(key);
    }

    private void updatePosition(MovableSpatialElement element) {
        float newX = (float) (element.getX() + element.getVelocityX() * dt);
        float newY = (float) (element.getY() + element.getVelocityY() * dt);

        element.setXCoordinate(newX);
        element.setYCoordinate(newY);
    }

    //TODO - This should be an array of size 8 not a List
    private List<List<MovableSpatialElement>> computeSurroundingCells(int cellX, int cellY) {
        List<List<MovableSpatialElement>> surroundingCells = new ArrayList<>();

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

//        @SuppressWarnings("unchecked")
//        List<MovableSpatialElement>[] array = new List[surroundingCells.size()];
//        array = surroundingCells.toArray(array);

        return surroundingCells;
    }

    private int getKey(int x, int y) {
        return y * gridWidth + x;
    }

    @Override
    public boolean isColliding(SpatialElement element, SpatialElement element2) {
        throw new RuntimeException();
    }
}
