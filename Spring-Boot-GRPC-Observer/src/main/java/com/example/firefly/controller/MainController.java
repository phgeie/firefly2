package com.example.firefly.controller;

import com.example.firefly.FireflyClient;
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


    @GetMapping(path="/getFireflies")
    public double[] getFireflies(@RequestParam String host,
                            @RequestParam int port1,
                            @RequestParam int port2,
                            @RequestParam int port3,
                                 @RequestParam int port4){

        double[] res = new double[4];
        FireflyClient fireflyClient1 = new FireflyClient(host, port1);
        FireflyClient fireflyClient2 = new FireflyClient(host, port2);
        FireflyClient fireflyClient3 = new FireflyClient(host, port3);
        FireflyClient fireflyClient4 = new FireflyClient(host, port4);
        res[0] = fireflyClient1.getPhase();
        res[1] = fireflyClient2.getPhase();
        res[2] = fireflyClient3.getPhase();
        res[3] = fireflyClient4.getPhase();
        return res;
    }


}