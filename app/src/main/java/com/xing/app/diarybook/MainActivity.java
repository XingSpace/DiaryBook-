package com.xing.app.diarybook;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.xing.app.diarybook.Activity.ActivityBase;
import com.xing.app.myutils.Utils.PermissionUtil;

/**
 * 日记本项目，开工大吉鸭
 *
 * create by xing 2020-4-5
 */
public class MainActivity extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionUtil.permissionEntry(this,getApplicationContext(),true,2333);

//        setTitle("日记本");
//        hideActionBar();
        hideBottomBar();
//        RecyclerView recyclerView
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
