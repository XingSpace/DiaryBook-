package com.xing.app.diarybook.Activity;

import android.app.Activity;
import android.content.Context;

import com.xing.app.diarybook.MainActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class MyActivityManager {

    private static Map<String, Activity> mActivityMap = new HashMap<>();

    private MyActivityManager(){}

    public static void addActivity(String tag,Activity activity){
        mActivityMap.put(tag,activity);
    }

    public static void closeApp() {
        Set<String> keySet = mActivityMap.keySet();

        for (String key : keySet) {
            Activity activity = mActivityMap.get(key);
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
                activity = null;
            }
        }

        mActivityMap.clear();

    }

    public static void closeActivity(String tag){
        Activity activity = mActivityMap.get(tag);

        if (activity != null && !activity.isFinishing()) activity.finish();

        mActivityMap.remove(tag);
    }

    public static Context getContext(){
        return mActivityMap.get(MainActivity.class.getSimpleName());
    }

}
