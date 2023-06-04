package com.example.machinetest.view


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.machinetest.R
import com.example.machinetest.databinding.AdapterImageListItemBinding
import com.example.machinetest.model.ImageListModel
import java.util.*

class ImageListAdapter(
    private val context: Context?,
    private var arrayListImages:List<ImageListModel>,
    private var imageClickInterface: ImageClickInterface,
) :
    RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>(), Filterable {
    var strSearchQuery = ""
    var defaultList: ArrayList<ImageListModel> = arrayListImages as ArrayList<ImageListModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = AdapterImageListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ImageViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        var stringTitle: String? = null
        if (arrayListImages.isNotEmpty()) {
            stringTitle = arrayListImages[position].name.lowercase()
            holder.mBinding?.title?.text = stringTitle
            holder.mBinding?.imageView?.let {
                Glide.with(context!!).load(arrayListImages[position].image).centerCrop().into(it)
            }

        }


        var spannableStringBuilder = SpannableStringBuilder(stringTitle)
        if (strSearchQuery.isNotEmpty()) {
            var index = stringTitle?.lowercase()?.indexOf(strSearchQuery)!!
            while (index > -1) {
                spannableStringBuilder = SpannableStringBuilder(stringTitle)
                val foregroundColorSpan =
                    ForegroundColorSpan(context?.resources?.getColor(R.color.highlight_red)!!) //specify color here
                val styleSpan = StyleSpan(Typeface.BOLD)
                spannableStringBuilder.setSpan(
                    styleSpan,
                    index,
                    (index + strSearchQuery.length),
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
                spannableStringBuilder.setSpan(
                    foregroundColorSpan,
                    index,
                    (index + strSearchQuery.length),
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
                index = stringTitle.indexOf(strSearchQuery, index + 1)

            }
            //  setFadeAnimation(holder.mLayout)

            holder.mBinding?.title?.text = spannableStringBuilder

        } else {
            holder.mBinding?.title?.text = Html.fromHtml(stringTitle)
        }

        holder.mBinding?.container?.setOnClickListener {
            imageClickInterface.onImageClick(arrayListImages[position].image)
        }
    }

    override fun getItemCount(): Int {
        return arrayListImages.size
    }


    inner class ImageViewHolder(val mBinding: AdapterImageListItemBinding?) :
        RecyclerView.ViewHolder(mBinding?.root!!) {
    }

    /**
     * filter used for searching images by name.
     */
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString: String = constraint as String
                strSearchQuery = charString.lowercase()
                arrayListImages = if (constraint.isEmpty()) {
                    defaultList
                } else {
                    val filteredList = ArrayList<ImageListModel>()
                    for (row in defaultList) {
                        val mTitle = row.name.lowercase()
                        if (mTitle.contains(strSearchQuery)) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResult = FilterResults()
                filterResult.values = arrayListImages
                return filterResult

            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                arrayListImages = results?.values as ArrayList<ImageListModel>
                notifyDataSetChanged()
            }
        }
    }
}


