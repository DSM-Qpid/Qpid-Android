package com.example.qpid_android.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.qpid_android.R
import com.example.qpid_android.design_system.color.QpidColor
import com.example.qpid_android.design_system.typograpy.PreBold12

@Composable
fun QpidBottomNavigation(
    navController: NavController,
    items: List<QpidBottomNavigationItem> = listOf(
        QpidBottomNavigationItem.Search,
        QpidBottomNavigationItem.Board,
        QpidBottomNavigationItem.Mypage,
    )
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = QpidColor.Gray200,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
    ) {
        items.forEach { screen ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == screen.route } == true
            val color = if (selected) QpidColor.Blue else QpidColor.Black
            Column(
                Modifier
                    .height(80.dp)
                    .weight(1f)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(
                            radius = 28.dp
                        ),
                        enabled = !selected
                    ) {
                        navController.navigate(screen.route) { popUpTo(0) }
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = screen.icon(),
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
                PreBold12(text = screen.label, color = color)
            }
        }
    }
}

enum class QpidBottomNavigationItem(
    val route: String,
    val icon: @Composable () -> Painter,
    val label: String,
) {
    Search(
        route = "search",
        icon = { painterResource(id = R.drawable.ic_search) },
        label = "검색",
    ),
    Board(
        route = "board",
        icon = { painterResource(id = R.drawable.ic_board) },
        label = "게시판",
    ),
    Mypage(
        route = "mypage",
        icon = { painterResource(id = R.drawable.ic_mypage) },
        label = "내 정보"
    ),
}