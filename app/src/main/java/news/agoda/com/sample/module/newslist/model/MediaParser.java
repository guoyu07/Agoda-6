package news.agoda.com.sample.module.newslist.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class MediaParser {

    private static final String TAG = MediaParser.class.getSimpleName();

    List<NewsEntity> parseMediaContent(String content) {
        List<NewsEntity> newsItemList = new ArrayList<>();
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(content);
            JSONArray resultArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject newsObject = resultArray.getJSONObject(i);
                NewsEntity newsEntity = new NewsEntity(newsObject);
                newsItemList.add(newsEntity);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Fail to parse json string = "+e);
        }
        return newsItemList;
    }
}
