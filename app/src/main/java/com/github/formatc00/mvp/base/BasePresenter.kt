package com.github.formatc00.mvp.base

import com.github.formatc00.core.exception.ViewNotAttachedException
import com.github.formatc00.util.Logger
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
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
    
    protected fun <T> subscribe(upstream: Single<T>, onSuccess: (T) -> Unit) {
        val disposable = upstream.subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
            .subscribe({ onSuccess(it) }, { this.onError(it) })
        
        addDisposable(disposable)
    }
    
    private fun addDisposable(disposable: Disposable) {
        compositeDisposable?.add(disposable)
    }
}