package com.xing.app.diarybook.RecyclerView;

import com.xing.app.diarybook.DiaryData.DiaryDataModel;

/**
 * 每个Item的数据包
 */
public class ItemViewData {

    /**
     * 预览文字
     */
    private CharSequence mCharSequence;

    /**
     * 时间字符串
     */
    private String strTime;

    /**
     * 日记对象
     */
    private DiaryDataModel mDataModel;

    public ItemViewData(DiaryDataModel model) {
        mDataModel = model;
        init();
    }

    /**
     * @param charSequence 预览内容
     * @deprecated 主要是 {@link #ItemViewData(DiaryDataModel)} 靠谱
     */
    public ItemViewData(CharSequence charSequence) {
        mCharSequence = charSequence;
    }

    private void init() {
        if (mDataModel != null) {
            setContent(mDataModel.getContent());
            setStrTime(mDataModel.getStringDate());
        }
    }

    public CharSequence getContent() {
        return mCharSequence;
    }

    public void setContent(CharSequence charSequence) {
        mCharSequence = charSequence;
    }

    public void setStrTime(String s) {
        strTime = s;
    }

    public String getStrTime() {
        return strTime;
    }

    public DiaryDataModel getDataModel() {
        return mDataModel;
    }

    public void setDataModel(DiaryDataModel dataModel) {
        mDataModel = dataModel;
    }
}
