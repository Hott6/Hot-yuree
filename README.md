# **Seminar 1**

## 1. SignInActivity

- [X] 로그인 페이지 만들기

```kotlin
@file:Suppress("UnusedImport")

package com.example.a220402

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.a220402.databinding.ActivitySignInBinding
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        //registerForActivityResult : Activityresult에 대한 콜백 생성, Lancher 생성
        {
            if (it.resultCode == Activity.RESULT_OK) { //result_ok인 경우 수행
                val id = it.data?.getStringExtra("id") ?: "" //?. 연산은 엘비스 연산자임. 왼쪽 피연산자 값이 null이 아니면 id 출력
                val pw = it.data?.getStringExtra("pw") ?: ""
                binding.etId.setText(id)
                binding.etPw.setText(pw)
            }
        }
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater) //inflate는 xml의 뷰를 객체화해준다고 생각하자
        setContentView(binding.root)

        val intent = Intent(this, HomeActivity::class.java)

        binding.btn.setOnClickListener() {
            if (binding.etId.text.isNullOrBlank() || binding.etPw.text.isNullOrBlank()) {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            //isNullOrBlank 함수 사용, id와 pw 둘 중 하나만 비어있어도 Toast 출력
            } else {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                //로그인 성공 시 홈 화면으로 이동
            }
        }

        binding.btnSignup.setOnClickListener() {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
            //signup 버튼을 누르면 SignUpActivity로 이동, intent 객체를 lancher에 실어 이동.
        }
    }
}

```

- **if** : 빈칸인 경우를 작성할 때, if문 내에서 "" 같은 내용을 쓸 수도 있지만, isNullOrBlank 같은 함수를 이용해보는 것도 좋은 방법인 듯!

---

## 2. SignInActivity

- [X] 회원가입 페이지 만들기

```kotlin
package com.example.a220402

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a220402.databinding.ActivitySignUpBinding
import kotlinx.android.synthetic.main.activity_sign_in.*

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
                val intent = Intent(this@SignUpActivity, SignInActivity::class.java) //signinactivity에 대한 intent 객체 생성
                intent.putExtra("id", et_id.text.toString()) //id에 et_id 데이터 담음
                intent.putExtra("pw", et_pw.text.toString()) //마찬가지로 pw에 et_pw 담음
                setResult(Activity.RESULT_OK, intent) //result_ok인 경우 SignInActivitiy로 intent 객체 보냄

                finish()
            }
        }
    }
}

```

- **CallBack** : 다른 함수의 인자로써 이용되는 함수 / 이벤트에 의해 호출되는 함수
- **putextra** : putextra를 통해 데이터 담아서 전달이 가능하다.
- **RESULT_OK** : setResult(Activity.RESULT_OK, intent)에서 결과 ok면 intent 객체 보낸다.

---

## 💙Seminar 1에서 배운 내용

### 1. ScrollView 밑에는 하나의 직계 자식만 가질 수 있다.

그렇기 때문에

```xml
<ScrollView
        android:id="@+id/sv_profile"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <LinearLayout

        //(.. ImageView와 TextView들 .. )

        </LinearLayout>
    </ScrollView>

</androidx.constraintlayout.widget.ConstraintLayout>

```

이처럼 ScrollView 밑에 LinearLayout 하나 넣고, 그 밑에 TextView와 ImageView등을 넣어주면 된다.

### 2. android:gravity="center"

중심을 가운데다 줄 수 있다는 의미.

### 3. 엘비스 연산자

?. 연산은 엘비스 연산자이다.

```kotlin
val a = 왼쪽?. : 오른쪽
val b = a?.length ?:0
```

왼쪽 피연산자 값이 null이 아니면 그 피연산자 값을 반환하고 null이면 오른쪽 피연산자의 결과값을 반환

### 4. 라인 정리

```
ctl + alt + l
```

### 5. Inflate

xml의 뷰를 객체화해준다고 생각하면 된다. 더 쉽게 말하면 실체화 시키는 것.

### 6. registerForActivityResult

Activityresult에 대한 콜백 생성, Lancher 생성.

### 7. 객체화

```kotlin
val intent = Intent(this, HomeActivity::class.java)
```

HomeActivity에 대한 intent 객체 생성

---

## 실행 화면
|Login|Join|
|---|---| 
|<img src="https://user-images.githubusercontent.com/102457223/164253856-2c2426a5-70ae-4025-81b6-adaef0a05449.gif" width="250" height="400"/>|<img src="https://user-images.githubusercontent.com/102457223/164253825-092d822e-0dfd-4ae1-8eec-f04b6806827c.gif" width="250" height="400"/>|
|아이디, 비밀번호 중 하나라도 미입력 시 로그인 불가, 로그인 시 로그인 성공 토스트 출력, MY INFO 스크롤뷰 구현|회원가입 내용 중 하나라도 미입력시 회원가입 불가, 회원가입 아이디, 비밀번호가 로그인 시 유지|  

---

# **Seminar 2**

## 1. Follower
### 1-1. FollowerData.kt
``` Kotlin
package com.example.a220402

data class FollowerData(
    val image: Int,
    val name : String,
    val introduction : String
)
```
- **dataclass** : 리스트로 보여줄 데이터를 담는 클래스라고 생각하기  

### 1-2. FollowerViewHolder.kt
```kotlin
package com.example.a220402

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a220402.databinding.ItemFollowerListBinding

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    val followerList = mutableListOf<FollowerData>()

    class FollowerViewHolder(
        private val binding: ItemFollowerListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData) {
            binding.ivProfile.setImageResource(data.image)
            binding.tvName.text = data.name
            binding.tvIntro.text = data.introduction
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemFollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }


    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    override fun getItemCount(): Int = followerList.size
}
```
- class FollowerAdapter 안에 class FollowerViewHolder가 있는 **NestedClass** 구조!

- **onCreateViewHolder** : parent에 들어온 뷰그룹을 받아서 해당 뷰그룹이 어떤 흐름에 생성되어야 할지 정보를 가지고 있고, 정보를 LayoutInflater에 넘겨준다. 이후 생성된 뷰 객체를 return한다!

- **onBindViewHolder** : ViewHolder와 position의 데이터 결합

- **getItemCount** : 전체 데이터 개수

### 1-3. fragment_follower.xml (RecyclerView) 
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".FollowerFragment">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rv_Follower"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
        tools:itemCount="8"
        tools:listitem="@layout/item_follower_list"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```
- **itemcount** = 8개로 지정
- listitem = item_follower_list.xml을 리스트에 띄움

### 1-4. FollowerFragment.kt
```kotlin
package com.example.a220402

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a220402.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private lateinit var followerAdapter: FollowerAdapter
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        initAdapter()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter

        followerAdapter.followerList.addAll(
            listOf(
                FollowerData(R.drawable.yr, "최유리", "안드로이드 YB 파트원 치코리타"),
                FollowerData(R.drawable.yj, "최윤정", "안드로이드 YB 파트원 마자용"),
                FollowerData(R.drawable.sb, "김수빈", "안드로이드 OB 파트원 라이츄"),
                FollowerData(R.drawable.jw, "이준원", "안드로이드 YB 파트원 꼬지모"),
                FollowerData(R.drawable.ym, "권용민", "안드로이드 OB 파트원 알통몬")
            )
        )
        followerAdapter.notifyDataSetChanged()
    }
}
```
- Adapter 초기화 후 Adapter와 RecyclerView 연동
- List로 보여줄 데이터를 Adapter에 넣고(listOf), Adapter에 전체 리스트의 데이터가 갱신되었다고 알려줌
---

## 2. HomeActivity 
-[x] fragment간 전환 (follower <-> repo)
```kotlin
package com.example.a220402

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a220402.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private var position = FOLLOWER_POSITION
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTransactionEvent()
    }

    fun initTransactionEvent() {
        val fragment1 = FollowerFragment()
        val fragment2 = RepoFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragment_main, fragment1).commit()

        binding.followerbtn.setOnClickListener {
            if (position == REPO_POSITION) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment1)
                    .commit()
                position = FOLLOWER_POSITION
            }
        }

        binding.repobtn.setOnClickListener {
            if (position == FOLLOWER_POSITION) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment2)
                    .commit()
                position = REPO_POSITION
            }
        }
    }
```
- **add()** : 프래그먼트를 추가, replace는 교체!
- **beginTransaction()** : 트랜잭션 추가 or 교체 or 삭제 생성
- **commit()** : 커밋을 꼭 해야 작업 수행!
- **companion object** : 상수 값 선언, 클래스에 하나만 존재

---
## 3. drawable (버튼 Gradient, 테두리)
### 3-1. gradient1.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item>
        <shape>
            <gradient android:angle="270" android:endColor="#009999" android:startColor="#bbffff" />
            <corners android:radius="10dp" />
            <stroke android:width="5dp" android:color="#bbffff" />
        </shape>
    </item>
</selector>
```
- **stroke** : 테두리 굵기 선정
- 버튼에서 **android:background="@drawable/gradient1"** 으로 불러옴
### 3-2. round.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item>
        <shape
            android:shape="rectangle">
            <corners
                android:radius="10dp"/>
            <stroke
                android:width="2dp"
                android:color="@color/purple_200" />
        </shape>
    </item>
</selector>
```
- item_follower_list에서 **android:background="@drawable/round"** 로 불러옴

## 4. strings.xml
```xml
<resources>
    <string name="app_name">220402</string>
    <string name="profile">이름 : 최유리\n나이 : 빠른.. 23살\nMBTI : ESFP\n</string>
</resources>
```
- TextView 안에 **android:text="@string/profile"** 로 불러옴
- 긴 내용을 따로 정리함으로써 코드가 더 간결해짐!

---

## 💙Seminar 2에서 배운 내용
### 1. ViewHolder와 Adapter
ViewHolder는 틀이라고 생각하면 이해하기 편하다..! 내용을 담는 그릇  
Adapter는 ViewHolder를 생성하고 ItemLayout을 ViewHolder에 넘겨준다!

### 2. drawable 활용
TextView나 button 테두리, 그라데이션 같은 경우 불러와서 사용할 수 있다!

### 3. 이미지 변수
dataclass에 **val image: Int** 로 이미지 변수를 추가하고,  
**R.drawable.이미지이름**을 list에 추가,  
FollowerViewHolder 클래스 안에 **binding.ivProfile.setImageResource(data.image)** 해주면
이미지도 리스트의 이름, 소개처럼 사람마다 변경할 수 있다!

### 4. alt + enter
오버라이딩 해주어야 하는 경우 alt + enter 누르면 오버라이딩이 자동으로 뜸! 오버라이딩 다 칠 필요 없어서 편하게 할 수 있다..!

### 5. GridLayout
app:layoutManager="androidx.recyclerview.widget.GridLayoutManager" 사용하면 바둑판처럼 배열 가능하다.- 

### 6. const 
```kotlin
companion object{
        const val FOLLOWER_POSITION = 1
        const val REPO_POSITION = 2
    }
}
```
**const** 붙은 이유는 런타임이 아니라 컴파일타임에 1, 2로 초기화되기 때문이다.

---

## 실행 화면
|Login - Follower List - Repository List|
|:---:|
|<img src="https://user-images.githubusercontent.com/102457223/164293437-aba09d7d-f982-492d-99cf-a605bcfa34e3.gif" width="250" height="400"/>|
|버튼 클릭시 전환, GridLayout 적용, 설명 길면 ...으로 표시되게 하기|
---