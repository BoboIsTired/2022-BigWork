package com.example.demo.controller;


import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/android")
public class CodeController {

    @PostMapping(value = "/operation")
    public String sendCmd(@RequestBody Map<String, Object> map) {
        String msg = map.get("msg").toString();
        String codeID = map.get("codeId").toString();

//        String message = new Gson().toJson(codeID+msg);
        String message = codeID + ">" +msg;
        System.out.println("message in json is :"+message);

        return WebSocket.sendMessage(message,10086);

    }

    @RequestMapping(value = "/test")
    public String test(){
        System.out.print("test");
        return "hello world";
    }

}
