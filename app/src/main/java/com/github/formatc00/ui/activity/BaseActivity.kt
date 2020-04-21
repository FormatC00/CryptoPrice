package com.github.formatc00.ui.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.github.formatc00.mvp.base.BaseContract
import com.github.formatc00.mvp.base.MessageInterface
import com.github.formatc00.ui.navigation.BaseNavigator
import dagger.android.support.DaggerAppCompatActivity
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> :
    DaggerAppCompatActivity(), BaseContract.View {
    
    @Inject
    lateinit var presenter: P
    
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    
    @Inject
    lateinit var navigator: BaseNavigator
    
    @Inject
    lateinit var messageInterface: MessageInterface
    
    @get:LayoutRes
    protected abstract val layoutId: Int
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }
    
    override fun onStart() {
        super.onStart()
        presenter.attachView(this as V)
    }
    
    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }
    
    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
    
    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }
    
    override fun onError(throwable: Throwable) {
        messageInterface.showError(throwable)
    }
    
    override fun showProgress() {
        //todo show progress dialog
    }
    
    override fun hideProgress() {
        //todo hide progress dialog
    }
}