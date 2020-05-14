package com.pms.drugzx.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pms.drugzx.databinding.ActivityLoginBinding
import com.pms.drugzx.ui.main.MainActivity
import com.pms.drugzx.datamodels.Login
import com.pms.drugzx.datamodels.api.User
import com.pms.drugzx.repo.MainRepository
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginVM::class.java)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        viewModel.getLoginInfo().observe(this, Observer {login->
//
//            println("LOGIN MODEL DATACHANGE: $login")
//        })
    }

        fun navMainActivity(view: View) {

        if(input_username.text.isNotEmpty()&& input_password.text.isNotEmpty()){

            val login= Login(
                input_username.text.toString(),
                input_password.text.toString()
            )

            viewModel.setLoginInfo(login)

            viewModel.user.observe(this, Observer {
                println("LOGIN: $it")
              if( it?.userName.equals(login.userName)&&it?.password.equals(login.password)){
                  val intent = Intent(this, MainActivity::class.java)
                  startActivity(intent)
              }
                else{
                  Toast.makeText(this,"Invalid Login", Toast.LENGTH_SHORT).show()
              }
            })

        }
        else{
            Toast.makeText(this,"Username/Password is Empty ", Toast.LENGTH_SHORT).show()
        }



    }
}
