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
import java.text.NumberFormat

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
                mProperty = it.getParcelable(INTENT_EXTRA_PROPERTY) as Property?
                DLog.d(LOG_TAG, "mProperty != null")
                DLog.d(LOG_TAG, "mProperty?.title: " + mProperty?.title)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        DLog.i(LOG_TAG, "onCreateView")
        val view = inflater.inflate(R.layout.property_detail, container, false)
        view.property_detail_empty_message.visibility = if (mProperty == null) View.VISIBLE else View.GONE
        view.property_detail_title.text = mProperty?.title
        view.property_detail_address.text = getString(R.string.property_address_combined,
                        mProperty?.location?.address_1,
                        mProperty?.location?.suburb,
                        mProperty?.location?.postcode)
        view.property_detail_description.text = mProperty?.description
        try {
            view.property_detail_price.text = NumberFormat.getCurrencyInstance().format(mProperty?.price)
        } catch (e: Exception) {
            view.property_detail_price.text = ""
        }
        return view
    }
}
