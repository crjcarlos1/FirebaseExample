package com.cralos.firebase2.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.cralos.firebase2.R;
import com.cralos.firebase2.activities.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import static android.graphics.Color.rgb;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static final String TAG = "NOTIFICATIONS";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e(TAG, "FROM: " + remoteMessage.getFrom());

        if (remoteMessage.getData().isEmpty()) {
            showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        } else {
            showNotification(remoteMessage.getData());
        }

        //if (remoteMessage.getData().size() > 0) {
        //    Log.e(TAG, "MESSAGE DATA PAYLOAD: " + remoteMessage.getData());
        //}
    }

    private void showNotification(String title, String body) {
        String NOTIFICATIONS_CHANNEL_ID = "customChannelId";
        buildNotification(title, body, NOTIFICATIONS_CHANNEL_ID);
    }


    private void showNotification(Map<String, String> map) {
        String title = map.get("title");
        String body = map.get("body");
        String NOTIFICATION_CHANNEL_ID = "customChannelId";
        buildNotification(title, body, NOTIFICATION_CHANNEL_ID);
    }

    private void buildNotification(String title, String body, String NOTIFICATIONS_CHANNEL_ID) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATIONS_CHANNEL_ID);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher_round)
                .setSmallIcon(R.drawable.ic_notifications)
                .setColor(rgb(0, 100, 100))
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setVibrate(new long[]{0, 1000, 500, 1000})
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setContentInfo("Info");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        /*SINCE ANDROID OREO  NOTIFICATION CHANNEL IS NEEDED*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATIONS_CHANNEL_ID, "NAME_NOTIFICATION", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(0/*id notification*/, notificationBuilder.build());
    }

}
