package com.hyosik.android.samplerecyclerviewgrid

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hyosik.android.samplerecyclerviewgrid.SpannedGridLayoutManager.GridSpanLookup
import com.hyosik.android.samplerecyclerviewgrid.SpannedGridLayoutManager.SpanInfo
import com.hyosik.android.samplerecyclerviewgrid.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    private val adapter : ThumbAdapter = ThumbAdapter()

    private val thumbList : MutableList<Thumb> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)

        val height = metrics.heightPixels
        val width = metrics.widthPixels / 3
        adapter.setWidthHeight(deviceWidth = width , deviceHeight = height)
        binding.rvThumb.adapter = adapter
        binding.rvThumb.setLayoutManager(
            SpannedGridLayoutManager(
                object : GridSpanLookup {
                    override fun getSpanInfo(position: Int): SpanInfo {
                        return if (position == 0) {
                            SpanInfo(2, 2)
                        } else {
                            SpanInfo(1, 1)
                        }
                    }
                },
                3 /* Three columns */,
                1f /* We want our items to be 1:1 ratio */
            )
        )

        (1..41).map {
            when(it % 5) {
                0, 2 -> {
                    thumbList.add(Thumb(thumbUrl = "https://i.picsum.photos/id/514/400/400.jpg?hmac=C-v02srTnHuEf3HpxN04V0yZKOgdJbA7TNes_KlhhWo"))
                }
                else -> thumbList.add(Thumb())
            }
        }

        adapter.submitList(thumbList)

    }
}