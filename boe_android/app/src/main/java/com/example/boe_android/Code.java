package com.example.boe_android;

public class Code {
    private static String msg;
    private static int codeID;

    public int getCodeID() {
        return codeID;
    }

    public String getMsg() {
        return msg;
    }

    public void setCodeID(int codeID) {
        Code.codeID = codeID;
    }

    public void setMsg(String msg) {
        Code.msg = msg;
    }
}
