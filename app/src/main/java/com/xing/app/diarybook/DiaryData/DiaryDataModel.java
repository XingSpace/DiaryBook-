package com.xing.app.diarybook.DiaryData;

import com.xing.app.myutils.Utils.DateUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class DiaryDataModel {

    /**
     * 记录日志的 年 月 日
     * 格式：yyyy-MM-dd
     */
    private String stringDate;

    /**
     * 时间戳，记录日志的最后修改时间
     */
    private long longDate;

    /**
     * 日记的具体内容
     */
    private String content;

    public static DiaryDataModel getInstance(String json) {
        DiaryDataModel model = null;
        try {
            JSONObject jO = new JSONObject(json);

            model = new DiaryDataModel(jO.getString("stringDate")
                    , jO.getLong("longDate")
                    , jO.getString("content"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return model;
    }

    public DiaryDataModel() {
        this("");
    }

    public DiaryDataModel(String content) {
        this(DateUtil.getYYYYMMDD(),System.currentTimeMillis(),content);
    }

    private DiaryDataModel(String stringDate, long longDate, String content) {
        this.stringDate = stringDate;
        this.longDate = longDate;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return 记录日志的 年 月 日
     *         格式：yyyy-MM-dd
     */
    public String getStringDate() {
        return stringDate;
    }

    /**
     * @param stringDate
     *
     * @hide 不让外部直接调用, 应该通过这个方法设置时间{@link #setStringDate(long)}
     */
    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    /**
     * 设置日志的日期
     * @param time 时间戳
     */
    public void setStringDate(long time) {
        if (time <= 0) throw new IllegalArgumentException("setStringDate() the time cannot <= 0");
        setStringDate(DateUtil.convert2String(time,DateUtil.yyyyMMdd));
    }

    public long getLongDate() {
        return longDate;
    }

    /**
     * 日志的最后修改时间
     * @param longDate
     */
    public void setLongDate(long longDate) {
        if (longDate <= 0) throw new IllegalArgumentException("setLongDate() the longDate cannot <= 0");
        this.longDate = longDate;
    }

    /**
     * 保存至本地路径的文件名
     * 这里是依赖 {@code stringDate} 的，也就是说这个时间如果发生了变化，那么文件名也可能会发生变化
     *
     * @return 文件名
     */
    public String getFileName() {
        return stringDate +".dia";
    }

    public String toJson() {
        return toJsonObject().toString();
    }

    public JSONObject toJsonObject() {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("fileName", getFileName());
            jsonObject.put("stringDate", getStringDate());
            jsonObject.put("longDate", getLongDate());
            jsonObject.put("content", getContent());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
