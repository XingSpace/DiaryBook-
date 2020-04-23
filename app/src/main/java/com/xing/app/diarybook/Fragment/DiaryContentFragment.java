package com.xing.app.diarybook.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xing.app.diarybook.R;
import com.xing.app.diarybook.RecyclerView.ItemViewData;
import com.xing.app.myutils.Utils.LogUtil;

/**
 * 展示日记内容的Fragment
 */
public class DiaryContentFragment extends FragmentBase {

    private ItemViewData mData;

    private TextView mTextView;

    /**
     * 可能会导致空指针异常。。。要在new完之后，加入activity之前，设置好mData
     */
    public DiaryContentFragment() {

    }

    public DiaryContentFragment(ItemViewData data) {
        setData(data);
    }

    public void setData(ItemViewData data) {
        mData = data;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        mTextView = view.findViewById(R.id.content_tv);
        mTextView.setText(mData.getContent());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public String tag() {
        return "DiaryContentFragment";
    }

    /**
     * 在这里拦截掉了整个触摸事件，防止Touch事件穿透到其它View
     * @param me 触摸对象
     * @return true表示消耗掉本次事件
     */
    @Override
    public boolean onTouch(MotionEvent me) {
        LogUtil.e("完成onTouch事件的传递");
        return true;
    }
}
