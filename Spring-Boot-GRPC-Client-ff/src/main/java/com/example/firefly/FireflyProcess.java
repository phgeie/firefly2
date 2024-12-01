package com.example.firefly;

import com.example.firefly.FireflyService;

public class FireflyProcess {
    private final FireflyService fireflyService;
    private final int interval;

    public FireflyProcess(FireflyService fireflyService, int interval) {
        this.fireflyService = fireflyService;
        this.interval = interval;
    }

    public void start() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(interval);
                    fireflyService.updatePhase();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }
}