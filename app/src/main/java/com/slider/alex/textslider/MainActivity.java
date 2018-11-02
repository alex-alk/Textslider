package com.slider.alex.textslider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AdView mAdView;

    Intent i;
    Spinner spTextColor;
    Spinner spTextBack;
    Spinner spSpeed;
    Spinner spSize;
    EditText editText;

    static String spTextColorStr;
    static String spTextBackStr;
    static String spSpeedStr;
    static String spSizeStr;
    static String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-9428355695867172~2313300168");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        spTextColor = findViewById(R.id.spinTextColor);
        spTextBack = findViewById(R.id.spTextBack);
        spSize = findViewById(R.id.spSize);
        spSpeed = findViewById(R.id.spSpeed);
        editText = findViewById(R.id.editText);

        Display display = getWindowManager().getDefaultDisplay();
        Point psize = new Point();
        display.getSize(psize);
        int y = psize.y;
        float density = getResources().getDisplayMetrics().scaledDensity;

        Spin aaTextColor = new Spin(this);
        SpinB aaTextColorB = new SpinB(this);

        spTextColor.setAdapter(aaTextColor);
        spTextBack.setAdapter(aaTextColorB);

        String[] speed = {"3","0","1","2","4","5"};
        String[] size = {"100%","10%","25%","50%","75%"};

        ArrayAdapter<String> aSpeed= new ArrayAdapter<>(this, R.layout.spinner_item,speed);
        ArrayAdapter<String> aSize = new ArrayAdapter<>(this,R.layout.spinner_item,size);

        spSpeed.setAdapter(aSpeed);
        spSize.setAdapter(aSize);

        i = new Intent(this, GameActivity.class);

        Button btnPlay = findViewById(R.id.button);
        btnPlay.setOnClickListener(this);

        editText.setMaxHeight((int)(density*y*0.1));
        editText.setText(GameActivity.msg);
        if(y<400){
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)btnPlay.getLayoutParams();
            params.setMargins((int)(density*190),(int)(density*15),(int)(density*10),(int)(density*60));
            btnPlay.setLayoutParams(params);
        }
    }

    @Override
    public void onClick(View view) {
        spTextColorStr = spTextColor.getSelectedItem().toString();
        spTextBackStr = spTextBack.getSelectedItem().toString();
        spSpeedStr = spSpeed.getSelectedItem().toString();
        spSizeStr = spSize.getSelectedItem().toString();
        text = editText.getText().toString();
        startActivity(i);
    }
}
