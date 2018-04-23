package alikazi.com.sentia.main

import alikazi.com.sentia.R
import alikazi.com.sentia.models.Properties
import alikazi.com.sentia.models.Property
import alikazi.com.sentia.network.RequestQueueHelper
import alikazi.com.sentia.network.RequestsProcessor
import alikazi.com.sentia.utils.AnimationUtils
import alikazi.com.sentia.utils.AppConf
import alikazi.com.sentia.utils.DLog
import android.app.FragmentTransaction
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.android.volley.VolleyError
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(),
        RequestsProcessor.RequestResponseListener,
        AnimationUtils.ToolbarAnimationListener,
        RecyclerAdapter.RecyclerItemClickListener {

    companion object {

        private const val LOG_TAG = AppConf.LOG_TAG_MAIN

        private const val SAVE_INSTANCE_KEY_FEED = "SAVE_INSTANCE_KEY_FEED"
    }

    private var mIsTabletMode = false
    private var mRecyclerAdapter: RecyclerAdapter? = null
    private var mRequestsProcessor: RequestsProcessor? = null
    private var mListItems: Properties? = null

    private val isNetworkConnected: Boolean
        get() {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DLog.i(LOG_TAG, "onCreate")
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
        initUi()

        mRequestsProcessor = RequestsProcessor(this, this)
        if (savedInstanceState == null) {
            DLog.i(LOG_TAG,"savedInstanceState == null")
            // Start from scratch
            AnimationUtils.animateToolbar(this, toolbar!!, this)
        } else {
            mListItems = savedInstanceState.getParcelable(SAVE_INSTANCE_KEY_FEED)
            handleOrientationChange()
        }
    }

    private fun initUi() {
        setSupportActionBar(toolbar)
        main_swipe_refresh_layout.setOnRefreshListener { makeRequest() }
        if (property_detail_container != null) {
            mIsTabletMode = true
        }
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        main_recycler_view.layoutManager = layoutManager
        mRecyclerAdapter = RecyclerAdapter(this, this)
        main_recycler_view.adapter = mRecyclerAdapter

        showHideEmptyListMessage(true)
    }

    override fun onPropertyItemClick(property: Property?) {
        DLog.i(LOG_TAG, "onPropertyItemClick")
        if (mIsTabletMode) {
            val fragment = DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DetailsFragment.INTENT_EXTRA_PROPERTY, property)
                }
            }
            supportFragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.property_detail_container, fragment)
                    .commit()
        } else {
            goToDetailActivity(property)
        }
    }

    private fun goToDetailActivity(property: Property?) {
        val intent = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(DetailActivity.INTENT_EXTRA_PROPERTY, property)
        intent.putExtra(DetailActivity.INTENT_EXTRA_BUNDLE, bundle)
        startActivity(intent)
    }

    private fun handleOrientationChange() {
        DLog.i(LOG_TAG, "handleOrientationChange")
        val layoutParams = toolbar.layoutParams
        layoutParams?.height = AnimationUtils.getDefaultActionBarHeightInPixels(this).toInt()
        main_swipe_refresh_layout?.isRefreshing = false
        mRecyclerAdapter?.setListItems(mListItems)
        showHideEmptyListMessage(false)
    }

    override fun onToolbarAnimationEnd() {
        DLog.i(LOG_TAG, "onToolbarAnimationEnd")
        toolbar?.title = getString(R.string.toolbar_title_properties)
        main_empty_list_text_view?.text = getString(R.string.feed_empty_list_message)
        makeRequest()
    }

    private fun makeRequest() {
        if (mRequestsProcessor != null) {
            mRequestsProcessor?.getProperties()
            main_swipe_refresh_layout?.isRefreshing = true
            main_empty_list_text_view?.setText(R.string.feed_empty_list_message)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        DLog.i(LOG_TAG, "onSaveInstanceState")
        outState?.putParcelable(SAVE_INSTANCE_KEY_FEED, mListItems)
    }

    override fun responseOk(properties: Properties) {
        DLog.i(LOG_TAG, "responseOk")
        mListItems = properties
        mRecyclerAdapter?.setListItems(properties)
        main_swipe_refresh_layout?.isRefreshing = false
        showHideEmptyListMessage(false)
    }

    override fun responseError(error: VolleyError) {
        DLog.i(LOG_TAG, "responseError: " + error.toString())
        main_empty_list_text_view?.setText(R.string.feed_empty_list_error_message)
        val snackbarMessage = if (isNetworkConnected)
            getString(R.string.snackbar_feed_load_error)
        else
            getString(R.string.snackbar_network_error_message)
        Snackbar.make(main_recycler_view!!, snackbarMessage, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.refresh, { makeRequest() })
                .show()

        showHideEmptyListMessage(true)
        main_swipe_refresh_layout?.isRefreshing = false
    }

    private fun showHideEmptyListMessage(showMessage: Boolean) {
        main_empty_list_text_view?.visibility = if (showMessage) View.VISIBLE else View.GONE
        main_recycler_view?.visibility = if (showMessage) View.GONE else View.VISIBLE
    }

    override fun onStop() {
        super.onStop()
        RequestQueueHelper.getInstance(this).cancelAllRequests()
    }
}
