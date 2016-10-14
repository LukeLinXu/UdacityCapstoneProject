package com.example.lukelin.udacitycapstoneproject.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lukelin.udacitycapstoneproject.R;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/**
 * Created by lukelin on 2016-10-14.
 */

public abstract class ClickToRefreshFragmentBase<T> extends Fragment {

    private RelativeLayout mainContent;
    private TextView refreshButton;
    private ImageView progressBar;
    private Subscription subscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.click_to_refresh_fragment_base, container, false);
        View spView = inflater.inflate(getLayoutId(), container, false);
        mainContent = (RelativeLayout) view.findViewById(R.id.click_to_refresh_fragment_maincontent);
        mainContent.addView(spView);
        refreshButton = (TextView) view.findViewById(R.id.click_to_refresh_fragment_refresh_button);
        progressBar = (ImageView) view.findViewById(R.id.click_to_refresh_fragment_progress);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        refresh();
        return view;
    }

    private void refresh(){
        this.subscription = doRefresh()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        onProgress();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onFail();
                    }

                    @Override
                    public void onNext(T object) {
                        onSuccess(object);
                    }
                });
    }

    protected abstract Observable<T> doRefresh();

    private void onFail() {
        mainContent.setVisibility(View.INVISIBLE);
        refreshButton.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void onSuccess(T object) {
        mainContent.setVisibility(View.VISIBLE);
        refreshButton.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        refreshUI(mainContent, object);
    }

    protected abstract void refreshUI(RelativeLayout mainContent, T object);

    private void onProgress() {
        mainContent.setVisibility(View.INVISIBLE);
        refreshButton.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        this.subscription.unsubscribe();
        super.onDestroy();
    }

    protected abstract int getLayoutId();

}