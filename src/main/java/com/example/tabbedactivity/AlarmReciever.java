package com.example.tabbedactivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        int notificationId = intent.getIntExtra("notificationId",0);
        String message = intent.getStringExtra("todo");

        Intent mainIntent = new Intent(context,MainActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(context,0,mainIntent,0);


        NotificationManager myNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder builder =  new Notification.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Its Time!!")
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent);

        myNotificationManager.notify(notificationId,builder.build());

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone r = RingtoneManager.getRingtone(context,notification);
        r.play();


    }
}
