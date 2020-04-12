package com.xing.app.diarybook.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xing.app.myutils.Utils.LogUtil;

/**
 * 占坑 2020-4-11
 */
public abstract class FragmentBase extends Fragment implements OnFragmentTouchListener {

    /**
     * @return 针对Fragment类的唯一标识，区别于{@link #getTag()}
     */
    public abstract String tag();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        LogUtil.i("FragmentBase onAttach -> " + tag());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i("FragmentBase onCreate -> " + tag());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.i("FragmentBase onActivityCreated -> " + tag());

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onStart() {
        super.onStart();
        LogUtil.i("FragmentBase onStart -> " + tag());
        getView().setOnTouchListener((view, motionEvent) -> FragmentBase.this.onTouch(motionEvent));
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i("FragmentBase onResume -> " + tag());
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.i("FragmentBase onPause -> " + tag());
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.i("FragmentBase onStop -> " + tag());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.i("FragmentBase onDestroyView -> " + tag());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i("FragmentBase onDestroy -> " + tag());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.i("FragmentBase onDetach -> " + tag());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtil.i("FragmentBase onHiddenChanged -> " + tag() + " :" + hidden);
    }

}
