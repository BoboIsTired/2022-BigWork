package com.example.boe_android;

public class Code {
    private static String msg;
    private static int codeID;
    private static String picUrl;

    public int getCodeID() {
        return codeID;
    }

    public String getMsg() {
        return msg;
    }

    public String getPicUrl(){return picUrl;}

    public void setCodeID(int codeID) {
        Code.codeID = codeID;
    }

    public void setMsg(String msg) {
        Code.msg = msg;
    }

    public void setPicUrl(String picUrl){ Code.picUrl=picUrl; }

}
