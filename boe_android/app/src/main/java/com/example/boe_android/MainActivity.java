package com.example.boe_android;

//import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

    private ImageView imageview;

//    private Code currentCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //调用webClient
        WebClient.initWebSocket(this,10086);


        imageview = (ImageView) this.findViewById(R.id.imageView);


    }



    public void changePic(String picUrl){
        imageview = (ImageView) this.findViewById(R.id.imageView);
            try {
            Bitmap bitmap = getBitmap(picUrl);
            imageview.setImageBitmap(bitmap);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


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


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

//        Code codeText = new Code();
//        int fontSize = Integer.parseInt(codeText.getFontSize());
//        String textBody = codeText.getTextBody();
//
//        String textPosition = codeText.getFontPosition();
//        int position = 1;
//        if(textPosition.equals("中")){
//            position=5;
//        }
//        else if(textPosition.equals("下"))
//            position = 10;
//
//        String fontColor = codeText.getFontColor();
//        //创建布局对象
//        RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativelayout);
//        rl.removeAllViews();
//            //将HorseView1加入布局中
//        HorseView horseview1 = new HorseView(this);
//        rl.addView(horseview1);
//
//        //设置HorseView的布局属性
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) horseview1.getLayoutParams();
//        layoutParams.setMargins(0, position * 150, 0, 0);
//        layoutParams.width = 1200;
//        layoutParams.height = 150;
//        horseview1.setLayoutParams(layoutParams);
//        horseview1.setScrollWidth(layoutParams.width); //滚动框的宽度
//        horseview1.setCoordinateY(150 / 2 + 50 / 2);
//        horseview1.setCurrentPosition(layoutParams.width);//设置滚动信息从右边出来
//        horseview1.setSpeed(4); //文字播放速度
//        horseview1.setColor(Color.parseColor(fontColor));
//        horseview1.setTextSize(fontSize);
//        horseview1.setText(textBody);
//        horseview1.setIndex(1);
//
//        horseview1.start();
    }

}

