package com.bione.corona.ui.base;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bione.corona.R;
import com.bione.corona.network.ApiError;
import com.bione.corona.utils.AppConstant;
import com.bione.corona.utils.CommonUtil;
import com.bione.corona.utils.ProgressDialog;

import java.util.Calendar;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private static final int OVERLAY_TEXT_SIZE_INT = 15;
    private static final int TEN = 10;
    //    private static final String OVERLAY_TEXT = BuildConfig.APP_NAME + "_" + BuildConfig.FLAVOR + "_v" + BuildConfig.VERSION_CODE;
    private BasePresenter mBasePresenter;

    /**
     * Receiver To handle Notification When App is in Foreground state
     */
    private BroadcastReceiver notificationReceiver;
    private AlertDialog mNotificationDialog;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Intent notifyIntent = new Intent(this, MyReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast
//                (getApplicationContext(), 101, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
//                1000 * 60, pendingIntent);

//        notificationReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(final Context context, final Intent intent) {
//
//            }
//        };

//        scheduleNotification(getNotification("5 second delay"), 5000);

//        Intent myIntent = new Intent(this, NotifyService.class);
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        PendingIntent pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.HOUR, 0);
//        calendar.set(Calendar.AM_PM, Calendar.AM);
//        calendar.add(Calendar.DAY_OF_MONTH, 1);
//
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60, pendingIntent);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60*60*24 , pendingIntent);
    }


    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MINUTE, 52);
//        calendar.set(Calendar.HOUR, 5);
//        calendar.set(Calendar.AM_PM, Calendar.PM);
//        calendar.add(Calendar.DAY_OF_MONTH, 1);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, futureInMillis, 10 * 1000, pendingIntent);
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setChannelId(createNotificationChannel());
        builder.setSmallIcon(R.drawable.ic_back);
        return builder.build();
    }

    private String createNotificationChannel() {
// Create the NotificationChannel, but only on API 26+ because
// the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "bione";
            String description = "covid";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("101", name,
                    importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviours after this
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            return "101";
        }
        return "";
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
//        LocalBroadcastManager.getInstance(this).registerReceiver(notificationReceiver, new IntentFilter(AppConstant.NOTIFICATION_RECEIVED));
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void showErrorMessage(final int resId) {
        showErrorMessage(getString(resId), null);
    }

    @Override
    public void showErrorMessage(final String errorMessage) {
        showErrorMessage(errorMessage, null);
    }

    @Override
    public void showErrorMessage(final String errorMessage, final OnErrorHandleCallback mOnErrorHandleCallback) {
        new AlertDialog.Builder(this)
                .setMessage(errorMessage)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        if (mOnErrorHandleCallback != null) {
                            mOnErrorHandleCallback.onErrorCallback();
                        }
                    }
                })
                .show();
    }


    @Override
    public void showErrorMessage(final ApiError apiError) {
        showErrorMessage(apiError, null);
    }

    @Override
    public void showErrorMessage(final ApiError apiError, final OnErrorHandleCallback mOnErrorHandleCallback) {
        if (apiError != null) {
            if (apiError.getStatusCode() == AppConstant.SESSION_EXPIRED) {
                //todo handle session expired case
                CommonUtil.showToast(this, getString(R.string.error_session_expired));
            } else {
                showErrorMessage(apiError.getMessage(), mOnErrorHandleCallback);
            }
        } else {
            showErrorMessage(getString(R.string.error_unexpected_error), mOnErrorHandleCallback);
        }
    }


    @Override
    public boolean isNetworkConnected() {
        return CommonUtil.isNetworkAvailable(this);
    }


    @Override
    public void showLoading() {
        ProgressDialog.showProgressDialog(this);
    }

    @Override
    public void showLoading(final String message) {
        ProgressDialog.showProgressDialog(this, message);
    }

    @Override
    public void hideLoading() {
        ProgressDialog.dismissProgressDialog();
    }


    @Override
    public boolean dispatchTouchEvent(final MotionEvent event) {
        final View view = getCurrentFocus();
        try {
            final boolean ret = super.dispatchTouchEvent(event);
            if (view != null && view instanceof EditText) {
                final View w = getCurrentFocus();
                final int[] scrcoords = new int[2];
                assert w != null;
                w.getLocationOnScreen(scrcoords);
                final float x = event.getRawX() + w.getLeft() - scrcoords[0];
                final float y = event.getRawY() + w.getTop() - scrcoords[1];
                if (event.getAction() == MotionEvent.ACTION_UP
                        && (x < w.getLeft() || x >= w.getRight()
                        || y < w.getTop() || y > w.getBottom())) {
                    final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                }
            }
            return ret;
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method to set toolbar
     */
    public void setToolbar() {

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("");
        ab.setHomeAsUpIndicator(R.drawable.ic_launcher_background);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
