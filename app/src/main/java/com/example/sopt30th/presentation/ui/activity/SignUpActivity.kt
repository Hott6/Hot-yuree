package com.example.sopt30th.presentation.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sopt30th.databinding.ActivitySignUpBinding
import com.example.sopt30th.data.model.request.RequestSignUp
import com.example.sopt30th.util.ServiceCreator
import com.example.sopt30th.util.enqueueUtil
import com.example.sopt30th.util.showToast
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickEvent()
    }

    private fun clickEvent() {
        binding.btnFinshSignup.setOnClickListener {
            if (binding.etName.text.isNullOrBlank() || binding.etId.text.isNullOrBlank() || binding.etPw.text.isNullOrBlank()) {
                showToast("입력되지 않은 정보가 있습니다")
            } else {
                val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                intent.putExtra("id", et_id.text.toString()) //id에 et_id 데이터 담음
                intent.putExtra("pw", et_pw.text.toString()) //마찬가지로 pw에 et_pw 담음
                setResult(Activity.RESULT_OK, intent) //result_ok인 경우 SignInActivitiy로 intent 객체 보냄
                SignUpNetwork()
                finish()
            }
        }
    }

    //    //함수.. oncreate 밑에 씁시다..

    private fun SignUpNetwork() {
        val requestSignUp = RequestSignUp(
            name = binding.etName.text.toString(),
            email = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )

        val call = ServiceCreator.soptService.postSignup(requestSignUp)

        call.enqueueUtil(
            onSuccess = {
                it.data
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            },
            onError = {
                showToast("회원가입에 실패하였습니다")
            }
        )
    }
}