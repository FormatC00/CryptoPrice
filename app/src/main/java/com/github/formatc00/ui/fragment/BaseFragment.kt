package com.github.formatc00.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.fragment.app.ListFragment
import com.github.formatc00.mvp.base.BaseContract
import com.github.formatc00.ui.activity.BaseActivity
import com.github.formatc00.util.Logger
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> :
    DaggerFragment(), BaseContract.View {
    
    private val logger = Logger.create(this)
    
    @Inject
    lateinit var presenter: P
    
    @get:LayoutRes
    protected abstract val layoutId: Int
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutId, container, false)
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        presenter.attachView(this as V)
    }
    
    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }
    
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuRes = getMenuRes()
        if (menuRes != 0) {
            inflater.inflate(menuRes, menu)
        }
    }
    
    protected fun getBaseActivity() = activity as BaseActivity<*, *>?
    
    override fun onError(throwable: Throwable) {
        val baseActivity = getBaseActivity()
        if (baseActivity != null && isVisible) {
            getBaseActivity()?.onError(throwable)
        }
    }
    
    override fun showProgress() {
        val baseActivity = getBaseActivity()
        if (baseActivity != null && isVisible) {
            getBaseActivity()?.showProgress()
        }
    }
    
    override fun hideProgress() {
        val baseActivity = getBaseActivity()
        if (baseActivity != null && isVisible) {
            getBaseActivity()?.hideProgress()
        }
    }
    
    @MenuRes
    protected fun getMenuRes() = 0
}