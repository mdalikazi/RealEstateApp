package alikazi.com.sentia.main

import alikazi.com.sentia.R
import alikazi.com.sentia.models.Properties
import alikazi.com.sentia.network.RequestQueueHelper
import alikazi.com.sentia.network.RequestsProcessor
import alikazi.com.sentia.utils.AnimationUtils
import alikazi.com.sentia.utils.AppConf
import alikazi.com.sentia.utils.DLog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.android.volley.VolleyError

class MainActivity : AppCompatActivity(),
        RequestsProcessor.RequestResponseListener,
        AnimationUtils.ToolbarAnimationListener {

    companion object {

        private const val LOG_TAG = AppConf.LOG_TAG_MAIN

        private const val SAVE_INSTANCE_KEY_FEED = "SAVE_INSTANCE_KEY_FEED"
    }

    private var mRecyclerAdapter: RecyclerAdapter? = null
    private var mRequestsProcessor: RequestsProcessor? = null
    private var mListItems: Properties? = null

    private var mEmptyListTextView: TextView? = null
    private var mRecyclerView: RecyclerView? = null
    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    private var mToolbar: Toolbar? = null

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
            AnimationUtils.animateToolbar(this, mToolbar!!, this)
        } else {
            mListItems = savedInstanceState.getParcelable(SAVE_INSTANCE_KEY_FEED)
            DLog.d(LOG_TAG, "mListItems?.data?.size: " + mListItems?.data?.size)
            handleOrientationChange()
        }
    }

    private fun initUi() {
        initToolbar()
        mSwipeRefreshLayout = findViewById(R.id.main_swipe_refresh_layout)
        mSwipeRefreshLayout!!.setOnRefreshListener { makeRequest() }
        mEmptyListTextView = findViewById(R.id.main_empty_list_text_view)

        mRecyclerAdapter = RecyclerAdapter(this)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecyclerView = findViewById(R.id.main_recycler_view)
        mRecyclerView!!.layoutManager = layoutManager
        mRecyclerView!!.adapter = mRecyclerAdapter
        showHideEmptyListMessage(true)
    }

    private fun handleOrientationChange() {
        DLog.i(LOG_TAG, "handleOrientationChange")
        val layoutParams = mToolbar!!.layoutParams
        layoutParams.height = AnimationUtils.getDefaultActionBarHeightInPixels(this).toInt()
        mSwipeRefreshLayout!!.isRefreshing = false
        mRecyclerAdapter!!.setListItems(mListItems!!)
        showHideEmptyListMessage(false)
    }

    override fun onToolbarAnimationEnd() {
        DLog.i(LOG_TAG, "onToolbarAnimationEnd")
        makeRequest()
    }

    private fun makeRequest() {
        if (mRequestsProcessor != null) {
            mRequestsProcessor!!.getProperties()
            mSwipeRefreshLayout!!.isRefreshing = true
            mEmptyListTextView!!.setText(R.string.feed_empty_list_message)
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
        mRecyclerAdapter!!.setListItems(properties)
        mSwipeRefreshLayout!!.isRefreshing = false
        showHideEmptyListMessage(false)
    }

    override fun responseError(error: VolleyError) {
        DLog.i(LOG_TAG, "responseError: " + error.toString())
        mEmptyListTextView!!.setText(R.string.feed_empty_list_error_message)
        val snackbarMessage = if (isNetworkConnected)
            getString(R.string.snackbar_feed_load_error)
        else
            getString(R.string.snackbar_network_error_message)
        Snackbar.make(mRecyclerView!!, snackbarMessage, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.refresh, { makeRequest() })
                .show()

        showHideEmptyListMessage(true)
        mSwipeRefreshLayout!!.isRefreshing = false
    }

    private fun showHideEmptyListMessage(showMessage: Boolean) {
        mEmptyListTextView!!.visibility = if (showMessage) View.VISIBLE else View.GONE
        mRecyclerView!!.visibility = if (showMessage) View.GONE else View.VISIBLE
    }

    override fun onStop() {
        super.onStop()
        RequestQueueHelper.getInstance(this).cancelAllRequests()
    }

    private fun initToolbar() {
        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)
    }
}
