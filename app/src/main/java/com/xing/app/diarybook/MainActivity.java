package com.xing.app.diarybook;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xing.app.diarybook.Activity.ActivityBase;
import com.xing.app.diarybook.DiaryData.DiaryDataModel;
import com.xing.app.diarybook.DiaryData.DiaryIOUtil;
import com.xing.app.diarybook.Fragment.DiaryContentFragment;
import com.xing.app.diarybook.Interface.OnItemClickListener;
import com.xing.app.diarybook.RecyclerView.ItemViewData;
import com.xing.app.diarybook.RecyclerView.MainAdapter;
import com.xing.app.myutils.Utils.LogUtil;
import com.xing.app.myutils.Utils.PermissionUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 日记本项目，开工大吉鸭
 * <p>
 * create by xing 2020-4-5
 */
public class MainActivity extends ActivityBase implements OnItemClickListener {

    //展示日志的RecyclerView
    private RecyclerView mRecyclerView;

    //展示异常的layout
    private FrameLayout exception_fl;

    //用于展示异常语的textview
    private TextView exception_tv;

    //日记读写工具
    private DiaryIOUtil mDiaryIOUtil;

    //搜索到的日记对象合集
    private List<ItemViewData> mDataList;

    private DiaryContentFragment mContentFragment;
    public static final String fragmentTag = "content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        int[] arr = new int[3];
//        int i = arr[6];

        findViews();

        showLoadingView();

        if (PermissionUtil.permissionEntry(this, getApplicationContext(), true, 2333)) {
            init();
        }

    }

    /**
     * 绑定控件
     */
    protected void findViews() {
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        exception_fl = findViewById(R.id.exception_fl);

        exception_tv = findViewById(R.id.exception_tv);

    }

    private void init() {
        mDiaryIOUtil = DiaryIOUtil.getInstance();

        loadDiary();
    }

    /**
     * 从本地加载日志出来
     * 如果没有日志，会显示一个 exception
     */
    private void loadDiary() {
        File[] files = mDiaryIOUtil.listFileSort();
        if (files.length <= 0) {
            hideLoadingView();
            onException("还没有写过日记哦");
            return;
        }

        mDataList = new ArrayList<>();

        for (File file : files) {
            DiaryDataModel dataModel = mDiaryIOUtil.readDiary(file);
            if (dataModel != null) {
                mDataList.add(new ItemViewData(dataModel));
            }
        }

        MainAdapter adapter = new MainAdapter(mDataList);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.scrollToPosition(adapter.getItemCount() - 1);

        hideLoadingView();
    }

    @Override
    public void onItemClick(int position) {
        LogUtil.e("被点击到了的->" + position);

        if (mContentFragment == null) {
            mContentFragment = new DiaryContentFragment();
        }

        mContentFragment.setData(mDataList.get(position));
        addFragment(R.id.fragment_root, mContentFragment, fragmentTag);

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().popBackStackImmediate()) {
            return;
        }
        super.onBackPressed();
    }

    /**
     * 解除异常状态
     */
    protected void cancelException() {
        exception_fl.setVisibility(View.GONE);
    }

    /**
     * 发生异常时回调
     *
     * @param tips 提示语
     */
    protected void onException(String tips) {
        exception_fl.setVisibility(View.VISIBLE);
        exception_tv.setText(tips);
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected void onStart() {
        LogUtil.e("MainActivity onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        LogUtil.e("MainActivity onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        LogUtil.e("MainActivity onDestroy");
        mRecyclerView.removeAllViews();
        exception_fl = null;
        exception_tv = null;
        mDiaryIOUtil.release();
        super.onDestroy();
        if (mContentFragment != null) mContentFragment.onDestroy();
    }

    @Override
    protected void onStop() {
        LogUtil.e("MainActivity onStop");
        super.onStop();
        if (mContentFragment != null) mContentFragment.onStop();
    }

    @Override
    protected void onPause() {
        LogUtil.e("MainActivity onPause");
        super.onPause();
        if (mContentFragment != null) mContentFragment.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != 2333) return;
        for (int i : grantResults) {
            if (i != 0) {
                return;
            }
        }
        init();
    }
}
