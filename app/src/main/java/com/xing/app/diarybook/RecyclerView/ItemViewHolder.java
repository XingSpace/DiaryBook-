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

    TextView content,time;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        content = itemView.findViewById(R.id.text);
        time = itemView.findViewById(R.id.time);

        Drawable drawable = new DrawableBuilder().setColor(0xff008577).setRadius(5f).create();
        content.setBackground(drawable);
    }

    public void setContent(CharSequence charSequence){
        content.setText(charSequence);
    }

    public void setOnClick(View.OnClickListener onClick){
        content.setOnClickListener(onClick);
    }

    public void setTime(String s) {
        time.setText(s);
    }

}
