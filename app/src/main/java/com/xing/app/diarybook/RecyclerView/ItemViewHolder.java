package com.xing.app.diarybook.RecyclerView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xing.app.diarybook.R;

/**
 * 每个item的实例holder
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    TextView mTextView;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.text);
    }

    public void setText(CharSequence charSequence){
        mTextView.setText(charSequence);
    }


}
