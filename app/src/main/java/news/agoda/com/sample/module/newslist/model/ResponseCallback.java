package news.agoda.com.sample.module.newslist.model;

import java.util.List;

public interface ResponseCallback {
    void onResult(List<NewsEntity> data);
}
