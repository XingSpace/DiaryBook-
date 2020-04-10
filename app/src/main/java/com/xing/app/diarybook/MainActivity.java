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

    private RecyclerView mRecyclerView;

    private FrameLayout exception_fl;

    private TextView exception_tv;

    private DiaryIOUtil mDiaryIOUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    private void loadDiary() {
        File[] files = mDiaryIOUtil.listFileSort();
        if (files.length <= 0) {
            hideLoadingView();
            onException("还没有写过日记哦");
            return;
        }

        List<ItemViewData> list = new ArrayList<>();

        for (File file : files) {
            DiaryDataModel dataModel = mDiaryIOUtil.readDiary(file);
            if (dataModel != null) {
                list.add(new ItemViewData(dataModel));
            }
        }

        MainAdapter adapter = new MainAdapter(list);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.scrollToPosition(adapter.getItemCount()-1);

        hideLoadingView();
    }

    @Override
    public void onItemClick(int position) {
        LogUtil.e("被点击到了的->" + position);

    }

    /**
     * 解除异常状态
     */
    protected void cancelException() {
        exception_fl.setVisibility(View.GONE);
    }

    /**
     * 发生异常时回调
     * @param tips
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
    protected void onDestroy() {
        mRecyclerView.removeAllViews();
        exception_fl = null;
        exception_tv = null;
        mDiaryIOUtil.release();
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != 2333) return;
        for (int i : grantResults){
            if (i != 0) {
                return;
            }
        }
        init();
    }
}
