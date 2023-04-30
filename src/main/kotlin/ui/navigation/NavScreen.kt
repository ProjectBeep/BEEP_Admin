package ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun NavScreen(onNavClick: (Navigation) -> Unit = {}) {
    Column(
        modifier = Modifier.width(160.dp),
    ) {
        Navigation.values().forEachIndexed { index, navigation ->
            NavItem(navigation, onNavClick)
            if (index != Navigation.values().lastIndex) {
                Divider(modifier = Modifier.fillMaxWidth().width(1.dp))
            }
        }
    }
}

@Composable
fun NavItem(nav: Navigation, onClick: (Navigation) -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(nav) }
            .padding(horizontal = 16.dp, vertical = 6.dp),
    ) {
        Icon(
            painter = painterResource(nav.resourcePath),
            contentDescription = nav.title,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = nav.title)
    }
}
