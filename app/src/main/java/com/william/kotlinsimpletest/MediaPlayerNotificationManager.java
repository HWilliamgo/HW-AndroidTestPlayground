package com.william.kotlinsimpletest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.RemoteException;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;


public class MediaPlayerNotificationManager {
    private static final String CHANNEL_ID = "my_channel_01";

    public static final String ACTION_PLAY = "action_play";
    public static final String ACTION_PAUSE = "action_pause";


    private NotificationManager notificationManager;

    private MediaSessionCompat mSession;
    private MediaControllerCompat mController;

    private BroadcastReceiverA receiverA;

    private OnLockScreenPlayerActionListener onLockScreenPlayerActionListener;

    private String title = null;
    private String description = null;

    // <editor-fold defaultstate="collapsed" desc="单例">

    private static class InstanceHolder {
        private static final MediaPlayerNotificationManager INSTANCE = new MediaPlayerNotificationManager();
    }

    private MediaPlayerNotificationManager() {/**/}

    public static MediaPlayerNotificationManager getInstance() {
        return InstanceHolder.INSTANCE;
    }
// </editor-fold>

    public void setOnLockScreenPlayerActionListener(OnLockScreenPlayerActionListener listener) {
        onLockScreenPlayerActionListener = listener;
    }

    public void sendAction(String action) {
        if (mSession == null) {
            initMediaSessions();
        }
        if (receiverA==null){
            receiverA=new BroadcastReceiverA();
            IntentFilter filter=new IntentFilter();
            filter.addAction(ACTION_PAUSE);
            filter.addAction(ACTION_PLAY);
            getApplicationContext().registerReceiver(receiverA,filter);
        }
        switch (action) {
            case ACTION_PLAY:
                mController.getTransportControls().play();
                break;
            case ACTION_PAUSE:
                mController.getTransportControls().pause();
                break;
        }
    }

    public void setTitle() {
    }

    private Context getApplicationContext() {
        return Utils.getApp().getApplicationContext();
    }

    private NotificationCompat.Action generateAction(int icon, String title, String intentAction) {
        Intent intent = new Intent();
        intent.setAction(intentAction);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, 0);
        return new NotificationCompat.Action.Builder(icon, title, pendingIntent).build();
    }


    private void buildNotification(NotificationCompat.Action action) {
        title = ""; // add variable to get current playing song title here
        description = ""; // add variable to get current playing song description  here
        Intent notificationIntent = new Intent(getApplicationContext(), SecondActivity.class); //specify which activity should be opened when widget is clicked (other than buttons)
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);

        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        // Notification channels are only supported on Android O+.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //method to create channel if android version is android. Descrition below
            createNotificationChannel();
        }

        final NotificationCompat.Builder builder;

        builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_launcher))
                .setContentTitle(title)
                .setContentText(description)
                .setContentIntent(contentIntent)
                .setChannelId(CHANNEL_ID)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setOnlyAlertOnce(true)
                .setOngoing(true)
                .setColor(getApplicationContext().getResources().getColor(R.color.colorPrimary))
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        // show only play/pause in compact view
                        .setShowActionsInCompactView(0));

        builder.addAction(action);
        notificationManager.notify(1, builder.build());

    }

    private void initMediaSessions() {
        mSession = new MediaSessionCompat(getApplicationContext(), "simple player session");
        try {
            mController = new MediaControllerCompat(getApplicationContext(), mSession.getSessionToken());
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        mSession.setCallback(new MediaSessionCompat.Callback() {
                                 @Override
                                 public void onPlay() {
                                     super.onPlay();
                                     //add you code for play button click here
                                     //replace your drawable id that shows pauseicon
                                     buildNotification(generateAction(R.drawable.pause, "Pause", ACTION_PAUSE));
                                     LogUtils.d("onPlay");
                                     notifyOnClickPlay();
                                 }

                                 @Override
                                 public void onPause() {
                                     super.onPause();
                                     //add you code for pause button click here
                                     //replace your drawable id that shows play icon
                                     buildNotification(generateAction(R.drawable.play, "Play", ACTION_PLAY));
                                     LogUtils.d("onPause");
                                     notifyOnClickPause();
                                 }
                             }
        );
    }


    //method to create notification channel on android Oreo and above
    @RequiresApi(Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        CharSequence name = "Player Widget";// The user-visible name of the channel. This channel name will be shown in settings.
        if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);


            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void notifyOnClickPlay() {
        if (onLockScreenPlayerActionListener != null) {
            onLockScreenPlayerActionListener.onClickPlay();
        }
    }

    private void notifyOnClickPause() {
        if (onLockScreenPlayerActionListener != null) {
            onLockScreenPlayerActionListener.onClickPause();
        }
    }

    interface OnLockScreenPlayerActionListener {
        /**
         * 点击播放
         */
        void onClickPlay();

        /**
         * 点击暂停
         */
        void onClickPause();
    }

    public class BroadcastReceiverA extends BroadcastReceiver {
        //静态注册的广播，不能是内部类，需要是静态内部类，是否可以动态注册一个呢？


        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtils.d(intent.toString());
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            sendAction(action);
            switch (action) {
                case ACTION_PLAY:
                    notifyOnClickPlay();
                    break;
                case ACTION_PAUSE:
                    notifyOnClickPause();
                    break;
            }
        }
    }
}