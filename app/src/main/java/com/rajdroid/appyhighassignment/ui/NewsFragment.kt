package com.rajdroid.appyhighassignment.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.*
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.rajdroid.appyhighassignment.Resource
import com.rajdroid.appyhighassignment.databinding.FragmentNewsBinding
import com.rajdroid.appyhighassignment.entites.Article
import com.rajdroid.appyhighassignment.room.NewsDao
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class NewsFragment : Fragment(),NewsAdapter.onitemClick  {

    private lateinit var binding: FragmentNewsBinding
    private val viewmodel: NewsViewModel by viewModels()
    private lateinit var adapter: NewsAdapter
    lateinit var list:ArrayList<Article>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bannerAdView.loadAd(AdRequest.Builder().build())



        list = ArrayList()
        var f:Int =0
        setupObservers1()
        binding.btnChange.setOnClickListener{
            Log.i("aaaaaaa",f.toString())
            if(f==0)
            {
                f=1
                binding.btnChange.text="IN"
                setupObservers()
            }
             else
            {
                f=0
                binding.btnChange.text="US"
                setupObservers1()
            }
        }



    }

    private fun setupObservers() {
        Log.i("luck","dad")
        viewmodel.news.observe(viewLifecycleOwner, {
            Log.i("luck",it.status.toString())
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty())
                    {
                        list.clear()
                        list.addAll(ArrayList(it.data))
                        adapter = NewsAdapter(ArrayList(it.data),this@NewsFragment)
                        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
                        binding.recycler.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

            }
        })
    }
    private fun setupObservers1() {
        viewmodel.news1.observe(viewLifecycleOwner, {
            Log.i("luck",it.status.toString())
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty())
                    {
                        list.clear()
                        list.addAll(ArrayList(it.data))
                        adapter = NewsAdapter(ArrayList(it.data),this@NewsFragment)
                        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
                        binding.recycler.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

            }
        })
    }

    override fun onItemClicked(d: Int) {
        val url = list.get(d).url
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

}