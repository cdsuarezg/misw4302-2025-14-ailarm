package com.project.ailarm.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.ailarm.R
import com.project.ailarm.ui.theme.AppBarTitle
import com.project.ailarm.ui.theme.BackgroundColor
import com.project.ailarm.ui.theme.PrimaryColor
import com.project.ailarm.ui.theme.TitleGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    showAccountBtn: Boolean = false,
    showBackBtn: Boolean = false
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(end = 20.dp, top = 30.dp),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = BackgroundColor,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        ),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ailarm_logo),
                    contentDescription = "Logo Ailarm",
                    modifier = Modifier
                        .width(98.dp)
                        .height(91.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "Ailarm",
                    style = AppBarTitle,
                    color = TitleGray,
                    modifier = Modifier.offset(x = (-26).dp)
                )
            }
        },
        navigationIcon = {
            if (showBackBtn) {
                HoverIconButton(
                    onClick = { },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = PrimaryColor,
                        disabledContentColor = PrimaryColor
                    )
                ) {
                    Icon(
                        Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Regresar",
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp)
                    )
                }
            }
        },
        actions = {
            if (showAccountBtn) {
                HoverIconButton(
                    onClick = { },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = PrimaryColor,
                        disabledContentColor = PrimaryColor
                    )
                ) {
                    Icon(
                        Icons.Filled.AccountCircle,
                        contentDescription = "Perfil",
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                    )
                }
            }
        }
    )

    Spacer(modifier = Modifier.padding(bottom = 16.dp))
}
