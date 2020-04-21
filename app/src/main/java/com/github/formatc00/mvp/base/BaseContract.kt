package com.github.formatc00.mvp.base

interface BaseContract {

    interface View {

        fun showProgress()

        fun hideProgress()

        fun onError(throwable: Throwable)
    }

    interface Presenter<V : View> {

        fun attachView(view: V)

        fun detachView()

        fun isViewAttached(): Boolean

        fun getView(): V

        fun onError(throwable: Throwable)
    }
}
