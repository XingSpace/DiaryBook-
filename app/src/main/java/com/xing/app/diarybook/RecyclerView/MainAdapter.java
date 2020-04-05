package com.xing.app.diarybook.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xing.app.diarybook.Activity.MyActivityManager;
import com.xing.app.diarybook.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<ItemViewData> mList;

    public MainAdapter(List<ItemViewData> list){
        mList = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(MyActivityManager.getContext())
                .inflate(R.layout.recycler_item,null);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
