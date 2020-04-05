package com.xing.app.diarybook.Activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xing.app.diarybook.R;

public abstract class ActivityBase extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyActivityManager.addActivity(getTag(),this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);


        FrameLayout frameLayout = findViewById(R.id.main_layout);
        frameLayout.addView(getLayoutInflater().inflate(layoutResID, null)
                ,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    protected abstract String getTag();

    @Override
    protected void onDestroy() {
        MyActivityManager.closeActivity(getTag());
        super.onDestroy();
    }
}
