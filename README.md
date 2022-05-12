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
app:layoutManager="androidx.recyclerview.widget.GridLayoutManager" 사용하면 바둑판처럼 배열 가능하다.

### 6. const 
```kotlin
companion object{
        const val FOLLOWER_POSITION = 1
        const val REPO_POSITION = 2
    }
}
```

---

## 실행 화면
|Login - Follower List - Repository List|
|:---:|
|<img src="https://user-images.githubusercontent.com/102457223/164293437-aba09d7d-f982-492d-99cf-a605bcfa34e3.gif" width="250" height="400"/>|
|버튼 클릭시 전환, GridLayout 적용, 설명 길면 ...으로 표시되게 하기|

---

# **Seminar 3**
- [x] HomeActivity를 ProfileFragment로 바꾸기  
- [x] Font 적용 
- [x] bottomNavigation 적용  
- [x] TabLayout 적용
- [x] Button에 Selector 활용하기  
- [x] 이미지 원형으로 표시  
- [x] ViewPager2 중첩 해결

---

## **1. MainActivity**
### 1-1. ViewPager2

-먼저 Profile, Home, Camera Fragment 3개 생성
-activity_main.xml에 ViewPager2를 배치하고 ViewPagerAdapter 생성
```kotlin
//TabViewPagerAdapter.kt
package com.example.a220402

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    val fragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
    return when (position) {
        FOLLOWING_FRAGMENT -> TabFragment1()
        FOLLOWER_FRAGMENT -> TabFragment2()
        else -> throw IndexOutOfBoundsException()
    }
}

    companion object {
    const val FOLLOWING_FRAGMENT = 0
    const val FOLLOWER_FRAGMENT = 1
    }
}
```
 - FragmentStateAdapter 클래스 상속 받음.  
 FragmentStateAdapter는 RecyclerView.Adapter를 상속받는다!

### 1-2. BottomNavigationView  
-bottomNavigationView 하단 메뉴 생성
```xml
//menu_sample.xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/menu_profile"
        android:icon="@drawable/ic_union"
        android:title="프로필" />
    <item
        android:id="@+id/menu_home"
        android:icon="@drawable/ic_home"
        android:title="홈" />
    <item
        android:id="@+id/menu_camera"
        android:icon="@drawable/ic_camera"
        android:title="카메라" />
</menu>
```
- Drawable Resource File에서 이미지 불러와서 icon 지정  
```xml
//selector_color.xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="#6424D5" android:state_pressed="true" /> //눌렀을 때
    <item android:color="#6424D5" android:state_checked="true" />
    <item android:color="#C9C9C9" android:state_checked="false" />
</selector>
```
- selector로 버튼 및 BottomNavigation 눌렸을 때, 눌려 있을 때 등 색상 지정

### 1-3. MainActivity (Adapter)  
```kotlin
//MainActivity.kt
package com.example.a220402

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.a220402.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var testViewAdapter: TestViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initBottomNavi()
    }

    private fun initAdapter() {
        val fragmentList = listOf(ProfileFragment(),HomeFragment(), CameraFragment())
        testViewAdapter = TestViewAdapter(this)
        testViewAdapter.fragments.addAll(fragmentList)

        binding.vpMain.adapter = testViewAdapter
    }

    private fun initBottomNavi() {
        binding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bnvMain.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_profile -> {
                    binding.vpMain.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.vpMain.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpMain.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }
}
```
- 아래 MainActivity에서 initAdapter가 ViewPagerAdpater,  
initBottomNavi가 ViewPager와 BottomNavigationView 연결하는 Adapter
---

## **2. ProfileFragment**

```kotlin
//ProfileFragment.kt
package com.example.a220402

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.a220402.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var position = FOLLOWER_POSITION
    private var _binding: FragmentProfileBinding? = null //fragment로 바꿨기 때문에 _binding
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        initTransactionEvent()
        initImage() //꼭 return 전에 작성해줘야 한다

        return binding.root
    }

    private fun initImage() {
        Glide.with(this)
            .load(R.drawable.uxri)
            .circleCrop()
            .into(binding.image)
    } //이미지 원형으로 크롭

    fun initTransactionEvent() {
        val fragment1 = ProfileFollowerFragment()
        val fragment2 = PfRepoAdapter()

        childFragmentManager.beginTransaction() 
            .add(R.id.fragment_main, fragment1)
            .commit()

        binding.followerbtn.setOnClickListener {
            if (position == REPO_POSITION) {
                childFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment1)
                    .commit()
                position = FOLLOWER_POSITION
            }
        }

        binding.repobtn.setOnClickListener {
            if (position == FOLLOWER_POSITION) {
                childFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment2)
                    .commit()
                position = REPO_POSITION
            }
        }
    }

    companion object {
        const val FOLLOWER_POSITION = 1
        const val REPO_POSITION = 2
    }
}

```
* **childFragmentManager**를 사용하여 중첩 Fragment가 가능하게 한다.   
parentFragmentManager를 사용해도 되지만, 만~약에 BottomNavigationView 중 하나가 사라진다면 예상치 못한 버그가 발생될 수 있으므로 안전하게 childFragmentManager를 사용하자.
* **Glide** 사용하여 Profile에 있는 사진 원형으로 크롭
---

## **3. 폰트 적용하기**
```xml
//noto_sans_kr.xml
<?xml version="1.0" encoding="utf-8"?>
<font-family xmlns:android="http://schemas.android.com/apk/res/android">
    <font
        android:font="@font/noto_sans_kr_thin"
        android:fontWeight="200" />
    <font
        android:font="@font/noto_sans_kr_light"
        android:fontWeight="300" />
    <font
        android:font="@font/noto_sans_kr_regular"
        android:fontWeight="400" />
    <font
        android:font="@font/noto_sans_kr_medium"
        android:fontWeight="500" />
    <font
        android:font="@font/noto_sans_kr_bold"
        android:fontWeight="700" />
    <font
        android:font="@font/noto_sans_kr_black"
        android:fontWeight="900" />
</font-family>
```
- Drawable Resource File에서 font 폴더 생성
- 파일명을 noto_sans_kr_nn으로 변경 후 불러옴
- 각 폰트마다 fontWeight를 부여하여 사용할 수 있도록 xml 파일 생성
---

## **4. TabLayout**
-fragment xml 파일에 TabLayout 추가하기  
-HomeActivity에 TabFragmentAdapter와 initTabLayout 추가하기
```kotlin
//HomeActivity.kt
package com.example.a220402

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a220402.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다")
    private lateinit var sampleTabViewPagerAdapter: TabViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        initAdapter()
        initTabLayout()

        return binding.root
    }

    private fun initAdapter() {
        val fragmentList = listOf(TabFragment1(), TabFragment2())

        sampleTabViewPagerAdapter = TabViewPagerAdapter(this)
        sampleTabViewPagerAdapter.fragments.addAll(fragmentList)

        binding.homevp.adapter = sampleTabViewPagerAdapter
    }

    private fun initTabLayout() {
        val tabLabel = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.hometl,binding.homevp) {tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }
}
```
- initAdapter는 1에서 했던 내용과 동일
- initTabLayout에서 TabLayoutMediator 불러옴
---

## **5. ViewPager2 Scroll 중첩 해결**
- https://github.com/android/views-widgets-samples/blob/master/ViewPager2/app/src/main/java/androidx/viewpager2/integration/testapp/NestedScrollableHost.kt 에서 NestedScrollableHost.kt 파일 불러오기 (구글 깃허브) 
```xml
//fragment_home.xml
<com.example.a220402.NestedScrollableHost
    android:layout_width="match_parent"
    android:layout_height="0dp"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/hometl">

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/homevp"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fontFamily="@font/noto_sans_kr_regular"
        android:includeFontPadding="false"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/hometl" />

</com.example.a220402.NestedScrollableHost> 
```
- 레이아웃 xml 파일에서 <NestedScrollableHost> 태그를 이용해 적용하고자 하는 요소를 감싸준다.
- 이 때 해당 요소는 ViewPager2의 바로 아래에 위치한 유일한 자식이어야 한다!
---
# 💙 **Seminar 3에서 배운 내용**
## 1. 폴더 관련
- Drawable Resource File에서 폰트 폴더 생성하면 자꾸 없어졌었는데 로컬에서 안스 폴더 들어가서 찾았다.. 앞으로 파일이 안보이면 로컬에서 찾아보자..   

## 2. return 문 
```kotlin
...
        initTransactionEvent()
        initImage() //return 전에 작성해줘야 한다

        return binding.root
    }
```
- return 뒤에 무언가를 호출하면 호출이 안되니 꼭 return 앞에서 호출하자!  

## 3. xml : shape, solid, corner
```xml
//rectancle_radius_5
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
<solid android:color="@color/selector_color"/>
<corners android:radius="5dp"/>
</shape>
```
- 버튼 background로 불러올 수 있는 xml 파일이다.  
shape로 모양을 정하고, corners로 모서리 굴곡 정도를 정하고, solid로 색을 정할 수 있는데 나는 color에서 미리 만들어두었던 Button Selector를 불러와서 적용하였다.  

## 4. selector_color
```xml
//selector_color
 <item android:color="#6424D5" android:state_pressed="true" />
    <item android:color="#6424D5" android:state_checked="true" />
    <item android:color="#6424D5" android:state_checked="false" />
```
- state_pressed는 버튼에서 사용하기 위한 내용이고(눌렀을 때), state_checked는 bottomNavi에서 사용하기 위한 내용이다(눌려 있는 상태). 이 내용을 한 xml 파일에 적용하고 각 xml 파일에서 background로 불러오면 된다.

## 5. childFragment
```kotlin
//ProfileFragment
...
fun initTransactionEvent() {
        val fragment1 = ProfileFollowerFragment()
        val fragment2 = PfRepoAdapter()

        childFragmentManager.beginTransaction() 
            .add(R.id.fragment_main, fragment1)
            .commit()
...
```
* **childFragmentManager**를 사용하여 중첩 Fragment가 가능하게 한다.   
parentFragmentManager를 사용해도 되지만, 만~약에 BottomNavigationView 중 하나가 사라진다면 예상치 못한 버그가 발생될 수 있으므로 안전하게 childFragmentManager를 사용하자.

## 6. ViewPager2 중첩 해결
```xml
<com.example.a220402.NestedScrollableHost
...
</com.example.a220402.NestedScrollableHost>
```
- xml에서 위 코드로 ViewPager2를 감싸주고, 구글에서 제공하는  NestedScrollableHost.kt 파일을 추가함으로써 쉽게 중첩 스크롤 문제를 해결할 수 있다.

## 7. Font 적용 시  Padding 제거
```xml
...
android:fontFamily="@font/noto_sans_kr_medium"
android:includeFontPadding="false"
...
```
- font는 xml 파일에서 이런 식으로 불러오는데, 여기서 includeFontPadding을 false로 적용해주면 폰트를 적용했을 때 위아래로 적용된 패딩 값을 없앨 수 있다.

## 8. png와 svg
기기 크기가 모두 달라 디자인이 달라질 수 있다. 이 문제는 px가 아닌 dp로 크기를 지정하는데, svg와 9-patch로 dp로 크기적용이 가능하다. png는 그냥 삽입하면 크기 등이 달라질 수 있기 때문에 9-patch로 변경해주면 된다.

---
# **실행 화면**
| Profile | BottomNavigation | 중첩 해결 |
|:---:|:---:|:---:|
|<img src="https://user-images.githubusercontent.com/102457223/166404906-84ca387d-8a7e-497f-9e3d-3b8de507db35.gif" width="200" height="300"/>|<img src="https://user-images.githubusercontent.com/102457223/166404928-bb7c18da-c904-4394-9f6a-7b8b4842d06a.gif" width="200" height="300"/>|<img src="https://user-images.githubusercontent.com/102457223/166404918-98539fa9-e8e1-4e35-bf8f-e0dbbcc4d3c1.gif" width="200" height="300"/>|
|ButtonSelector, CircleCrop, 원래 만들어 둔 HomeActivity를 ProfileFragment로 변경, BottomNavigation 적용|BottomNavigation으로 Profile, Home, Camera Fragment 넘기기, Home에 TabLayout 적용|ViewPager2에서 발생하게 되는 중첩 문제를 구글이 제시한 방식을 통하여 해결|
---
    
# Seminar 4

- [X] 필수과제
## 1. 로그인, 회원가입 서버통신 구현
### 💜 RequestSignIn
```kotlin
package com.example.a220402

data class RequestSignIn (
    val email: String, 
    val password: String
)
```
- 변수명을 email로 했고 이는 postman의 키 값과 동일하니 Serialized 안 해줘도 된다.  

### 💜 RequestSignup
```Kotlin
package com.example.a220402

data class RequestSignUp (
    val name: String,
    val email: String,
    val password: String
)
```
### 💜 ResponseSignIn
```Kotlin
package com.example.a220402

data class ResponseSignIn(
    val status: Int,
    val message: String,
    val data: Data
) {
    data class Data(
        val email: String,
        val name: String
    )
}
```

### 💜 ResponseSignUp
```Kotlin
package com.example.a220402

data class ResponseSignUp(
    val status: Int,
    val message: String,
    val data: Data
) {
    data class Data(
        val id: Int
    )
}
```

### 💜 SoptService
```Kotlin
package com.example.a220402

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signin")
    fun postLogin(
        @Body body: RequestSignIn
    ): Call<ResponseSignIn>

    @POST("auth/signup")
    fun postSignup(
        @Body body: RequestSignUp
    ): Call<ResponseSignUp>
}
```
- 동기적, 비동기적으로 Type을 받아오는 객체

### 💜 ServiceCreator
```Kotlin
package com.example.a220402

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "http://13.124.62.236/" 
    private const val BASE_URL_GITHUB = "https://api.github.com/"

    private val retrofit:Retrofit = Retrofit.Builder() //생성자 호출
        .baseUrl(BASE_URL) //서버에 메인 URL 전달
        .addConverterFactory(GsonConverterFactory.create()) //gson 컨버터 연동
        .build() //Retrofit 객체 변환

    private val githubRetrofit:Retrofit = Retrofit.Builder() 
        .baseUrl(BASE_URL_GITHUB) 
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val soptService: SoptService = retrofit.create(SoptService::class.java)
    val githubApiService: GithubApiService = githubRetrofit.create(GithubApiService::class.java)
    //interface 객체를 create에 넘겨 실제 구현체 생성
}
```
- BASE_URL = "http://13.124.62.236/" : 메인 서버 도메인  


---
- [X] 성장과제 2-1

### 💜 SignInActivity
```Kotlin
...
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
...

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private fun loginNetwork() {
        val requestSignIn = RequestSignIn(
            email = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )
//서버에 요청을 보내기 위한 RequestData 생성
        val call: Call<ResponseSignIn> = ServiceCreator.soptService.postLogin(requestSignIn)
//싱글톤 객체를 이용해 Retrofit이 만들어준 interface 구현체에 접근하여 Call 객체를 받아온다
        call.enqueue(object : Callback<ResponseSignIn> { 
            override fun onResponse( //Callback 익명클래스 선언
                call: Call<ResponseSignIn>,
                response: Response<ResponseSignIn>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data 
                

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
...

        binding.btn.setOnClickListener() {
            loginNetwork() // 로그인 버튼 눌렀을 때 서버통신 이루어짐
        
...
```
- call.enqueue는 실제 서버통신을 비동기적으로 요청
- if문에서 val data는 null값 올 수 있으므로 nullable 타입

### 💜 SignUpActivity

```Kotlin
...
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
    //함수.. oncreate 밑에 씁시다..

    private fun SignUpNetwork() {
        val requestSignUp = RequestSignUp(
            name = binding.etName.text.toString(),
            email = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )

        val call: Call<ResponseSignUp> = ServiceCreator.soptService.postSignup(requestSignUp)

        call.enqueue(object : Callback<ResponseSignUp> {
            override fun onResponse( //Callback 익명클래스 선언
                call: Call<ResponseSignUp>,
                response: Response<ResponseSignUp>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data //null값 올 수 있으므로 nullable 타입

                    Toast.makeText(this@SignUpActivity, "${data?.id}님 반갑습니다!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
                } else Toast.makeText(this@SignUpActivity, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                Log.e("NetworkTest", "error:$t") //오류처리 코드
            }
        })
    }
}
```

### 💜 ResponseUserInfo
```Kotlin
package com.example.a220402

data class ResponseUserInfo(
        val login : String,
        val avatar_url : String
        )

```
### 💜 GithubApiService
```Kotlin
package com.example.a220402

import retrofit2.Call
import retrofit2.http.GET

interface GithubApiService{
    @GET("users/uxri")
    fun getUserInfo(): Call<ResponseUserInfo>

    @GET("users/uxri/followers")
    fun getFollowingInfo(): Call<List<ResponseUserInfo>>
}
```
- GithubApi에서 받아오는 것

### 💜 ProfileFollowerAdapter
```Kotlin
...
    class FollowerViewHolder(
        private val binding: ItemProfileFollowerListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserInfo) {
            binding.follower = data 
            Glide.with(binding.ivProfile).load(data.avatar_url)
                .circleCrop()
                .into(binding.ivProfile)
        }
    }
...
```
- databinding 사용. 바인딩 이름, 설명 다 할 필요 없이 코드가 한 줄로 줄어들었음.
- Glide로 Github에서 받아온 avatar_url 사진 불러옴

### 💜 ProfileFollowerFragment
ㄴ~~이거진짜속많이썩였다..이마짚...~~
```Kotlin
...
class ProfileFollowerFragment : Fragment() {
    private lateinit var followerAdapter: ProfileFollowerAdapter
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding ?: error("바인딩이 초기화되지 않았습니다")
    var responseData = mutableListOf<ResponseUserInfo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUserInfoNetwork()
        followerAdapter = ProfileFollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
    }

    private fun initUserInfoNetwork() {
        val call: Call<List<ResponseUserInfo>> = ServiceCreator.githubApiService.getFollowingInfo()

        call.enqueue(object : Callback<List<ResponseUserInfo>> {
            override fun onResponse(
                call: Call<List<ResponseUserInfo>>,
                response: Response<List<ResponseUserInfo>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        followerAdapter.followerList = it.toMutableList()
                        followerAdapter.notifyDataSetChanged()
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<List<ResponseUserInfo>>, t: Throwable) {
            }


        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

```
- onCreateView에서 함수 다 호출하지 말고 override fun onViewCreated에서 하자!
- followerAdapter.notifyDataSetChanged 제발 쓰자 이거 안쓰면 안뜬다고...

### 💜 item_profile_follower_list
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

        <variable
            name="follower"
            type="com.example.a220402.ResponseUserInfo" />

    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="10dp"
        android:layout_marginVertical="10dp"
        android:background="@drawable/round">

        <ImageView
            android:id="@+id/iv_profile"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_marginHorizontal="5dp"
            android:layout_marginVertical="5dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintDimensionRatio="1:1"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintWidth_percent="0.25" />

        <TextView
            android:id="@+id/tv_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="20dp"
            android:fontFamily="@font/noto_sans_kr_bold"
            android:includeFontPadding="false"
            android:text="@{follower.login}"
            android:textColor="@color/black"
            android:textSize="25sp"
            android:textStyle="bold"
            app:layout_constraintStart_toEndOf="@+id/iv_profile"
            app:layout_constraintTop_toTopOf="@+id/iv_profile"
            app:layout_constraintBottom_toBottomOf="@id/iv_profile"
            tools:text="최유리" />


    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```
- databinding을 해주었습니다. follower안에 이미지랑 로그인이 들어가있습니다~
- ImageView에서 src를 지웠습니다... Glide 해줬기 때문에 src도 있으면 중복되니까! 둘다 있었을 때 src 때문에 이미지가 처음에만 뜨고 프래그먼트 넘기거나 하면 사진이 안 떴었다.

### 💜 fragment_follower.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".ProfileFollowerFragment">

        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/rv_Follower"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
            tools:itemCount="8"
            tools:listitem="@layout/item_profile_follower_list" />

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```
- 얘두 마찬가지로 데이터바인딩 해줬습니다!
---
# **실행 화면**
| 로그인 | POSTMAN |
|:---:|:---:|
|<img src="https://user-images.githubusercontent.com/102457223/168145631-bff89caa-da01-4bc1-954d-ab5ec64643aa.gif" width="300" height="400"/>|<img width="800" alt="로그인포스트맨" src="https://user-images.githubusercontent.com/102457223/168148653-03389591-4037-4cd4-a185-3d1d41dbe0d1.png">|
| 회원가입 | POSTMAN |
|<img src="https://user-images.githubusercontent.com/102457223/168145640-1b423aab-54ce-4444-841c-2163e8c62f85.gif" width="300" height="400"/>|<img width="800" alt="회원가입포스트맨" src="https://user-images.githubusercontent.com/102457223/168148746-6c3dbef3-d01d-4851-a1dd-ab2b971a8621.png">|
| Github | POSTMAN |
|<img src="https://user-images.githubusercontent.com/102457223/168150317-ebad5645-957d-456f-9811-fff60e1e90a9.gif" width="300" height="400"/>|<img src="https://user-images.githubusercontent.com/102457223/168150437-f33b8469-b253-4f65-a200-fd7672702a96.gif" width="800" height="400"/>|
---
## 💙 Seminar 4에서 배운 내용

### 1. 버튼 크기 설정

```xml
android:layout_width="0dp"
android:layout_height="wrap_content"
app:layout_constraintHorizontal_bias="0.5"
```

width와 height 값을 직접 입력하는 것보다 width 값을 0으로, height 값은 parent로 준 후에constraintHorizontal_bias에서 비율로 설정해주는 것이 좋습니다!

### 2. FragmentContainerView

```xml
<androidx.fragment.app.FragmentContainerView
android:id="@+id/fragment_main"
android:layout_width="match_parent"
android:layout_height="0dp"
app:layout_constraintEnd_toStartOf="parent"
app:layout_constraintStart_toEndOf="parent"
app:layout_constraintTop_toBottomOf="@+id/repobtn"/>
```

버튼 내용 수정하다가 FragmentContainerView 날려먹고 코틀린 파일에서 container id 오류 뜬다고 몇십분 헤매다가... 갑자기 뭔가를 지워버린 것 같은게 생각나서.. 깃헙에서 빨리 데려오니 괜찮아졌습니다... 다음부턴 이 중요한걸 날려먹는 바보짓을 하지말자...   
~~바보같은 나 도와준 천재 짱수빈 사랑해~~

### 3. import

```kotlin
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
```

제발 까먹지 말고 추가합시다  
" option enter " 로

### 4. 프래그먼트 이름은 의미있는 것으로 꼭꼭 바꿔줍시다
- TabFragment1 이런 의미없는건 안돼ㅡㅡ

### 5. Glide랑 src
```kotlin
    class FollowerViewHolder(
        private val binding: ItemProfileFollowerListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserInfo) {
            binding.follower = data 
            Glide.with(binding.ivProfile).load(data.avatar_url)
                .circleCrop()
                .into(binding.ivProfile)
```
- ImageView에서 src를 아예 지워줬습니다! 액티비티에서 Glide를 해줬기 때문에 src도 있으면 중복되니까! 둘다 있었을 때 src 때문에 이미지가 처음에만 뜨고 프래그먼트 넘기거나 하면 사진이 안 뜨는 일이 발생합니다..   
~~저를 구제해주신 승현오빠에게 압도적 감사를 드립니다!!!~~

### 6. ProfileFollowerFragment에서-1
```kotlin
 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUserInfoNetwork()
        followerAdapter = ProfileFollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
    }
```
- 함수를 onCreateView에서 다 호출하지 말고 이렇게 해줍시다~

### 7. ProfileFollowerFragment에서-2
```kotlin
private fun initUserInfoNetwork() {
        val call: Call<List<ResponseUserInfo>> = ServiceCreator.githubApiService.getFollowingInfo()

        call.enqueue(object : Callback<List<ResponseUserInfo>> {
            override fun onResponse(
                call: Call<List<ResponseUserInfo>>,
                response: Response<List<ResponseUserInfo>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        followerAdapter.followerList = it.toMutableList()
                        followerAdapter.notifyDataSetChanged()
                    }
                } else {
                }
            }
```
- 사실 이 부분이 완벽히 이해되지는 않았습니다.. ResponseUserInfo에서 데이터를 리스트로 받아온다는 것은 알았습니다!  
- MutableList가 이해되지 않아서 더 공부해 볼 예정입니다.

### 8. notifyDataSetChanged()
```kotlin
 followerAdapter.notifyDataSetChanged()
 ```
 - 이 친구를 해주지 않으면 보이지 않습니다...  
 ~~이거때문에 고생한 용민오빠에게 영광을(?) 돌립니댜..~~

### 9. 함수는 onCreate 밑에 씁시다
```kotlin
binding.btnFinshSignup.setOnClickListener
```
이게 왜 맨 뒤로 가있었죠 최유리씨? 정신차리세요

 ### 10. 꿀팁 아닌 꿀팁
 - Local History로 되돌리기가 가능합니다
 - command + F로 뭐 예를 들어 ResponseUserInfo가 있는 내용을 찾고싶다, 하면 바로 검색이 가능합니다.
