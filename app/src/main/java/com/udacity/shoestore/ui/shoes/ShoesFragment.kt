package com.udacity.shoestore.ui.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.MyBaseFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesBinding
import com.udacity.shoestore.databinding.SimpleShoeItemBinding
import com.udacity.shoestore.models.Shoe


/**
 * A simple [Fragment] subclass.
 * Use the [ShoesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoesFragment : MyBaseFragment() {
    private val viewModel: ShoesViewModel by activityViewModels()
    private lateinit var binding : FragmentShoesBinding
    private lateinit var linearLayout : LinearLayout
    private lateinit var inflater: LayoutInflater
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initMyFragments("List of Shoes", false)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentShoesBinding>(inflater, R.layout.fragment_shoes, container, false)
        this.inflater = inflater

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        val adapter = ShoeAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.shoes.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it)
            }
        )
        binding.lifecycleOwner = this

        setClickFunctions()
        return binding.root
    }

    private fun setClickFunctions(){
        binding.btnAddShoe.setOnClickListener {
            findNavController().navigate(R.id.action_shoesFragment_to_shoeDetailFragment)
        }
    }

    class ShoeAdapter : ListAdapter<Shoe, ShoeAdapter.ShoeHolder>(ShoeDiffCallBack()){
        override fun onBindViewHolder(holder: ShoeHolder, position: Int) {
            val item = getItem(position)
            holder.bind(item)
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeHolder {
            return ShoeHolder.from(parent)
        }

        class ShoeHolder private constructor(val binding : SimpleShoeItemBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(shoe:Shoe){
                binding.apply{
                    shoeName.text = shoe.name
                    companyName.text = shoe.company
                    sizeValue.text = shoe.size.toString()
                    executePendingBindings()
                }
            }

            companion object{
                fun from(parent: ViewGroup):ShoeHolder{
                    val inflater = LayoutInflater.from(parent.context)
                    val binding = DataBindingUtil.inflate<SimpleShoeItemBinding>(inflater, R.layout.simple_shoe_item,parent, false)
                    return ShoeHolder(binding)
                }
            }
        }
    }

    class ShoeDiffCallBack : DiffUtil.ItemCallback<Shoe>(){
        override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
            return (oldItem.name == newItem.name )
                    && (oldItem.company == newItem.company )
                    && (oldItem.size == newItem.size )
        }

        override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
            return oldItem == newItem
        }
    }
}