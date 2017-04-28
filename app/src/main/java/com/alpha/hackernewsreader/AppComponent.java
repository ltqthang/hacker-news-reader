package com.alpha.hackernewsreader;

import com.alpha.hackernewsreader.list.NewsListFragment;
import dagger.Component;

/**
 * @author Thang
 * @since 25/4/17 20:54
 */
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(NewsListFragment newsListFragment);
}
