package com.alpha.hackernewsreader.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.alpha.hackernewsreader.R;
import java.util.ArrayList;

public class CommentListActivity extends AppCompatActivity {
    private static final String EXTRA_COMMENT_IDS = "commentIds";

    public static void start(Context context, ArrayList<Integer> commentIds) {
        Intent starter = new Intent(context, CommentListActivity.class);
        starter.putIntegerArrayListExtra(EXTRA_COMMENT_IDS, commentIds);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        if (savedInstanceState == null) {
            CommentListFragment fragment = CommentListFragment.newInstance(getIntent().getIntegerArrayListExtra(EXTRA_COMMENT_IDS));
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.newsDetails, fragment)
                    .commit();
        }
    }
}
