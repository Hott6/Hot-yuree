package com.example.sopt30th.presentation.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.sopt30th.R
import com.example.sopt30th.databinding.ActivitySignUpBinding
import com.example.sopt30th.presentation.ui.base.BaseActivity
import com.example.sopt30th.presentation.ui.viewmodel.SignUpViewModel
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_sign_up
    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewmodel = signUpViewModel
        binding.lifecycleOwner = this
        clickEvent()
    }

    private fun clickEvent() {
        signUpViewModel.signUp()
        val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
        intent.putExtra("id", et_id.text.toString()) //id에 et_id 데이터 담음
        intent.putExtra("pw", et_pw.text.toString()) //마찬가지로 pw에 et_pw 담음
        setResult(Activity.RESULT_OK, intent) //result_ok인 경우 SignInActivitiy로 intent 객체 보냄

        finish()
    }
}

//    private fun signUpNetwork() {
//        val requestSignUp = RequestSignUp(
//            name = binding.etName.text.toString(),
//            email = binding.etId.text.toString(),
//            password = binding.etPw.text.toString()
//        )
//
//        val call = ServiceCreator.soptService.postSignup(requestSignUp)
//
//        call.enqueueUtil(
//            onSuccess = {
//                it.data
//                val intent = Intent(this, SignInActivity::class.java)
//                startActivity(intent)
//            },
//            onError = {
//                showToast("회원가입에 실패하였습니다")
//            }
//        )
//    }
