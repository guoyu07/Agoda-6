package news.agoda.com.sample.module.newslist.model;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import news.agoda.com.sample.module.Utility;

public class ServiceRequest {
    private static final String TAG = ServiceRequest.class.getSimpleName();
    private static final String API_URL = "https://api.myjson.com/bins/nl6jh";
    private List<NewsEntity> mediaList;

    public void loadResource(final ResponseCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mediaList = makeWebRequest(API_URL);
                callback.onResult(mediaList);
            }
        }).start();
    }

    List<NewsEntity> makeWebRequest(String apiUrl) {
        List<NewsEntity> newsList = null;
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            String readStream = Utility.readStream(con.getInputStream());
            newsList = new MediaParser().parseMediaContent(readStream);
        } catch (Exception e) {
            Log.e(TAG, "Fail to process web request = " + e);
        }
        return newsList;
    }
}
