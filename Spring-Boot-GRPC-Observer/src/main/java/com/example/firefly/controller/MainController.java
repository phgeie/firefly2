package com.example.firefly.controller;

import com.example.firefly.FireflyClient;
import com.example.firefly.service.SharedObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController // This means that this class is a Controller
@RequestMapping(path="/data") // This means URL's start with /data (after Application path)
public class MainController {
    private SharedObjectService sharedObjectService;

    public void setPhase(SharedObjectService sharedObjectService) {
        this.sharedObjectService = sharedObjectService;
    }


    @GetMapping(path="/getFireflies")
    public double[] getFireflies(@RequestParam String row, @RequestParam String col){

        int rowNum = Integer.parseInt(row);
        int colNum = Integer.parseInt(col);
        FireflyClient[] fireflyClient = new FireflyClient[rowNum * colNum];
        double[] res = new double[rowNum*colNum];
        for (int i = 0; i < rowNum * colNum; i++) {
            fireflyClient[i] = new FireflyClient("localhost", 50051+i);
            res[i] = fireflyClient[i].getPhase();
        }
        
        return res;
    }

    @GetMapping(path="/start")
    public int start(){
        System.out.println("tetstetset");
        sharedObjectService.setPhase(-1);
        return 0;
    }


}