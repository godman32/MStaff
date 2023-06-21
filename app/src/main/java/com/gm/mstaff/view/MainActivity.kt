package com.gm.mstaff.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gm.mstaff.conn.AppRepository
import com.gm.mstaff.databinding.ActivityMainBinding
import com.gm.mstaff.utills.Resource
import com.gm.mstaff.utills.ViewModelFactory
import com.gm.mstaff.utills.constant.Status
import com.gm.mstaff.utills.ext.setHidden
import com.gm.mstaff.utills.ext.setVisible
import com.gm.mstaff.utills.listener.OnScrollFullListener

class MainActivity : AppCompatActivity(), OnScrollFullListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this, ViewModelFactory(application, AppRepository())).get(
        MainVM::class.java)}
    private val dataAdapter by lazy { DataAdapter( this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lShimmer.duration= 800
        binding.lShimmer.startShimmerAnimation()

        viewModel.getData()

        observe()
    }


    override fun onScrollFull() {
        super.onScrollFull()
        if(!binding.load.isVisible){
            viewModel.page++
            viewModel.getData()
        }
    }

    private fun observe(){
        viewModel.staffs.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        if(binding.lShimmer.isVisible){
                            binding.lShimmer.stopShimmerAnimation()
                            binding.lShimmer.setHidden()
                        }

                        binding.load.setHidden()
                        response.data?.let { res ->
                            if(res.staffs.size > 0){
                                if(binding.rvData.adapter == null){
                                    binding.rvData.apply {
                                        adapter= dataAdapter
                                    }
                                }
                                dataAdapter.add(res.staffs)
                                binding.rvData.post(Runnable { dataAdapter.notifyItemInserted(dataAdapter.itemCount)})
                            } else{
                                dataAdapter.status= Status.CLEAR
                            }
                        }
                    }

                    is Resource.Error -> {
                        if(binding.lShimmer.isVisible){
                            binding.lShimmer.stopShimmerAnimation()
                            binding.lShimmer.setHidden()
                        }

                        dataAdapter.status= Status.CLEAR
                        binding.load.setHidden()
                        response.message?.let {}
                    }

                    is Resource.Loading -> { binding.load.setVisible()}
                }
            }
        })
    }
}