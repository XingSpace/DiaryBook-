package com.xing.app.diarybook;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xing.app.diarybook.Activity.ActivityBase;
import com.xing.app.diarybook.DiaryData.DiaryIOUtil;
import com.xing.app.diarybook.Interface.OnItemClickListener;
import com.xing.app.diarybook.RecyclerView.ItemViewData;
import com.xing.app.diarybook.RecyclerView.MainAdapter;
import com.xing.app.myutils.Utils.LogUtil;
import com.xing.app.myutils.Utils.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 日记本项目，开工大吉鸭
 * <p>
 * create by xing 2020-4-5
 */
public class MainActivity extends ActivityBase implements OnItemClickListener {

    private RecyclerView mRecyclerView;

    private FrameLayout exception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showLoadingView();

        if (PermissionUtil.permissionEntry(this, getApplicationContext(), true, 2333)) {
            init();
        }

    }

    private void init() {

        hideLoadingView();

        mRecyclerView = findViewById(R.id.recycler);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        List<ItemViewData> list = new ArrayList<>();
        list.add(new ItemViewData("测试数据呀，小宁子是最胖的"));
        list.add(new ItemViewData("测试数据呀，小宁子是最胖的"));
        list.add(new ItemViewData("测试数据呀，小宁子是最胖的"));
        list.add(new ItemViewData("测试数据呀，小宁子是最胖的"));
        list.add(new ItemViewData("测试数据呀，小宁子是最胖的"));
        list.add(new ItemViewData("测试数据呀，小宁子是最胖的"));
        list.add(new ItemViewData("测试数据呀，小宁子是最胖的"));
        list.add(new ItemViewData("测试数据呀，小宁子是最胖的"));
        list.add(new ItemViewData("测试数据呀，小宁子是最胖的"));
        list.add(new ItemViewData("测试数据呀，小宁子是最胖的"));
        list.add(new ItemViewData("测试数据呀，小宁子是最胖的"));
        list.add(new ItemViewData("测试数据呀，小宁子是最胖的"));

        MainAdapter adapter = new MainAdapter(list);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);

        DiaryIOUtil.getInstance();

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

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }

    @Override
    public void onItemClick(int position) {
        LogUtil.e("被点击到了的->" + position);
        showLoadingView();

    }
}
