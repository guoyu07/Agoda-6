package news.agoda.com.sample.module.newsdetail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import news.agoda.com.sample.R;
import news.agoda.com.sample.module.AppConstant;
import news.agoda.com.sample.module.Utility;
import news.agoda.com.sample.module.newslist.model.NewsEntity;

/**
 * News detail view
 */
public class DetailViewActivity extends Activity {
    private String storyURL = AppConstant.EMPTY_STRING;

    /**
     * Using starter pattern to avoid confusion when same activity is called from several places.
     *
     * @param activity calling activity
     * @param storyUrl Story url
     * @param title    Media title
     * @param summary  Media summary
     * @param imageUrl Media image url
     */
    public static void start(Activity activity, String storyUrl, String title, String summary, String imageUrl) {
        Intent startIntent = new Intent(activity, DetailViewActivity.class);
        startIntent.putExtra(NewsEntity.CONST_URL, storyUrl);
        startIntent.putExtra(NewsEntity.CONST_TITLE, title);
        startIntent.putExtra(NewsEntity.CONST_SUMMARY, summary);
        startIntent.putExtra(NewsEntity.CONST_IMAGE_URL, imageUrl);
        activity.startActivity(startIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        storyURL = extras.getString(NewsEntity.CONST_URL);
        String title = extras.getString(NewsEntity.CONST_TITLE);
        String summary = extras.getString(NewsEntity.CONST_SUMMARY);
        String imageURL = extras.getString(NewsEntity.CONST_IMAGE_URL);

        TextView titleView = (TextView) findViewById(R.id.title);
        DraweeView imageView = (DraweeView) findViewById(R.id.news_image);
        TextView summaryView = (TextView) findViewById(R.id.summary_content);

        titleView.setText(title);
        summaryView.setText(summary);

        if (imageURL != null) {
            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(ImageRequest.fromUri(Uri.parse(imageURL)))
                    .setOldController(imageView.getController()).build();
            imageView.setController(draweeController);
        }
    }

    /**
     * This method is used to launch news URL in browser.
     *
     * @param view
     */
    public void onFullStoryClicked(View view) {
        if (Utility.isNetworkAvailable(DetailViewActivity.this)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(storyURL));
            startActivity(intent);
        } else {
            Utility.showDialog(DetailViewActivity.this, AppConstant.INTERNET_ERROR_MSG);
        }
    }
}
