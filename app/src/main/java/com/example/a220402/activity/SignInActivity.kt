@file:Suppress("UnusedImport")

package com.example.a220402.activity

import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import android.app.Activity
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

        val intent = Intent(this, MainActivity::class.java)

        binding.btn.setOnClickListener() {
            if (binding.etId.text.isNullOrBlank() || binding.etPw.text.isNullOrBlank()) {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                if(binding.etId.text.isNotEmpty() || binding.etPw.text.isNotEmpty()) {
                    loginNetwork() // 로그인 버튼 눌렀을 때 빈칸이 아닌 경우 서버통신 이루어짐
                }
            }
        }

        binding.btnSignup.setOnClickListener() {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
            //signup 버튼을 누르면 SignUpActivity로 이동, intent 객체를 lancher에 실어 이동.
        }
    }

    private fun loginNetwork() {
        val requestSignIn = RequestSignIn(
            email = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )
        //서버에 요청을 보내기 위한 RequestData 생성
        val call: Call<ResponseSignIn> = ServiceCreator.soptService.postLogin(requestSignIn)
        //싱글톤 객체를 이용해 Retrofit이 만들어준 interface 구현체에 접근하여 Call 객체를 받아온다
        call.enqueue(object : Callback<ResponseSignIn> { //실제 서버통신을 비동기적으로 요청
            override fun onResponse( //Callback 익명클래스 선언
                call: Call<ResponseSignIn>,
                response: Response<ResponseSignIn>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data //null값 올 수 있으므로 nullable 타입

                    Toast.makeText(
                        this@SignInActivity,
                        "${data?.email}님 반갑습니다!",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                } else Toast.makeText(this@SignInActivity, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                Log.e("NetworkTest", "error:$t") //오류처리 코드
            }
        })
    }

}