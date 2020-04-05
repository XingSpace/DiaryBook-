package com.xing.app.diarybook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xing.app.diarybook.Activity.ActivityBase;
import com.xing.app.diarybook.RecyclerView.ItemViewData;
import com.xing.app.diarybook.RecyclerView.MainAdapter;
import com.xing.app.myutils.Utils.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 日记本项目，开工大吉鸭
 *
 * create by xing 2020-4-5
 */
public class MainActivity extends ActivityBase {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionUtil.permissionEntry(this,getApplicationContext(),true,2333);

        mRecyclerView = findViewById(R.id.recycler);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this,1));
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

        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2333) {
            //权限申请结果返回
        }
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }
}
