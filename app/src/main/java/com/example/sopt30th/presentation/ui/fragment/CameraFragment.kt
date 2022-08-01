package com.example.sopt30th.presentation.ui.fragment

import android.Manifest
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.sopt30th.R
import com.example.sopt30th.databinding.FragmentCameraBinding
import com.example.sopt30th.presentation.ui.base.BaseFragment

class CameraFragment : BaseFragment<FragmentCameraBinding>() {
    override val TAG: String
        get() = CameraFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_camera

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeProfileImage()
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                navigateGallery()
            } else {
                Toast.makeText(
                    requireContext(),
                    "갤러리 접근 권한이 없습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private val galleryLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                val imageUri = it.data?.data
                Glide.with(this)
                    .load(imageUri)
                    .into(binding.ivGalleryImage)
            } else if (it.resultCode == RESULT_CANCELED) {
                Toast.makeText(requireContext(), "사진 선택이 취소되었습니다", Toast.LENGTH_SHORT).show()
            }
        }

    private fun navigateGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        galleryLauncher.launch(intent)
    }

    private fun changeProfileImage() {
        binding.btnAddImage.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    navigateGallery()
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    }
}