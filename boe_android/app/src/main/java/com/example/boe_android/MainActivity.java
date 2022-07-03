package com.example.boe_android;

//import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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
import android.widget.TextView;


public class MainActivity extends Activity {

    private Button btn;
    private ImageView imageview;

//    private Code currentCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //调用webClient
        WebClient.initWebSocket(this,10086);


//        Code code = new Code();

        btn = (Button) this.findViewById(R.id.button);
        imageview = (ImageView) this.findViewById(R.id.imageView);

        StrictMode.setThreadPolicy(new
                StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
//                btn.setEnabled(false);

                Code code = new Code();
                TextView textview = (TextView) findViewById(R.id.textView3);
                textview.setText(code.getMsg());
                String strURL = code.getMsg();
                try {
                    Bitmap bitmap = getBitmap(strURL);
                    imageview.setImageBitmap(bitmap);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
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
}