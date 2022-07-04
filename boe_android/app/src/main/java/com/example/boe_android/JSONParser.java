//package com.example.boe_android;
//
//import android.util.Log;
//
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.ProtocolException;
//import java.net.URL;
//
//public class JSONParser {
//
//    private void networdRequest(){
//        HttpURLConnection connection=null;
//        try {
//            URL url = new URL("172.20.10.3:3000/admin");
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setConnectTimeout(3000);
//            connection.setReadTimeout(3000);
//            //设置请求方式 GET / POST 一定要大小
//            connection.setRequestMethod("POST");
//            connection.setDoInput(true);
//            connection.setDoOutput(false);
//            connection.connect();
//            int responseCode = connection.getResponseCode();
//            if (responseCode != HttpURLConnection.HTTP_OK) {
//                throw new IOException("HTTP error code" + responseCode);
//            }
//            String result = getStringByStream(connection.getInputStream());
//            if (result == null) {
//                Log.d("Fail", "失败了");
//            }else{
//                Log.d("succuss", "成功了 ");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private String getStringByStream(InputStream inputStream){
//        Reader reader;
//        try {
//            reader=new InputStreamReader(inputStream,"UTF-8");
//            char[] rawBuffer=new char[512];
//            StringBuffer buffer=new StringBuffer();
//            int length;
//            while ((length=reader.read(rawBuffer))!=-1){
//                buffer.append(rawBuffer,0,length);
//            }
//            return buffer.toString();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//
////    static InputStream sInputStream = null;
////    static JSONObject SReturnJsonObject = null;
////    static  String sRawJsonString = "";
////    public  JSONParser(){}
////    public JSONObject getJSONFromUrl(String url) {
////        try{
////            URL urlObj = new URL(url);
////            //得到connection对象
////            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
////            //设置请求方式
////            connection.setRequestMethod("GET");
////            //连接
////            connection.connect();
////            //得到响应码
////            int responseCode = connection.getResponseCode();
////            if(responseCode==HttpURLConnection.HTTP_OK) {
////                sInputStream = connection.getInputStream();
////
////            }
////        }catch (Exception e){
////            e.printStackTrace();
////        }
////
////        try{
////            BufferedReader reader = new BufferedReader(new InputStreamReader(sInputStream,"iso-8859-1"),8);
////            StringBuffer
////        }
////    }
////
//}
