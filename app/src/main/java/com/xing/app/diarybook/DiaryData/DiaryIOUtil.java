package com.xing.app.diarybook.DiaryData;

import android.content.Context;

import com.xing.app.diarybook.Activity.MyActivityManager;
import com.xing.app.myutils.Utils.FileUtil;
import com.xing.app.myutils.Utils.LogUtil;
import com.xing.app.myutils.Utils.PermissionUtil;

import java.io.File;
import java.util.Arrays;

/**
 * 将日志写入本地或读取本地日志的工具类
 */
public class DiaryIOUtil {

    private Context mContext;

    public static DiaryIOUtil ioUtil = null;

    /**
     * 保存日记的文件夹
     */
    public final static String DIARY_DIR = "diary";

    /**
     * 保存日记的文件夹绝对路径
     */
    public String diaryPath;

    public static DiaryIOUtil getInstance() {
        if (ioUtil == null) {
            ioUtil = new DiaryIOUtil();
        }
        return ioUtil;
    }

    private DiaryIOUtil() {

        mContext = MyActivityManager.getContext();

        for (String tmp : PermissionUtil.tPermissions) {
            if (!PermissionUtil.isGotPermission(tmp, mContext)) {
                //没有拿到读写权限
                release();
                return;
            }
        }

        diaryPath = mContext.getDir(DIARY_DIR, Context.MODE_PRIVATE).getAbsolutePath();
        LogUtil.i("The DiaryDirectory is created!");

    }

    /**
     * @return 排序后的日志文件
     */
    public File[] listFileSort() {

        File[] files = listFiles();

        //升序排列
        Arrays.sort(files);

        return files;
    }

    /**
     * @return 获得排序后的 文件名数组
     */
    public String[] listSort() {

        String[] name = list();

        //对数组进行升序排列
        Arrays.sort(name);

        return name;
    }

    /**
     * 获取日记目录下的所有文件名
     *
     * @return 返回一个数组，如果数组为空，则表示当前一篇日记都没有
     */
    public String[] list() {
        return mContext.getDir(DIARY_DIR, Context.MODE_PRIVATE).list();
    }

    /**
     * 获取日记目录下所有的文件
     *
     * @return 返回一个文件数组，每个元素表示一个一篇日志单位
     */
    public File[] listFiles() {
        return mContext.getDir(DIARY_DIR, Context.MODE_PRIVATE).listFiles();
    }

    /**
     * 将日记写入文件
     *
     * @param model 日记对象
     * @return 写入成功返回true
     */
    public boolean write(DiaryDataModel model) {
        if (model == null)
            throw new NullPointerException("write(DiaryDataModel model) -> The model is null!");

        LogUtil.e("创建一个文件夹");

        File file = FileUtil.createFile(diaryPath, model.getFileName());
        if (file == null) return false;

        //直接覆盖写入
        return FileUtil.writeToFile(file, model.toJson(), false);
    }

    /**
     * 从文件中读取日志，并解码成DiaryDataModel对象
     */
    public DiaryDataModel readDiary(File file) {
        return DiaryDataModel.getInstance(read(file));
    }

    /**
     * 从文件中读取日志，并解码成DiaryDataModel对象
     */
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
        if (file == null || !file.getName().endsWith(".dia"))
            throw new IllegalArgumentException("read(File file)->The file is fault");
        return FileUtil.readFile(file);
    }

    /**
     * 释放资源
     */
    public void release() {
        mContext = null;
        ioUtil = null;
        System.gc();
    }

}
