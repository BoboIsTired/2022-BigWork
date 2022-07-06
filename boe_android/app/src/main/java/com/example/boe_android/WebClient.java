package com.example.boe_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class WebClient extends WebSocketClient{
    public static final String ACTION_RECEIVE_MESSAGE = "com.jinuo.mhwang.servermanager";
    public static final String KEY_RECEIVED_DATA = "data";
    private static WebClient mWebClient;
    private ImageView imageview;
    private Context mContext;
    /**
     *  路径为ws+服务器地址+服务器端设置的子路径+参数（这里对应服务器端机器编号为参数）
     *  如果服务器端为https的，则前缀的ws则变为wss
     */
    private static final String mAddress = "ws://118.31.244.173:3000/websocket/";
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
        networkRequest(code.getCodeID(), code.getMsg());
//        doCode();

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
    private void networkRequest(Integer cordId,String id){

        String url1 = "";
        if(cordId==1){
//
            url1 = "http://118.31.244.173:3000/programs/android?id="+id;
            HttpURLConnection connection=null;
            try {

                URL url = new URL(url1);
//            url="http://172.20.10.3:3000/admin"
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(3000);
                connection.setReadTimeout(3000);
                //设置请求方式 GET / POST 一定要大小
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.setDoOutput(false);
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    throw new IOException("HTTP error code" + responseCode);
                }
//            String id = JSON.()
                String result = getStringByStream(connection.getInputStream());
                JSONObject res = new JSONObject(result);
                Code code = new Code();

                code.setPicUrl(res.get("programMaterial").toString());
                code.setPicUrl("http://118.31.244.173:3000/file/"+code.getPicUrl());
                System.out.println(code.getPicUrl());
                imageview = (ImageView) ((Activity)this.mContext).findViewById(R.id.imageView);
                try {
                    Bitmap bitmap = getBitmap(code.getPicUrl());
                    ((Activity)this.mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageview.setImageBitmap(bitmap);

                        }
                    });

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



                if (result == null) {
                    Log.d("Fail", "失败了");
                }else{
                    Log.d("succuss", result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            url1 = "http://118.31.244.173:3000/notice/android?id="+id;
            HttpURLConnection connection=null;
            try {

                URL url = new URL(url1);
//            url="http://172.20.10.3:3000/admin"
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(3000);
                connection.setReadTimeout(3000);
                //设置请求方式 GET / POST 一定要大小
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.setDoOutput(false);
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    throw new IOException("HTTP error code" + responseCode);
                }
//            String id = JSON.()
                String result = getStringByStream(connection.getInputStream());
                JSONObject res = new JSONObject(result);
                Code code = new Code();

//                code.setPicUrl(res.get("programMaterial").toString());
//                code.setPicUrl("http://172.20.10.3:3000/imgs/"+code.getPicUrl());
                code.setFontPosition(res.get("fontPosition").toString());
                code.setTextBody(res.get("body").toString());
                code.setFontSize(res.get("fontSize").toString());
                code.setFontColor(res.get("fontColor").toString());

                System.out.println(code.getTextBody());
                RelativeLayout rl = (RelativeLayout) ((Activity)this.mContext).findViewById(R.id.relativelayout);
//                imageview = (ImageView) ((Activity)this.mContext).findViewById(R.id.imageView);
                try {
//                    Bitmap bitmap = getBitmap(code.getPicUrl());

                    ((Activity)this.mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            imageview.setImageBitmap(bitmap);
                            Code codeText = new Code();
                            int fontSize = Integer.parseInt(codeText.getFontSize());
                            String textBody = codeText.getTextBody()+"     ";

                            String textPosition = codeText.getFontPosition();
                            int position = 0;
                            if(textPosition.equals("中")){
                                position=2;
                            }
                            else if(textPosition.equals("下"))
                                position = 5;

                            String fontColor = codeText.getFontColor();
                            rl.removeAllViews();
                            //将HorseView1加入布局中
                            HorseView horseview1 = new HorseView(mContext);
                            rl.addView(horseview1);

                            //设置HorseView的布局属性
                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) horseview1.getLayoutParams();
                            layoutParams.setMargins(0, position * 150, 0, 0);
                            layoutParams.width = 2000;
                            layoutParams.height = 800;
                            horseview1.setLayoutParams(layoutParams);
                            horseview1.setScrollWidth(layoutParams.width); //滚动框的宽度
//                            horseview1.setScrollWidth(layoutParams.height);
                            horseview1.setCoordinateY(500 / 2 );
                            horseview1.setCurrentPosition(layoutParams.width);//设置滚动信息从右边出来
                            horseview1.setSpeed(4); //文字播放速度
                            horseview1.setColor(Color.parseColor(fontColor));
                            horseview1.setTextSize(fontSize);
                            horseview1.setText(textBody);
                            horseview1.setIndex(1);

                            horseview1.start();

                        }
                    });

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



                if (result == null) {
                    Log.d("Fail", "失败了");
                }else{
                    Log.d("succuss", result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }
    private String getStringByStream(InputStream inputStream){
        Reader reader;
        try {
            reader=new InputStreamReader(inputStream,"UTF-8");
            char[] rawBuffer=new char[512];
            StringBuffer buffer=new StringBuffer();
            int length;
            while ((length=reader.read(rawBuffer))!=-1){
                buffer.append(rawBuffer,0,length);
            }
            return buffer.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

//    public void getMsg(){
//        Code code = new Code();
////        return code;
//        System.out.println(code.getCodeID());
//        System.out.println(code.getMsg());
//    }

    public Bitmap getBitmap(String path) throws IOException {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }



}