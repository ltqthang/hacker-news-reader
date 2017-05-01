package com.alpha.hackernewsreader.details;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.alpha.hackernewsreader.App;
import com.alpha.hackernewsreader.R;
import com.alpha.hackernewsreader.databinding.FragmentCommentListBinding;
import com.alpha.hackernewsreader.databinding.LayoutCommentItemBinding;
import com.alpha.hackernewsreader.model.Comment;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentListFragment extends Fragment implements CommentListView {
    private static final String EXTRA_COMMENT_IDS = "commentIds";

    @Inject
    CommentListPresenter mPresenter;

    private FragmentCommentListBinding mBinding;

    public static CommentListFragment newInstance(ArrayList<Integer> commentIds) {

        Bundle args = new Bundle();
        args.putIntegerArrayList(EXTRA_COMMENT_IDS, commentIds);

        CommentListFragment fragment = new CommentListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CommentListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getInstance().getsAppComponent().inject(this);

        mPresenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding = DataBindingUtil.bind(view);

        ArrayList<Integer> commentIds = getArguments().getIntegerArrayList(EXTRA_COMMENT_IDS);
        mPresenter.fetchCommentList(commentIds);
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
        mBinding.loadingView.setVisibility(View.GONE);
        Toast.makeText(getContext(), App.getInstance().getString(R.string.fetch_comment_list_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCommentListFetched(List<Comment> comments) {
        mBinding.loadingView.setVisibility(View.GONE);

        for (Comment comment : comments) {
            LayoutCommentItemBinding itemBinding = LayoutCommentItemBinding.inflate(LayoutInflater.from(getContext()));
            itemBinding.setComment(comment);
            mBinding.content.addView(itemBinding.getRoot());
        }
    }
}
