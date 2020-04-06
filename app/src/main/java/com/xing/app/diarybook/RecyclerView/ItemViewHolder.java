package com.xing.app.diarybook.RecyclerView;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xing.app.diarybook.R;
import com.xing.app.myutils.Utils.DrawableBuilder;

/**
 * 每个item的实例holder
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    TextView mTextView;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.text);
        Drawable drawable = new DrawableBuilder().setColor(0xff008577).setRadius(5f).create();
        mTextView.setBackground(drawable);
    }

    public void setText(CharSequence charSequence){
        mTextView.setText(charSequence);
    }


    public void setOnClick(View.OnClickListener onClick){
        mTextView.setOnClickListener(onClick);
    }

}
