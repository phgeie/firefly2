package com.example.firefly.service;

import org.springframework.stereotype.Service;

@Service
public class SharedObjectService {
    private double phase = 0.0;

    public double getPhase() {
        return phase;
    }

    public void setPhase(double phase) {
        System.out.println("Phase wurde auf -1 gesettet");
        this.phase = phase;
    }
}
