package com.xing.app.diarybook.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xing.app.diarybook.Fragment.OnFragmentTouchListener;
import com.xing.app.diarybook.R;

import java.util.LinkedList;
import java.util.List;

public abstract class ActivityBase extends AppCompatActivity {

    private ConstraintLayout actionBar, bottomBar;

    private FrameLayout mLoadingLayout;

    private List<OnFragmentTouchListener> mFragmentTouchListeners;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyActivityManager.addActivity(getTag(), this);
        mFragmentTouchListeners = new LinkedList<>();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);

        actionBar = findViewById(R.id.title_layout);
        bottomBar = findViewById(R.id.bottom_layout);
        mLoadingLayout = findViewById(R.id.loading);
        //拦截掉触摸和单击事件
        mLoadingLayout.setOnTouchListener((view, motionEvent) -> true);

        FrameLayout frameLayout = findViewById(R.id.main_layout);
        frameLayout.addView(getLayoutInflater().inflate(layoutResID, null)
                , new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
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

    protected void hideActionBar() {
        actionBar.setVisibility(View.GONE);
    }

    protected void showActionBar() {
        actionBar.setVisibility(View.VISIBLE);
    }

    protected void hideBottomBar() {
        bottomBar.setVisibility(View.GONE);
    }

    protected void showBottomBar() {
        bottomBar.setVisibility(View.VISIBLE);
    }

    protected void hideFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(fragment);
        ft.commit();
    }

    protected void showFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(fragment);
        ft.commit();
    }

    protected void addFragment(int layoutID, Fragment fragment, String tag) {
        addFragment(layoutID, fragment, tag, true);
    }

    /**
     *
     * @param layoutID 目标容器
     * @param fragment 要被加入了Fragment
     * @param tag 唯一标识
     * @param isAddToBackStack 是否加入BackStack回退栈，默认为true
     */
    protected void addFragment(int layoutID, Fragment fragment, String tag, boolean isAddToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(layoutID, fragment, tag);
        if (isAddToBackStack)
            ft.addToBackStack(tag);
        ft.commit();
    }

    protected void removeFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }

    protected boolean isExistFragment(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        return fm.findFragmentByTag(tag) != null;
    }

    protected boolean isExistFragment(int id) {
        FragmentManager fm = getSupportFragmentManager();
        return fm.findFragmentById(id) != null;
    }

    protected boolean isExistFragment(Fragment fragment) {
        return fragment.getActivity() == this;
    }

    protected List<Fragment> getFragments() {
        return getSupportFragmentManager().getFragments();
    }

    /**
     * 弹出加载界面，让用户没法操作
     */
    protected void showLoadingView() {
        mLoadingLayout.setVisibility(View.VISIBLE);
    }

    protected void hideLoadingView() {
        mLoadingLayout.setVisibility(View.GONE);
    }

}
