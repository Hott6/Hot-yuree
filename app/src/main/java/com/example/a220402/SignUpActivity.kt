package com.example.a220402

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a220402.databinding.ActivitySignUpBinding
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
                val intent = Intent(
                    this@SignUpActivity,
                    SignInActivity::class.java
                ) //signinactivity에 대한 intent 객체 생성
                intent.putExtra("id", et_id.text.toString()) //id에 et_id 데이터 담음
                intent.putExtra("pw", et_pw.text.toString()) //마찬가지로 pw에 et_pw 담음
                setResult(Activity.RESULT_OK, intent) //result_ok인 경우 SignInActivitiy로 intent 객체 보냄
                SignUpNetwork()
                finish()
            }
        }
    }

    private fun SignUpNetwork() {
        val requestSignUp = RequestSignUp(
            email = binding.etId.text.toString(),
            name = binding.etName.text.toString(),
            password = binding.etPw.text.toString()
        )
//서버에 요청을 보내기 위한 RequestData 생성
        val call: Call<ResponseSignUp> = ServiceCreator.soptService.postSignup(requestSignUp)
//싱글톤 객체를 이용해 Retrofit이 만들어준 interface 구현체에 접근하여 Call 객체를 받아온다
        call.enqueue(object : Callback<ResponseSignUp> { //실제 서버통신을 비동기적으로 요청
            override fun onResponse( //Callback 익명클래스 선언
                call: Call<ResponseSignUp>,
                response: Response<ResponseSignUp>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data //null값 올 수 있으므로 nullable 타입

                    Toast.makeText(
                        this@SignUpActivity,
                        "${data?.id}님 반갑습니다!",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
                } else Toast.makeText(this@SignUpActivity, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                Log.e("NetworkTest", "error:$t") //오류처리 코드
            }
        })
    }
}
