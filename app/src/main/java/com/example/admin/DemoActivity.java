package com.example.admin;
/* ***************************************
 *                                       *
 *  @dookay.com Internet make it happen  *
 *  ---------- ------------------------  *
 *  d d d  d d d   Defending your name   *
 *  o    o     o   I an here, as always  *
 *  k     k    k   As the Goddess wills  *
 *  a    a     a   Your magic is mine    *
 *  y y y  y y y   Don't worry Be happy  *
 *  ---------- ------------------------  *
 *  @dookay.com Internet make it happen  *
 *                                       *
 *****************************************/

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.admin.threedemo.MainActivity;
import com.example.admin.threedemo.R;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*
 * @author：GaoBin
 * @date：2019/1/7  14:15
 * @describe:
 */
public class DemoActivity extends UnityPlayerActivity {
     PopupWindow popupWindow;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            // 用于PopupWindow的View
            mUnityPlayer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if(popupWindow==null){
                        view = getLayoutInflater().inflate(R.layout.layout_close, null);
                        ImageView popUpwindowLayout = (ImageView) view.findViewById(R.id.back);
                        popUpwindowLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    mUnityPlayer.quit();
                                    UnityPlayer.UnitySendMessage("Canvas","quitApp","");
                                    finish();
                                }catch (Exception e){
                                    e.printStackTrace();
                                    finish();
                                }
                            }
                        });
                        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,false);
                        popupWindow.setBackgroundDrawable(new BitmapDrawable());
                        popupWindow.setOutsideTouchable(false);
                        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                        int[] location = new int[2];
                        popupWindow.setBackgroundDrawable(new BitmapDrawable());//注意这里如果不设置，下面的setOutsideTouchable(true);允许点击外部消失会失效
                        mUnityPlayer.getLocationOnScreen(location);
                        popupWindow.showAtLocation(mUnityPlayer, Gravity.NO_GRAVITY, 20, 50);
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        sendServiceUrl();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
    public void sendServiceUrl() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                String url = getIntent().getStringExtra("url");
                UnityPlayer.UnitySendMessage("Canvas","getServiceUrl",url);
            }
        });
    }



}
