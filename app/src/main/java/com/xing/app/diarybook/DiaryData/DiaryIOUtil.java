package com.xing.app.diarybook.DiaryData;

import android.content.Context;

import com.xing.app.diarybook.Activity.MyActivityManager;
import com.xing.app.myutils.Utils.FileUtil;
import com.xing.app.myutils.Utils.LogUtil;
import com.xing.app.myutils.Utils.PermissionUtil;

import java.io.File;

/**
 * 将日志写入本地或读取本地日志的工具类
 */
public class DiaryIOUtil {

    private Context mContext;

    public static DiaryIOUtil mWrite = null;

    /**
     * 保存日记的文件夹
     */
    public final static String DIARY_DIR = "diary";

    /**
     * 保存日记的文件夹绝对路径
     */
    public String diaryPath;

    public static DiaryIOUtil getInstance() {
        if (mWrite == null) {
            mWrite = new DiaryIOUtil();
        }
        return mWrite;
    }

    private DiaryIOUtil() {

        mContext = MyActivityManager.getContext();

        for (String tmp : PermissionUtil.tPermissions) {
            if (!PermissionUtil.isGotPermission(tmp, mContext)) {
                //todo 没有拿到读写权限
                release();
                return;
            }
        }

        diaryPath = mContext.getDir(DIARY_DIR, Context.MODE_PRIVATE).getAbsolutePath();
        LogUtil.i("The DiaryDirectory is created!");

        DiaryDataModel dataModel = new DiaryDataModel("            小宁子是最胖的，^-^\n\rnabab");

        write(dataModel);

        String modelForLocal = read(dataModel);
        LogUtil.e("测试读写功能->"+modelForLocal);

    }

    public boolean write(DiaryDataModel model) {
        if (model == null)
            throw new NullPointerException("write(DiaryDataModel model) -> The model is null!");

        LogUtil.e("创建一个文件夹");

        File file = FileUtil.createFile(diaryPath, model.getFileName());
        if (file == null) return false;

        //直接覆盖写入
        FileUtil.writeToFile(file, model.toJson(), false);

        return true;
    }

    public DiaryDataModel readDiary(File file) {
        return DiaryDataModel.getInstance(read(file));
    }

    public DiaryDataModel readDiary(String path) {
        return DiaryDataModel.getInstance(read(path));
    }

    /**
     * 这里是根据 {@code model} 从本地文件中读取数据哦
     *
     * @return 从本地读取到的文件
     */
    public String read(DiaryDataModel model) {
        return read(diaryPath + File.separator + model.getFileName());
    }

    public String read(String path) {
        if (path == null || !path.endsWith(".dia"))
            throw new IllegalArgumentException("read(String path)->The path is fault");
        return read(new File(path));
    }

    public String read(File file) {
        return FileUtil.readFile(file);
    }


    public void release() {
        mContext = null;
        mWrite = null;
        System.gc();
    }

}
