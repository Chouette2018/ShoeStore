package com.udacity.shoestore.ui.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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
import com.udacity.shoestore.databinding.SimpleShoeItem2Binding
import com.udacity.shoestore.databinding.SimpleShoeItemBinding
import com.udacity.shoestore.models.Shoe

class ShoesFragment : MyBaseFragment() {
    private val viewModel: ShoesViewModel by activityViewModels()
    private lateinit var binding : FragmentShoesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initMyFragments(getString(R.string.shoes_bar_title), false)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentShoesBinding>(inflater, R.layout.fragment_shoes, container, false)

        binding.apply{
            recyclerView.layoutManager = LinearLayoutManager(this@ShoesFragment.context)

            val adapter = ShoeAdapter()
            recyclerView.adapter = adapter
            viewModel.shoes.observe(viewLifecycleOwner, Observer {
                    adapter.submitList(it)
                }
            )
            lifecycleOwner = this@ShoesFragment

            setClickFunctions()

            return root
        }
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

            holder.itemView.setOnClickListener {
                // Get the current state of the item
                val expanded: Boolean = item.isExpanded
                // Change the state
                item.isExpanded = !expanded
                // Notify the adapter that item has changed
                notifyItemChanged(position)
            }
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeHolder {
            return ShoeHolder.from(parent)
        }

        class ShoeHolder private constructor(val binding : SimpleShoeItem2Binding) : RecyclerView.ViewHolder(binding.root){
            fun bind(shoe:Shoe){
                binding.apply{
                    shoeName.text = shoe.name
                    companyName.text = shoe.company
                    sizeValue.text = shoe.size.toString()
                    if(shoe.isExpanded) {
                        description.visibility = View.VISIBLE
                        descriptionText.visibility = View.VISIBLE
                        descriptionText.text = shoe.description
                    }else{
                        description.visibility = View.GONE
                        descriptionText.visibility = View.GONE
                        descriptionText.text = ""
                    }
                    executePendingBindings()
                }
            }

            companion object{
                fun from(parent: ViewGroup):ShoeHolder{
                    val inflater = LayoutInflater.from(parent.context)
                    val binding = DataBindingUtil.inflate<SimpleShoeItem2Binding>(inflater, R.layout.simple_shoe_item2,parent, false)
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