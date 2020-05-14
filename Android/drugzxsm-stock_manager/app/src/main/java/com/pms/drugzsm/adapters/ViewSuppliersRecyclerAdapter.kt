package com.pms.drugzsm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pms.drugzsm.R
import com.pms.drugzsm.datamodels.api.Supplier
import com.pms.drugzsm.datamodels.api.Suppliers
import com.pms.drugzsm.ui.main.ManageVM
import kotlinx.android.synthetic.main.layout_supplier_list_item.view.tv_supplier_name
import kotlinx.android.synthetic.main.layout_view_supplier_item.view.*

class ViewSuppliersRecyclerAdapter(viewModel: ManageVM) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: ArrayList<Suppliers> =ArrayList()
    var viewModel: ManageVM = viewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return SupplierVH(LayoutInflater.from(parent.context).inflate(R.layout.layout_view_supplier_item,parent,false))
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is SupplierVH->{
                holder.bind(items.get(position))
                holder.supplierUpdate.setOnClickListener {
                    viewModel.updateThisSupplier(items.get(position))

                }
                holder.supplierDelete.setOnClickListener {
                    viewModel.deleteThisSupplier(items.get(position).supplierId)
                    items.remove(items.get(position))
                    notifyDataSetChanged()

                }
            }
        }
    }
    fun setSupplierList(suppliers:ArrayList<Suppliers>){
        items=suppliers
    }


    override fun getItemCount(): Int {
        return items.size }

    class SupplierVH constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        val supplierId=itemView.tv_supplier_id_value
        val supplierName=itemView.tv_supplier_name
        val supplierContact=itemView.tv_supplier_contact_value
        val supplierAddress=itemView.tv_supplier_address_value
        val supplierEmail=itemView.tv_supplier_email_value
        val supplierLicense=itemView.tv_supplier_license_value
        val supplierUpdate=itemView.btn_view_supplier_update
        val supplierDelete=itemView.btn_view_supplier_delete

        fun bind(supplier: Suppliers){
            println("SUPPLIER"+supplier.toString())
            supplierId.setText(supplier.supplierId.toString())
            supplierName.setText(supplier.supplierName)
            supplierContact.setText(supplier.contactNo.toString())
            supplierAddress.setText(supplier.address.toString())
            supplierEmail.setText(supplier.email)
            supplierLicense.setText(supplier.license)


        }
    }

}