package com.kienct.sodep

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    modifier : Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    Column(
        //modifier to make the column center of the screen
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                      //open ResultScreen
                        viewModel.openResultScreen()
            },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_edgesensor_high_24),
                contentDescription = stringResource(R.string.shake_device),
                //modifier to make the icon 30 degree rotated
                modifier = Modifier.rotate(animateFloatAsState(30f, label = "").value)
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSize))
            Text(text = "Gieo quẻ")
        }
    }
}

//make a preview of HomeScreen
@Composable
@Preview
fun PreviewHomeScreen() {
    HomeScreen()
}
