package kr.co.hanbit.permission

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.hanbit.permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    /* 1단계: 권한 요청 만들기 */
    lateinit var activityResult:ActivityResultLauncher<String> // 권한을 처리하는 프로퍼티 선언(RequestPermission() 사용하므로 제너릭은 String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        activityResult = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted -> //런처 생성 (isGranted는 받아온 인자-런처)
            if (isGranted) { //카메라권한이 승인(ture)이라면
                startProcess() //카메라 실행
            } else {
                finish() //앱을 종료
            }
        }

        /* 2단계: 사용자에게 승인 요청 */
        binding.btnCamera.setOnClickListener {
            activityResult.launch(Manifest.permission.CAMERA)//permission.CAMERA)
        }
    }
    
    fun startProcess() {
        Toast.makeText(this, "카메라를 실행합니다.", Toast.LENGTH_LONG).show()
    }
}