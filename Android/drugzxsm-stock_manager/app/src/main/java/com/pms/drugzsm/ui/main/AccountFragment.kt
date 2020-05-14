package com.pms.drugzsm.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pms.drugzsm.R
import com.pms.drugzsm.databinding.FragmentAccountBinding
import com.pms.drugzsm.ui.login.LoginActivity
import com.pms.drugzsm.ui.login.LoginVM


/**
 * A simple [Fragment] subclass.
 */
class AccountFragment : Fragment() ,View.OnClickListener{

    private var _binding: FragmentAccountBinding? = null
    lateinit var navController: NavController
    private lateinit var viewModel:LoginVM
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginVM::class.java)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btn_edit_account).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_logout_account).setOnClickListener(this)
        binding.tvUserType.setText(viewModel.getUser().role.roleName)
        binding.tvUserId.setText(viewModel.getUser().userId.toString())
        binding.tvUserName.setText(viewModel.getUser().userName)
        binding.tvUserEmail.setText(viewModel.getUser().email)
        binding.tvUserPhone.setText(viewModel.getUser().contactNo.toString())
        binding.tvUserAddress.setText(viewModel.getUser().address)

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onClick(v: View?) {

        when(v!!.id) {
            R.id.btn_edit_account -> {
                navController!!.navigate(
                    R.id.editAccountFragment
                )
            }
            R.id.btn_logout_account -> {
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }


          }

}
