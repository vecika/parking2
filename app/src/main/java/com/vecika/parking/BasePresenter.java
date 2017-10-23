package com.vecika.parking;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author tturcic
 *         \date 8.8.2017.
 */
public abstract class BasePresenter<T> {

    protected T view;
    private CompositeDisposable subscriptions;

    public void setView(T view){
        this.view = view;
    }

    protected void addSubscription(Disposable subscription){
        if(subscriptions == null){
            subscriptions = new CompositeDisposable();
        }
        subscriptions.add(subscription);
    }

    public void unsubscribe(){
        if(subscriptions != null){
            subscriptions.clear();
        }
    }
}
