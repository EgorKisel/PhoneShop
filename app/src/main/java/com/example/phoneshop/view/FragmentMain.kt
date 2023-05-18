package com.example.phoneshop.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.phoneshop.KEY_BUNDLE
import com.example.phoneshop.R
import com.example.phoneshop.databinding.FragmentMainBinding
import com.example.phoneshop.view.adapter.BestSellerAdapter
import com.example.phoneshop.view.adapter.HomeStoreAdapter
import com.example.phoneshop.viewmodel.AppState
import com.example.phoneshop.viewmodel.StoreViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class FragmentMain : Fragment(), OnItemClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!
    private val viewModel: StoreViewModel by lazy {
        ViewModelProvider(this)[StoreViewModel::class.java]
    }
    private val adapterHome = BestSellerAdapter()
    private val adapterBest = HomeStoreAdapter()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<AppState> { renderData(it) }
        binding.recyclerHomeStorePhones.adapter = adapterHome
        adapterHome.mSetOnClickListener(this)
        binding.recyclerBestSeller.adapter = adapterBest
        viewModel.apply {
            getLiveData().observe(viewLifecycleOwner, observer)
            getHomeStore()
            getBestSeller()
        }
        setHasOptionsMenu(true)

        setBottomSheetBehavior()

        binding.behaviorInclude.btnClose.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    private fun setBottomSheetBehavior() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.behaviorInclude.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.filter -> {
                Log.d("@@@", "Filter")
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun renderData(state: AppState) {
        when (state) {
            is AppState.Error -> {}
            AppState.Loading -> {}
            is AppState.Success -> {
                adapterHome.setData(state.responseDTO.bestSeller)
                adapterBest.setData(state.responseDTO.homeStore)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMain()
    }

    override fun onItemClick(id: Int) {
        val bundle = Bundle()
        bundle.putInt(KEY_BUNDLE, id)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container, FragmentProductDetails.newInstance(bundle)).addToBackStack("")
            .commit()
    }
}