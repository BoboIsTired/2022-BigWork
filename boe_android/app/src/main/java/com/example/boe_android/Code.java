package com.example.boe_android;

public class Code {
    private static String msg;
    private static int codeID;
    private static String picUrl;
    private static String textBody="SB   ";
    private static String fontSize="25";
    private static String fontColor="#000000";
    private static String fontPosition="上";

    public int getCodeID() {
        return codeID;
    }

    public String getMsg() {
        return msg;
    }

    public String getPicUrl(){return picUrl;}

    public String getTextBody(){ return textBody; }

    public String getFontSize(){ return fontSize; }

    public String getFontColor() { return fontColor; }

    public String getFontPosition() { return fontPosition; }

    public void setCodeID(int codeID) { Code.codeID = codeID; }

    public void setMsg(String msg) {
        Code.msg = msg;
    }

    public void setPicUrl(String picUrl){ Code.picUrl=picUrl; }

    public void setTextBody(String textBody){ Code.textBody = textBody; }

    public void setFontSize(String fontSize){ Code.fontSize = fontSize; }

    public void setFontColor(String fontColor){ Code.fontColor = fontColor; }

    public void setFontPosition(String fontPosition){ Code.fontPosition = fontPosition; }

}
