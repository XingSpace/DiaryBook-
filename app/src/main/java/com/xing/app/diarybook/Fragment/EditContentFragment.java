package com.xing.app.diarybook.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.xing.app.diarybook.DiaryData.DiaryDataModel;
import com.xing.app.diarybook.R;


public class EditContentFragment extends FragmentBase {

    private EditText editText;

    private DiaryDataModel mModel;

    public EditContentFragment() {

    }

    public EditContentFragment(DiaryDataModel model) {
        setData(model);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_content, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            editText = view.findViewById(R.id.edit);
        }
        editText.setText(mModel.getContent());
    }

    public void setData(DiaryDataModel model) {
        if (model == null) return;
        mModel = model;
    }

    /**
     * @return 修改之后的日记内容
     */
    public DiaryDataModel getDiaryDataModel() {
        if (mModel == null) {
            mModel = new DiaryDataModel();
            mModel.setLongDate(System.currentTimeMillis());
            mModel.setStringDate(System.currentTimeMillis());
        }

        mModel.setContent(editText.getText().toString());

        return mModel;
    }

    @Override
    public String tag() {
        return "EditContentFragment";
    }

    @Override
    public boolean onTouch(MotionEvent me) {
        return true;
    }
}
