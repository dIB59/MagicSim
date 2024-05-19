package com.magic.game.simulation;

import com.magic.game.physics.MovableSpatialElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cell {

    private List<MovableSpatialElement> elements;
    private int numberOfCells;
    private final int cellSize;


    public Cell(int numOfCells, List<MovableSpatialElement> elements, Grid grid) {
        this.numberOfCells = numOfCells;
        this.elements = elements;
        this.cellSize = Math.max(1, grid.getGridWidth() / numOfCells);
    }

}
