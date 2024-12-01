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
    public List<Double> getFireflies(@RequestParam String row, @RequestParam String col){

        return sharedObjectService.getPhases();
    }

    @GetMapping(path="/start")
    public int[] start(){
        sharedObjectService.setPhase(-1);
        int[] rc = new int[3];
        rc[0] = sharedObjectService.getRow();
        rc[1] = sharedObjectService.getCol();
        rc[2] = sharedObjectService.getTime();
        return rc;
    }


}