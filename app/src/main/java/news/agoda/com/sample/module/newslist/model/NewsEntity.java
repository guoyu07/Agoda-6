package news.agoda.com.sample.module.newslist.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents a news item
 */
public class NewsEntity {
    private static final String TAG = NewsEntity.class.getSimpleName();

    public static final String CONST_TITLE = "title";
    public static final String CONST_SUMMARY = "abstract";
    public static final String CONST_URL = "url";
    public static final String CONST_MULTIMEDIA = "multimedia";
    public static final String CONST_IMAGE_URL = "imageURL";

    public String title;
    public String summary;
    public String articleUrl;
    public List<MediaEntity> mediaEntityList;

    public NewsEntity() {}

    NewsEntity(JSONObject jsonObject) {
        try {
            mediaEntityList = new ArrayList<>();
            title = jsonObject.getString(CONST_TITLE);
            summary = jsonObject.getString(CONST_SUMMARY);
            articleUrl = jsonObject.getString(CONST_URL);
            JSONArray mediaArray = jsonObject.getJSONArray(CONST_MULTIMEDIA);
            if (mediaArray != null && mediaArray.length() > 0) {
                JSONObject mediaObject = mediaArray.getJSONObject(0);
                MediaEntity mediaEntity = new MediaEntity(mediaObject);
                mediaEntityList.add(mediaEntity);
            }
        } catch (JSONException exception) {
            Log.e(TAG, "Fail to parser response = "+exception);
        }
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public List<MediaEntity> getMediaEntity() {
        return mediaEntityList;
    }
}
