@file:Suppress("UnusedImport")

package com.example.sopt30th.presentation.ui.activity

import retrofit2.Call
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.sopt30th.R
import com.example.sopt30th.databinding.ActivitySignInBinding
import com.example.sopt30th.data.model.request.RequestSignIn
import com.example.sopt30th.data.model.response.ResponseSignIn
import com.example.sopt30th.data.model.response.ResponseWrapper
import com.example.sopt30th.presentation.ui.base.BaseActivity
import com.example.sopt30th.presentation.ui.viewmodel.SignInViewModel
import com.example.sopt30th.util.LoginSharedPreferences
import com.example.sopt30th.util.ServiceCreator
import com.example.sopt30th.util.enqueueUtil
import com.example.sopt30th.util.showToast

class SignInActivity : BaseActivity<ActivitySignInBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_sign_in
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val signInViewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewmodel = signInViewModel
        binding.lifecycleOwner = this

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        //registerForActivityResult : Activityresult에 대한 콜백 생성, Lancher 생성
        {
            if (it.resultCode == Activity.RESULT_OK) { //result_ok인 경우 수행
                val id = it.data?.getStringExtra("id") ?: "" //?. 연산은 엘비스 연산자
                val pw = it.data?.getStringExtra("pw") ?: ""
                binding.etId.setText(id)
                binding.etPw.setText(pw)
            }
        }

        login()
        signUp()
        initClickEvent()
        isAutoLogin()
    }

    private fun signUp() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun login() {
        signInViewModel.login()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun initClickEvent() {
        binding.btnAutoLogin.setOnClickListener {
            binding.btnAutoLogin.isSelected = !binding.btnAutoLogin.isSelected

            LoginSharedPreferences.setAutoLogin(this, binding.btnAutoLogin.isSelected)
        }
    }

    private fun isAutoLogin() {
        if (LoginSharedPreferences.getAutoLogin()) {
            showToast("자동로그인 되었습니다")
            startActivity(Intent(this@SignInActivity, MainActivity::class.java))
            finish()
        }
    }
}


//    private fun loginNetwork() {
//        val requestSignIn = RequestSignIn(
//            email = binding.etId.text.toString(),
//            password = binding.etPw.text.toString()
//        )
//        val call: Call<ResponseWrapper<ResponseSignIn>> =
//            ServiceCreator.soptService.postLogin(requestSignIn)
//        //싱글톤 객체를 이용해 Retrofit이 만들어준 interface 구현체에 접근하여 Call 객체를 받아온다
//        call.enqueueUtil(
//            onSuccess = {
//                showToast("로그인에 성공하였습니다")
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//            },
//            onError = {
//                showToast("로그인에 실패하였습니다")
//            }
//        )
//    }
