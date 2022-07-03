package com.example.boe_android;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class WebClient extends WebSocketClient{
    public static final String ACTION_RECEIVE_MESSAGE = "com.jinuo.mhwang.servermanager";
    public static final String KEY_RECEIVED_DATA = "data";
    private static WebClient mWebClient;
    private Context mContext;
    /**
     *  路径为ws+服务器地址+服务器端设置的子路径+参数（这里对应服务器端机器编号为参数）
     *  如果服务器端为https的，则前缀的ws则变为wss
     */
    private static final String mAddress = "ws://192.168.31.170:3000/websocket/";
    private void showLog(String msg){
        Log.d("WebClient---->", msg);
    }
    private WebClient(URI serverUri, Context context){
        super(serverUri, new Draft_6455());
        mContext = context;
        showLog("WebClient");
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        showLog("open->"+handshakedata.toString());
    }

    @Override
    public void onMessage(String message) {
        showLog("onMessage->"+message);
        Code code = new Code();
        String[] tmp = message.split(">");
//        showLog(String.valueOf(tmp.length));
        code.setCodeID(Integer.parseInt(tmp[0]));
        code.setMsg(tmp[1]);
//        sendMessageBroadcast(message);
//        showLog(code.getMsg());
//        showLog(String.valueOf(code.getCodeID()));

        getMsg();

//        return "1";
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        showLog("onClose->"+reason);
    }

    @Override
    public void onError(Exception ex) {
        showLog("onError->"+ex.toString());
    }

    /** 初始化
     * @param vmc_no
     */
    public static void initWebSocket(final Context context, final long vmc_no){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mWebClient = new WebClient(new URI(mAddress+vmc_no), context);
                    try {
                        mWebClient.connectBlocking();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
//
//    /** 发送消息广播
//     * @param message
//     */
////    private void sendMessageBroadcast(String message){
////        if (!message.isEmpty()){
////            Intent intent = new Intent();
////            intent.setAction(ACTION_RECEIVE_MESSAGE);
////            intent.putExtra(KEY_RECEIVED_DATA,message);
//////            showLog("发送收到的消息");
//////            mContext.sendBroadcast(intent);
////        }
////    }

    public void getMsg(){
        Code code = new Code();
//        return code;
        System.out.println(code.getCodeID());
        System.out.println(code.getMsg());
    }



}