package com.github.formatc00.ui.activity

import android.os.Bundle
import com.bumptech.glide.Glide
import com.github.formatc00.R
import com.github.formatc00.core.entity.Cryptocurrency
import com.github.formatc00.core.entity.CryptocurrencyMetadata
import com.github.formatc00.core.entity.CryptocurrencyQuote
import com.github.formatc00.mvp.contract.CryptoDetailsActivityContract
import com.github.formatc00.ui.navigation.Screens
import com.github.formatc00.ui.widget.UrlView
import com.github.formatc00.util.UiUtils
import com.github.formatc00.util.extension.visible
import kotlinx.android.synthetic.main.activity_crypto_details.aboutDetailsLabel
import kotlinx.android.synthetic.main.activity_crypto_details.aboutLabel
import kotlinx.android.synthetic.main.activity_crypto_details.announcementUrl
import kotlinx.android.synthetic.main.activity_crypto_details.chatUrl
import kotlinx.android.synthetic.main.activity_crypto_details.explorerUrl
import kotlinx.android.synthetic.main.activity_crypto_details.logo
import kotlinx.android.synthetic.main.activity_crypto_details.messageBoardUrl
import kotlinx.android.synthetic.main.activity_crypto_details.priceChangeLabel
import kotlinx.android.synthetic.main.activity_crypto_details.priceLabel
import kotlinx.android.synthetic.main.activity_crypto_details.redditUrl
import kotlinx.android.synthetic.main.activity_crypto_details.sourceCodeUrl
import kotlinx.android.synthetic.main.activity_crypto_details.subtitleLabel
import kotlinx.android.synthetic.main.activity_crypto_details.techDocUrl
import kotlinx.android.synthetic.main.activity_crypto_details.titleLabel
import kotlinx.android.synthetic.main.activity_crypto_details.toolbar
import kotlinx.android.synthetic.main.activity_crypto_details.twitterUrl
import kotlinx.android.synthetic.main.activity_crypto_details.urlsContainer
import kotlinx.android.synthetic.main.activity_crypto_details.websiteUrl
import javax.inject.Inject

class CryptoDetailsActivity :
    BaseActivity<CryptoDetailsActivityContract.View, CryptoDetailsActivityContract.Presenter>(),
    CryptoDetailsActivityContract.View {
    
    override val layoutId = R.layout.activity_crypto_details
    
    @Inject
    lateinit var uiUtils: UiUtils
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cryptocurrency =
            intent.getParcelableExtra(Screens.CryptoDetailsScreen.CRYPTOCURRENCY_EXTRA) as? Cryptocurrency
        
        toolbar?.apply {
            setSupportActionBar(this)
            setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            setNavigationOnClickListener { finish() }
        }
        
        cryptocurrency?.apply {
            titleLabel.text = name
            subtitleLabel.text = symbol
            Glide.with(logo).load(metadata?.logoUrl).into(logo)
            quote?.let(::setupQuotes)
            aboutLabel.text = getString(R.string.about_label_pattern, name)
            setupMetadata(metadata)
        }
    }
    
    private fun setupMetadata(metadata: CryptocurrencyMetadata?) {
        listOf(urlsContainer, aboutDetailsLabel, aboutLabel).forEach {
            it.visible = metadata != null
        }
        
        aboutDetailsLabel.text = metadata?.description
        val urls = metadata?.urls
        urlsContainer.visible = urls != null
        urls?.apply {
            setupUrl(website, websiteUrl)
            setupUrl(technicalDoc, techDocUrl)
            setupUrl(twitter, twitterUrl)
            setupUrl(reddit, redditUrl)
            setupUrl(messageBoard, messageBoardUrl)
            setupUrl(announcement, announcementUrl)
            setupUrl(chat, chatUrl)
            setupUrl(explorer, explorerUrl)
            setupUrl(sourceCode, sourceCodeUrl)
        }
    }
    
    private fun setupUrl(urls: List<String>, urlView: UrlView) {
        val url = urls.firstOrNull()
        urlView.visible = url?.isNotEmpty() ?: false
        url?.let { urlView.setUrl(it) }
    }
    
    private fun setupQuotes(quote: CryptocurrencyQuote) {
        uiUtils.setupPriceChange(quote.percentChange1h, priceChangeLabel)
        priceLabel.text = resources.getString(R.string.price_pattern, quote.price)
    }
}