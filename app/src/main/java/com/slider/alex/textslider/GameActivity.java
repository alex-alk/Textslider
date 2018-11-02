package com.slider.alex.textslider;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Display;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM;

public class GameActivity extends AppCompatActivity {

    static String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView tv = findViewById(R.id.textView5);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int y = size.y-100;
        int x =size.x;

        float density = getResources().getDisplayMetrics().scaledDensity;
        String spSizeStr = MainActivity.spSizeStr;
        float textSize=0;
        switch(spSizeStr){
            case "100%":
                textSize = y/density;
                break;
            case "50%":
                textSize = (y+50)/density/2;
                break;
            case "10%":
                textSize = (y+90)/density/10;
                break;
            case "25%":
                textSize = (y+75)/density/4;
                break;
            case "75%":
                textSize = (y+25)/density/1.3f;
        }
        tv.setTextSize(textSize);

        ConstraintLayout frame = findViewById(R.id.frame);
        ViewGroup.LayoutParams a=  frame.getLayoutParams();

        String bcolorstr = MainActivity.spTextBackStr;
        String colorstr = MainActivity.spTextColorStr;
        int bcolor=Integer.parseInt(bcolorstr);
        int color=Integer.parseInt(colorstr);

        frame.setBackgroundColor(bcolor);
        tv.setTextColor(color);
        tv.setText(MainActivity.text);
        msg = MainActivity.text;

        tv.measure(0,0);
        if(tv.getMeasuredWidth()>x)
            a.width = tv.getMeasuredWidth();
        else
            a.width = x;
        frame.setLayoutParams(a);

        int speed = Integer.parseInt(MainActivity.spSpeedStr);
        int width = tv.getMeasuredWidth();
        float duration = width*15/(density*speed);
        if(speed!=0) {
            TranslateAnimation animation = new TranslateAnimation(x, -width, 0, 0);
            animation.setDuration((int) duration);
            animation.setInterpolator(new LinearInterpolator());
            animation.setFillAfter(true);
            animation.setRepeatMode(Animation.RESTART);
            animation.setRepeatCount(Animation.INFINITE);
            tv.startAnimation(animation);
        }
    }
}
