package com.example.pets.presentation.ui.bases;

import android.databinding.ObservableInt;

import com.example.pets.Logger;
import com.example.pets.core.NetworkFacade;
import com.example.pets.presentation.ui.utils.ProjectDataBindingUtil;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class BaseViewModel {

    public final ObservableInt loadings = new ObservableInt();

    @Inject protected NetworkFacade networkFacade;

    private CompositeDisposable disposables = new CompositeDisposable();

    protected <T> void execute(
            Single<T> single,
            BaseSingleObserver<T> observer
    ) {
        execute(single, observer, preloader);
    }

    @SuppressWarnings("WeakerAccess")
    protected <T> void execute(
            Single<T> single,
            BaseSingleObserver<T> observer,
            final ProgressBehavior progressBehavior
    ) {
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((disposable) -> progressBehavior.start())
                .doOnEvent((item, error) -> progressBehavior.terminate())
                .doOnSubscribe(disposables::add)
                .subscribe(observer);
    }

    public void onDestroy() {
        Logger.e(this, "onDestroy()");
        disposables.dispose();
    }


    protected class BaseSingleObserver<T> extends DisposableSingleObserver<T> {

        @Override
        public void onSuccess(T t) {
        }

        @Override
        public void onError(Throwable e) {
            throw new RuntimeException(e);
        }
    }


    protected interface ProgressBehavior {
        void start();
        void terminate();
    }


    @SuppressWarnings("WeakerAccess")
    protected final ProgressBehavior preloader = new ProgressBehavior() {

        @Override
        public void start() {
            ProjectDataBindingUtil.inc(loadings);
        }

        @Override
        public void terminate() {
            ProjectDataBindingUtil.dec(loadings);
        }
    };
}
