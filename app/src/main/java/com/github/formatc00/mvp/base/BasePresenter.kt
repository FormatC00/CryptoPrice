package com.github.formatc00.mvp.base

import com.github.formatc00.core.exception.ViewNotAttachedException
import com.github.formatc00.util.Logger
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import ru.terrakok.cicerone.Router

open class BasePresenter<V : BaseContract.View>(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler,
    val router: Router
) : BaseContract.Presenter<V> {
    
    private val logger = Logger.create(this)
    
    private var view: V? = null
    
    private var compositeDisposable: CompositeDisposable? = null
    
    private var firstAttach = true
    
    override fun isViewAttached() = view != null
    
    override fun onError(throwable: Throwable) {
        if (isViewAttached()) {
            getView().onError(throwable)
        }
    }
    
    override fun attachView(view: V) {
        if (compositeDisposable == null || compositeDisposable!!.isDisposed) {
            this.compositeDisposable = CompositeDisposable()
        }
        this.view = view
        
        if (firstAttach) {
            firstAttach = false
            onFirstAttach()
        }
    }
    
    protected open fun onFirstAttach() {}
    
    override fun detachView() {
        this.compositeDisposable?.dispose()
        this.view = null
    }
    
    override fun getView(): V {
        if (view == null) {
            throw ViewNotAttachedException()
        }
        return view as V
    }
    
    protected fun <T> subscribe(upstream: Observable<T>, onNext: Consumer<T>): Disposable {
        val disposable = upstream.subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .subscribe(onNext, Consumer { this.onError(it) })
        
        addDisposable(disposable)
        return disposable
    }
    
    protected fun <T> subscribe(
        upstream: Maybe<T>,
        onNext: Consumer<T>
    ) {
        val disposable = upstream.subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .subscribe(onNext, Consumer { this.onError(it) })
        
        addDisposable(disposable)
    }
    
    protected fun <T> subscribe(upstream: Single<T>, onSuccess: Consumer<T>) {
        val disposable = upstream.subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .subscribe(onSuccess, Consumer { this.onError(it) })
        
        addDisposable(disposable)
    }
    
    protected fun subscribe(upstream: Completable, onComplete: Action) {
        val disposable = upstream.subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .subscribe(onComplete, Consumer { this.onError(it) })
        
        addDisposable(disposable)
    }
    
    protected fun <T> subscribeWithProgress(
        upstream: Observable<T>,
        onNext: Consumer<T>,
        showProgress: Consumer<Disposable>,
        hideProgress: Action
    ) {
        val disposable = upstream.doOnSubscribe(showProgress)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .doOnTerminate(hideProgress)
            .subscribe(onNext, Consumer { this.onError(it) })
        
        addDisposable(disposable)
    }
    
    protected fun <T> subscribeWithProgress(
        upstream: Single<T>,
        onSuccess: Consumer<T>,
        showProgress: Consumer<Disposable>,
        hideProgress: Action
    ) {
        val disposable = upstream.doOnSubscribe(showProgress)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .doAfterTerminate(hideProgress)
            .subscribe(onSuccess, Consumer { this.onError(it) })
        
        addDisposable(disposable)
    }
    
    protected fun subscribeWithProgress(
        upstream: Completable,
        onComplete: Action,
        showProgress: Consumer<Disposable>,
        hideProgress: Action
    ) {
        val disposable = upstream.doOnSubscribe(showProgress)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .doOnTerminate(hideProgress)
            .subscribe(onComplete, Consumer { this.onError(it) })
        
        addDisposable(disposable)
    }
    
    protected fun subscribeWithProgressInUiThread(
        upstream: Completable,
        onComplete: Action
    ) {
        val disposable = upstream.doOnSubscribe { getView().showProgress() }
            .subscribeOn(foregroundScheduler)
            .observeOn(foregroundScheduler)
            .doFinally { getView().hideProgress() }
            .subscribe(onComplete, Consumer { this.onError(it) })
        
        addDisposable(disposable)
    }
    
    private fun addDisposable(disposable: Disposable) {
        compositeDisposable?.add(disposable)
    }
}