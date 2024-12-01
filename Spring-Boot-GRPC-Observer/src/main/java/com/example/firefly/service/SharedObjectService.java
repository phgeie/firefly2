package com.example.firefly.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SharedObjectService {
    private double phase = 0.0;
    public List<Double> phases;
    public int row;
    public int col;
    public int time;

    public double getPhase() {
        return phase;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    public List<Double> getPhases() {
        return phases;
    }

    public void setPhase(double phase) {
        System.out.println("Phase wurde auf -1 gesettet");
        this.phase = phase;
    }

    public void setPhases(List<Double> phases) {
        this.phases = phases;
        System.out.println("phases: " + phases);
    }
}
