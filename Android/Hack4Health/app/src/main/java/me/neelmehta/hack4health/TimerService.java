package me.neelmehta.hack4health;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by neel on 05/11/16 at 10:06 AM.
 */

public class TimerService extends Service {
    // Stores list of timers and names for the timers
    private ArrayList<CountDownTimer> timers = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            // Fetch length and name from intent
            long length = intent.getLongExtra("length", 0);
            final String name = intent.getStringExtra("name");

            // Add to the ArrayLists
            names.add(name);
            timers.add(new CountDownTimer(length, 100) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.d("timer", names.get(timers.indexOf(this)));
                }

                @Override
                public void onFinish() {
                    // Send notification on finish
                    Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(TimerService.this)
                            .setSmallIcon(R.mipmap.goodshows_logo_white)
                            .setAutoCancel(true)
                            .setContentTitle(getString(R.string.app_name))
                            .setContentText(names.get(timers.indexOf(this)))
                            .setLights(Color.BLUE, 2000, 2000)
                            .setVibrate(new long[]{250, 250, 250, 250})
                            .setSound(defaultSoundUri);

                    NotificationManager notificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                    notificationManager.notify(5, notificationBuilder.build());

                    Log.d("service", "notification sent");
                }
            }.start());
        } else {
            Log.d("service", "intent was null lol");
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
