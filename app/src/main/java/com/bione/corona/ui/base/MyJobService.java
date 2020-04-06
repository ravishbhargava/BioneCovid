package com.bione.corona.ui.base;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.bione.corona.utils.Log;

public class MyJobService extends JobService {

    private static String TAG = "MyJobService";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "onStartJob");
        doBackground(jobParameters);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "onStopJob");
        jobCancelled = true;
        return true;
    }


    private void doBackground(JobParameters jobParameters) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Log.d(TAG, "job : " + i);
                    if (jobCancelled) {
                        return;

                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "job finished");
                    jobFinished(jobParameters, false);
                }
            }
        }).start();
    }
}
