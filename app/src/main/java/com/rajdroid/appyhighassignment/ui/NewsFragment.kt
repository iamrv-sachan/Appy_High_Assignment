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
import com.rajdroid.appyhighassignment.Resource
import com.rajdroid.appyhighassignment.databinding.FragmentNewsBinding
import com.rajdroid.appyhighassignment.entites.Article
import dagger.hilt.android.AndroidEntryPoint


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
        list = ArrayList()
        setupObservers()

    }

    private fun setupObservers() {
        Log.i("luck","dad")
        viewmodel.news.observe(viewLifecycleOwner, {
            Log.i("luck",it.status.toString())
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty())
                    {
                        list.addAll(ArrayList(it.data))
                        adapter = NewsAdapter(ArrayList(it.data),this@NewsFragment)
                        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
                        binding.recycler.adapter = adapter
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