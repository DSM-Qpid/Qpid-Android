package com.example.qpid_android.feature.write

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.qpid_android.R
import com.example.qpid_android.design_system.color.QpidColor
import com.example.qpid_android.design_system.typograpy.PreBold20
import com.example.qpid_android.design_system.typograpy.PreMedium16
import com.example.qpid_android.util.setLightStatusBar

@Composable
fun WriteScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    context.setLightStatusBar()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(QpidColor.Gray000)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            Row {
                Spacer(modifier = Modifier.width(12.dp))
                
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )
            }

            PreBold20(
                text = "글쓰기",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
            )

            PreMedium16(
                text = "등록",
                color = QpidColor.Blue,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.End)
                    .padding(end = 10.dp)
            )
        }
        
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            PreMedium16(text = "제목")
            
            Spacer(modifier = Modifier.height(16.dp))
            PreMedium16(text = "내용")

            Spacer(modifier = Modifier.height(16.dp))
            PreMedium16(text = "태그")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun WritePreview() {
    val navController = rememberNavController()
    WriteScreen(navController = navController)
}