package com.example.composesample.ui.view.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun CustomAppBar(title: String){
    TopAppBar(backgroundColor = Color.White, modifier = Modifier.fillMaxWidth(), title = {
        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    })
}


@Composable
@Preview
fun CustomAppBarPreview(){
    MaterialTheme {
        CustomAppBar("홈")
    }
}