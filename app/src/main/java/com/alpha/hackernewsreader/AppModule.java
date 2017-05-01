package com.alpha.hackernewsreader;

import com.alpha.hackernewsreader.api.CommentDetailsAPI;
import com.alpha.hackernewsreader.api.StoryDetailsAPI;
import com.alpha.hackernewsreader.api.TopStoriesAPI;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Thang
 * @since 25/4/17 20:54
 */
@Module
public class AppModule {
    private App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    App provideApp() {
        return mApp;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://hacker-news.firebaseio.com/v0/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    TopStoriesAPI provideTopStoriesAPI(Retrofit retrofit) {
        return retrofit.create(TopStoriesAPI.class);
    }

    @Provides
    @Singleton
    StoryDetailsAPI provideStoryDetailsAPI(Retrofit retrofit) {
        return retrofit.create(StoryDetailsAPI.class);
    }

    @Provides
    @Singleton
    CommentDetailsAPI provideCommentDetailsAPI(Retrofit retrofit) {
        return retrofit.create(CommentDetailsAPI.class);
    }
}
