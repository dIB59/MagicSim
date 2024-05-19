package com.magic.game.simulation;

import com.magic.game.physics.MovableSpatialElement;
import com.magic.game.physics.SpatialElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


public class Simulation implements CollisionHandler {

    private final Grid grid;
    private final float dt;
    @Getter
    private final List<MovableSpatialElement> elements;

    public Simulation(List<MovableSpatialElement> elements, float timeStep) {

        this.grid = new Grid(1000, 1000, 10);
        this.elements = elements;
        this.dt = timeStep;
    }

    public Simulation(List<MovableSpatialElement> elements, float timeStep, Grid grid) {

        this.grid = new Grid(1000, 1000, 10);
        this.elements = elements;
        this.dt = timeStep;
    }

    public void run() {

        for (MovableSpatialElement element: elements) {
//            elements.stream().peek(e -> resolveCollision(e, element));
            System.out.println(element.getId());
            updatePosition(element);
        }
    }

    public void removeElement(MovableSpatialElement particle) {
        this.elements.removeIf(p -> p.getId() == particle.getId());
    }

//    private List<List<MovableSpatialElement>> getSurroundingCells(int cellX, int cellY) {
//        int key = getKey(cellX * this.grid.getCellSize(), cellY * this.grid.getCellSize());
//        return surroundingCellsCache.get(key);
//    }

    private void updatePosition(MovableSpatialElement element) {
        float newX = (float) (element.getX() + element.getVelocityX() * dt);
        float newY = (float) (element.getY() + element.getVelocityY() * dt);

        element.setXCoordinate(newX);
        element.setYCoordinate(newY);
    }

    //TODO - This should be an array of size 8 not a List
//    private List<List<MovableSpatialElement>> computeSurroundingCells(int cellX, int cellY) {
//        List<List<MovableSpatialElement>> surroundingCells = new ArrayList<>();
//
//        for (int dx = -1; dx <= 1; dx++) {
//            for (int dy = -1; dy <= 1; dy++) {
//                int neighborX = cellX + dx;
//                int neighborY = cellY + dy;
//
//                if (dx == 0 && dy == 0) {
//                    continue;
//                }
//
//                // Check if neighbor cell is within grid bounds
//                if (neighborX >= 0 && neighborX < gridWidth / cellSize &&
//                        neighborY >= 0 && neighborY < gridHeight / cellSize) {
//                    surroundingCells.add(this.cells.get(this.getKey(neighborX * cellSize, neighborY * cellSize)));
//                }
//            }
//        }
//
//        @SuppressWarnings("unchecked")
//        List<MovableSpatialElement>[] array = new List[surroundingCells.size()];
//        array = surroundingCells.toArray(array);
//
//        return surroundingCells;
//    }

//    private int getKey(int x, int y) {
//        return y * gridWidth + x;
//    }

    @Override
    public boolean isColliding(SpatialElement element, SpatialElement element2) {
        throw new RuntimeException();
    }
}
