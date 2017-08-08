package news.agoda.com.sample.module.newslist.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class represents a media item
 */
public class MediaEntity {
    public String url;

    public MediaEntity(){}

    MediaEntity(JSONObject jsonObject) throws JSONException {
        url = jsonObject.getString("url");
    }

    public String getUrl() {
        return url;
    }

}
