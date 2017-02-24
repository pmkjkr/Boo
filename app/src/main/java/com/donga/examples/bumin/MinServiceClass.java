package com.donga.examples.bumin;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MinServiceClass extends Service {

    private Handler mHandler;
    private int mInterval = 5000;

    AppendLog log = new AppendLog();

    public MinServiceClass() {
    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            try {
                log.appendLog("SERVICE TEST");
            } finally {
                mHandler.postDelayed(r, mInterval);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
