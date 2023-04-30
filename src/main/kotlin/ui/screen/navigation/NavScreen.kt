package ui.screen.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
    LazyColumn(
        modifier = Modifier.width(160.dp),
    ) {
        itemsIndexed(Navigation.values()) { index, nav ->
            NavItem(nav, onNavClick)
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
