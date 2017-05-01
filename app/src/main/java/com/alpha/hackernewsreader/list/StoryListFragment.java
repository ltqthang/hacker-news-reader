package com.alpha.hackernewsreader.list;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.alpha.hackernewsreader.App;
import com.alpha.hackernewsreader.Navigator;
import com.alpha.hackernewsreader.R;
import com.alpha.hackernewsreader.databinding.FragmentStoryListBinding;
import com.alpha.hackernewsreader.databinding.LayoutStoryItemBinding;
import com.alpha.hackernewsreader.model.Story;
import java.util.List;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryListFragment extends Fragment implements StoryListView {

    @Inject
    StoryListPresenter mPresenter;

    Navigator mNavigator;

    private FragmentStoryListBinding mBinding;

    public StoryListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getInstance().getsAppComponent().inject(this);

        mNavigator = new Navigator(getContext());

        mPresenter.setStoryListView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_story_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding = DataBindingUtil.bind(view);
        mBinding.setPresenter(mPresenter);

        mPresenter.fetchTopStories(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mPresenter.destroy();
    }

    @Override
    public void onLoading() {
        mBinding.loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError() {
        mBinding.swipeToRefresh.setRefreshing(false);
        mBinding.loadingView.setVisibility(View.GONE);
        Toast.makeText(getContext(), App.getInstance().getString(R.string.fetch_story_list_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStoryListFetched(List<Story> stories) {
        mBinding.swipeToRefresh.setRefreshing(false);
        mBinding.loadingView.setVisibility(View.GONE);

        for (Story story : stories) {
            LayoutStoryItemBinding binding = LayoutStoryItemBinding.inflate(LayoutInflater.from(getContext()));
            binding.setStory(story);
            binding.setNavigator(mNavigator);
            mBinding.content.addView(binding.getRoot());
        }
    }
}
