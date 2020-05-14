package com.pms.drugzx.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pms.drugzx.R
import com.pms.drugzx.datamodels.Product
import com.pms.drugzx.datamodels.api.Products
import com.pms.drugzx.ui.main.ProductListingVM
import kotlinx.android.synthetic.main.layout_products_list_item.view.*

class ProductsRecyclerAdapter(viewModel: ProductListingVM) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: ArrayList<Products> =ArrayList()
    var selectedProducts:ArrayList<Products> =ArrayList()
    var productViewModel: ProductListingVM = viewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ProductVH(LayoutInflater.from(parent.context).inflate(R.layout.layout_products_list_item,parent,false))
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is ProductVH->{
                holder.bind(items.get(position))
                holder.productCheckBox?.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (!selectedProducts.contains(items.get(position)))
                 selectedProducts.add(items.get(position))
                    println("selectedProducts"+selectedProducts.toString())
                }
            }
        }
    }
    fun setProductList(products:ArrayList<Products>){
        items=products
        notifyDataSetChanged()
    }

    fun setSelectedProducts(){

        productViewModel.setProducts(selectedProducts)
    }
    override fun getItemCount(): Int {
        return items.size }

    class ProductVH constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        val productName=itemView.product_name
        val productCheckBox=itemView.cb_product

        fun bind(product:Products){
            productName.setText(product.pName)
            productCheckBox.isChecked=false

        }
    }

}