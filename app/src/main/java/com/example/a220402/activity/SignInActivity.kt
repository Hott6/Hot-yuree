@file:Suppress("UnusedImport")

package com.example.a220402.activity

import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.a220402.request.RequestSignIn
import com.example.a220402.response.ResponseSignIn
import com.example.a220402.databinding.ActivitySignInBinding
import com.example.a220402.request.RequestSignUp
import com.example.a220402.response.ResponseWrapper
import com.example.a220402.util.LoginSharedPreferences
import com.example.a220402.util.ServiceCreator

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
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
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater) //inflate는 xml의 뷰를 객체화해준다고 생각하자
        setContentView(binding.root)

        setOnClickListener()
        initClickEvent()
        isAutoLogin()

    }
    private fun setOnClickListener() {
        binding.btn.setOnClickListener {
            if (binding.etId.text.isNullOrBlank() || binding.etPw.text.isNullOrBlank()) {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                loginNetwork() // 로그인 버튼 눌렀을 때 빈칸이 아닌 경우 서버통신 이루어짐
            }
        }

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
            //signup 버튼을 누르면 SignUpActivity로 이동, intent 객체를 lancher에 실어 이동.
        }
    }

    private fun initClickEvent(){
        binding.btnAutoLogin.setOnClickListener{
            binding.btnAutoLogin.isSelected = !binding.btnAutoLogin.isSelected

            LoginSharedPreferences.setAutoLogin(this, binding.btnAutoLogin.isSelected)
        }
    }

    private fun isAutoLogin() {
        if(LoginSharedPreferences.getAutoLogin(this)){
            showToast("자동로그인 되었습니다")
            startActivity(Intent(this@SignInActivity, MainActivity::class.java))
            finish()
        }
    }

    private fun loginNetwork() {
        val requestSignIn = RequestSignIn(
            email = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )
        //서버에 요청을 보내기 위한 RequestData 생성
        val call: Call<ResponseWrapper<ResponseSignIn>> =
            ServiceCreator.soptService.postLogin(requestSignIn)
        //싱글톤 객체를 이용해 Retrofit이 만들어준 interface 구현체에 접근하여 Call 객체를 받아온다
        call.enqueueUtil(
            onSuccess = {
                showToast("로그인에 성공하였습니다")
            },
            onError = {
                showToast("로그인에 실패하였습니다")
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
            override fun onResponse(
                call: Call<ResponseType>,
                response: Response<ResponseType>
            ) {
                if (response.isSuccessful) {
                    onSuccess.invoke((response.body() ?: return))
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
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