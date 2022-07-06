package com.example.boe_android;

import static android.text.TextUtils.isEmpty;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import android.annotation.SuppressLint;
@SuppressLint("AppCompatCustomView")


public class HorseView extends TextView {

    private boolean stopMarquee;//是否停止滚动
    private String text;//文本内容
    private float coordinateX;//当前滚动位置
    private float coordinateY;//当前滚动位置
    private float textWidth;//文本宽度
    private int scrollWidth;//滚动区域宽度
    private int speed;//滚动速度
    private int index=0;
    private int color;


    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public float getCurrentPosition(){
        return coordinateX;
    }
    public void setCurrentPosition(float coordinateX){
        this.coordinateX=coordinateX;
    }
    public float getCoordinateY() {
        return coordinateY;
    }
    public void setCoordinateY(float coordinateY) {
        this.coordinateY = coordinateY;
    }
    public int getScrollWidth() {
        return scrollWidth;
    }
    public void setScrollWidth(int scrollWidth) {
        this.scrollWidth = scrollWidth;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public HorseView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    public void setText(String text){
        this.text=text;
        textWidth =getPaint().measureText(text);
    }
    public void start(){
        int offsettime=0;
        offsettime=(index-1)*scrollWidth/speed*30; //第一个字从开始的位置滚动到滚动框最左边的时间
        Log.d(VIEW_LOG_TAG, "=============offsettime is "+offsettime);

        handler.sendEmptyMessageDelayed(0, offsettime);
    }
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    //当文字走到最左边
                    if(coordinateX < (- textWidth - scrollWidth)){

                        coordinateX = 0;//文字滚动完了，重新开始滚动

                        invalidate();//无限循环
                        if(!stopMarquee){
                            sendEmptyMessageDelayed(0, 30);
                        }
                    }
                    else{
                        coordinateX -= speed;//开始文字滚动
                        invalidate();
                        if(!stopMarquee){
                            sendEmptyMessageDelayed(0, 30);
                        }
                    }
                    break;
            }
            super.handleMessage(msg);
        }

    };
    @Override
    public void draw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.draw(canvas);
        if(!isEmpty(text)){
            Log.d(VIEW_LOG_TAG, "=============draw index is "+index+"==============");
            Paint p=getPaint();
            p.setColor(getColor());
            if(coordinateX < (- (textWidth  - scrollWidth))){
                text += text;
                textWidth =getPaint().measureText(text);
                invalidate();
            }
            textWidth =getPaint().measureText(text);
            canvas.drawText(text, coordinateX, coordinateY, p);
        }
    }

}