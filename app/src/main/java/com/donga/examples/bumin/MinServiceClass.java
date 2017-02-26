package com.donga.examples.bumin;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MinServiceClass extends Service {

    private Handler mHandler = new Handler();
    private int mInterval = 5000;

    AppendLog log = new AppendLog();

    public MinServiceClass() {
    }

    @Override
    public void onCreate() {
//        Timer timer = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                log.appendLog("SERVICE TEST");
//            }
//        };
//        timer.schedule(timerTask, mInterval);

//        final Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                log.appendLog("SERVICE TEST");
//                mHandler.postDelayed(this, 5000);
//            }
//        };

        new Thread(new Runnable(){
            public void run() {
                // TODO Auto-generated method stub
                while(true)
                {
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.appendLog("SERVICE TEST");
                }

            }
        }).start();

        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
