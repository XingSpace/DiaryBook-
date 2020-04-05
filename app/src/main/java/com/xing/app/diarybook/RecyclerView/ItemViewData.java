package com.xing.app.diarybook.RecyclerView;

/**
 * 每个Item的数据包
 */
public class ItemViewData {

    private CharSequence mCharSequence;

    public ItemViewData(CharSequence charSequence){
        mCharSequence = charSequence;
    }

    public CharSequence getText(){
        return mCharSequence;
    }

}
