package com.example.a220402.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a220402.databinding.ActivitySignUpBinding
import com.example.a220402.request.RequestSignUp
import com.example.a220402.util.ServiceCreator
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFinshSignup.setOnClickListener {
            if (binding.etName.text.isNullOrBlank() || binding.etId.text.isNullOrBlank() || binding.etPw.text.isNullOrBlank()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
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
            },
            onError = {
                showToast("회원가입에 실패하였습니다")
            }
        )
    }

    fun Context.showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun <ResponseType> Call<ResponseType>.enqueueUtil(
        onSuccess: (ResponseType) -> Unit,
        onError: ((stateCode: Int) -> Unit)? = null
    ) {
        this.enqueue(object : Callback<ResponseType> {
            override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
                if (response.isSuccessful) {
                    onSuccess.invoke((response.body() ?: return))
                    startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
                } else {
                    onError?.invoke(response.code())
                }
            }

            override fun onFailure(call: Call<ResponseType>, t: Throwable) {
                Log.d("NetWorkTest", "error:$t")
            }
        })
    }
}