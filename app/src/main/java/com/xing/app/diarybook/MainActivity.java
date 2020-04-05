package com.xing.app.diarybook;

import android.os.Bundle;

import com.xing.app.diarybook.Activity.ActivityBase;

/**
 * 日记本项目，开工大吉鸭
 *
 * create by xing 2020-4-5
 */
public class MainActivity extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }
}
