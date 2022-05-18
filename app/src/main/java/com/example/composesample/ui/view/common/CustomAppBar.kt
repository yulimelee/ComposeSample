package com.example.composesample.ui.view.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.composesample.R


@Composable
fun CustomAppBar(title: String, showBackBtn: Boolean, onClickBackBtn: () -> Unit? = {}) {
    TopAppBar(navigationIcon = {
        if (showBackBtn){
            IconButton(onClick = {
                onClickBackBtn
            }) {
                Icon(painter = painterResource(R.drawable.ic_back), contentDescription = "뒤로가기")
            }
        }else{
            Box(modifier = Modifier)
        }
    }, backgroundColor = Color.White, modifier = Modifier.fillMaxWidth(), title = {
        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    })
}


@Composable
@Preview
fun CustomAppBarPreview() {
    MaterialTheme {
        CustomAppBar("홈", false)
    }
}