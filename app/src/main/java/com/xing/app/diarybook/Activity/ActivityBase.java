package com.xing.app.diarybook.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.xing.app.diarybook.R;

public abstract class ActivityBase extends AppCompatActivity {

    ConstraintLayout actionBar,bottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyActivityManager.addActivity(getTag(),this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);

        actionBar = findViewById(R.id.title_layout);
        bottomBar = findViewById(R.id.bottom_layout);

        FrameLayout frameLayout = findViewById(R.id.main_layout);
        frameLayout.addView(getLayoutInflater().inflate(layoutResID, null)
                ,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    protected abstract String getTag();

    @Override
    public void setTitle(int titleId) {
        setTitle(getResources().getString(titleId));
    }

    @Override
    public void setTitle(CharSequence title) {
        TextView textView = actionBar.findViewById(R.id.title);
        textView.setText(title);
    }

    protected void hideActionBar(){
        actionBar.setVisibility(View.GONE);
    }

    protected void hideBottomBar(){
        bottomBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        MyActivityManager.closeActivity(getTag());
        super.onDestroy();
    }
}
