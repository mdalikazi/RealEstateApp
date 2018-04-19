package alikazi.com.sentia.main

import alikazi.com.sentia.R
import alikazi.com.sentia.models.Property
import alikazi.com.sentia.utils.AppConf
import alikazi.com.sentia.utils.DLog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.property_detail.view.*

class DetailsFragment : Fragment() {

    companion object {
        const val LOG_TAG = AppConf.LOG_TAG_MAIN
        const val INTENT_EXTRA_PROPERTY = "INTENT_EXTRA_PROPERTY"
    }

    private var mProperty: Property? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DLog.i(LOG_TAG, "onCreate")
        arguments?.let {
            if (it.containsKey(INTENT_EXTRA_PROPERTY)) {
                mProperty = it.getParcelable(INTENT_EXTRA_PROPERTY)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        DLog.i(LOG_TAG, "onCreateView")
        val view = inflater.inflate(R.layout.property_detail, container, false)
        view.property_detail_text_view.text = mProperty?.title.toString()
        return view
    }
}
