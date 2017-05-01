package com.alpha.hackernewsreader;

import com.alpha.hackernewsreader.details.CommentListFragment;
import com.alpha.hackernewsreader.list.StoryListFragment;
import dagger.Component;
import javax.inject.Singleton;

/**
 * @author Thang
 * @since 25/4/17 20:54
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(StoryListFragment newsListFragment);

    void inject(CommentListFragment commentListFragment);
}
