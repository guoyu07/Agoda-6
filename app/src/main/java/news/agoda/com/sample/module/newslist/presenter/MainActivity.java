package news.agoda.com.sample.module.newslist.presenter;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import news.agoda.com.sample.R;
import news.agoda.com.sample.module.AppConstant;
import news.agoda.com.sample.module.Utility;
import news.agoda.com.sample.module.newsdetail.DetailViewActivity;
import news.agoda.com.sample.module.newslist.model.NewsEntity;
import news.agoda.com.sample.module.newslist.model.ResponseCallback;
import news.agoda.com.sample.module.newslist.model.ServiceRequest;
import news.agoda.com.sample.module.newslist.view.NewsListAdapter;


public class MainActivity extends ListActivity implements ResponseCallback {

    private Handler handler = new Handler(Looper.getMainLooper());
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        spinner = (ProgressBar)findViewById(R.id.progress_bar);
        spinner.setVisibility(View.VISIBLE);
        if (Utility.isNetworkAvailable(MainActivity.this)) {
            new ServiceRequest().loadResource(this);
        } else {
            Utility.showDialog(MainActivity.this, AppConstant.INTERNET_ERROR_MSG);
        }
    }

    @Override
    public void onResult(final List<NewsEntity> mediaList) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mediaList == null) {
                    Utility.showDialog(MainActivity.this, AppConstant.ERROR_MSG);
                    return;
                }
                NewsListAdapter adapter = new NewsListAdapter(MainActivity.this, R.layout.list_item_news, mediaList);
                setListAdapter(adapter);
                spinner.setVisibility(View.GONE);
                ListView listView = getListView();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        NewsEntity newsEntity = mediaList.get(position);
                        String imageUrl = null;
                        if (newsEntity.getMediaEntity() != null && !newsEntity.getMediaEntity().isEmpty()) {
                            imageUrl = newsEntity.getMediaEntity().get(0).getUrl();
                        }
                        DetailViewActivity.start(MainActivity.this, newsEntity.getArticleUrl(), newsEntity.getTitle(), newsEntity.getSummary(), imageUrl);
                    }
                });
            }
        }, 0);
    }
}
