package com.pms.drugzsm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pms.drugzsm.R
import com.pms.drugzsm.datamodels.api.Products
import com.pms.drugzsm.ui.main.ManageVM
import kotlinx.android.synthetic.main.layout_view_products_item.view.*

class ViewProductsRecyclerAdapter (viewModel: ManageVM) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: ArrayList<Products> =ArrayList()
    var viewModel: ManageVM = viewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ViewProductVH(LayoutInflater.from(parent.context).inflate(R.layout.layout_view_products_item,parent,false))
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is ViewProductVH->{
                holder.bind(items.get(position))
                holder.updateProduct.setOnClickListener{
                    println("updateProduct")
viewModel.updateThisProduct(items.get(position))
                }
                holder.deleteProduct.setOnClickListener{
viewModel.deleteThisProduct(items.get(position).pId)
                  items.remove(items.get(position))
                    notifyDataSetChanged()
                }
            }
        }
    }
    fun setProductList(products:ArrayList<Products>){
        items=products
    }


    override fun getItemCount(): Int {
        return items.size }

    class ViewProductVH constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
       var productId=itemView.tv_product_id_value
        var productName=itemView.tv_product_name_value
        var productDescription=itemView.tv_product_description_value
        var productSellingPrice=itemView.tv_product_selling_price_value
        var productCostPrice=itemView.tv_product_cost_price_value
        var productManufacture=itemView.tv_product_manu_date_value
        var productExpiry=itemView.tv_product_expiry_date_value
        var sellerId=itemView.tv_seller_id_value
        var productQuantity=itemView.product_quantity
        var updateProduct=itemView.btn_view_product_update
        var deleteProduct=itemView.btn_view_product_delete

        fun bind(products:Products){
            productId.setText(products.pId.toString())
            productName.setText(products.pName)
            productDescription.setText(products.pDescription)
            productSellingPrice.setText(products.pSellingPrice.toString())
            productCostPrice.setText(products.pCostPrice.toString())
            productManufacture.setText(products.pManufactureDate)
            productExpiry.setText(products.pExpiryDate)
            sellerId.setText(products.sId.toString())
            productQuantity.setText(products.pQuantity.toString())
//            updateProduct.setOnClickListener(View.OnClickListener {
//
//            })
//            deleteProduct.setOnClickListener(View.OnClickListener {
//
//            })




        }
    }

}