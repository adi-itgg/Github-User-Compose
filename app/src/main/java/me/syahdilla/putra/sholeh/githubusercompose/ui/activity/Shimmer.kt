package me.syahdilla.putra.sholeh.githubusercompose.ui.activity

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.syahdilla.putra.sholeh.githubusercompose.ui.theme.ShimmerColorShades

@Composable
fun ShimmerAnimation(
    block: @Composable (brush: Brush) -> Unit
) {
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )
    block(brush = brush)
}

@Composable
fun ShimmerCardUserItem(
    modifier: Modifier = Modifier,
    brush: Brush
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = brush)
                .height(88.dp)
        )

    }
}

@Composable
fun ShimmerUser(
    modifier: Modifier = Modifier,
    brush: Brush
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier.padding(top = 14.dp, bottom = 24.dp),
            shape = CircleShape
        ) {
            Spacer(
                modifier = Modifier
                    .size(160.dp)
                    .background(brush = brush)
            )
        }

        Card(
            modifier = Modifier.padding(top = 12.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Spacer(
                modifier = Modifier
                    .width(180.dp)
                    .height(24.dp)
                    .background(brush = brush)
            )
        }
        Card(
            modifier = Modifier.padding(top = 2.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Spacer(
                modifier = Modifier
                    .width(80.dp)
                    .height(14.dp)
                    .background(brush = brush)
            )
        }

        Card(
            modifier = Modifier.padding(top = 14.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Spacer(
                modifier = Modifier
                    .width(280.dp)
                    .height(18.dp)
                    .background(brush = brush)
            )
        }

        Card(
            modifier = Modifier.padding(top = 8.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Spacer(
                modifier = Modifier
                    .width(160.dp)
                    .height(18.dp)
                    .background(brush = brush)
            )
        }

        Card(
            modifier = Modifier.padding(top = 8.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Spacer(
                modifier = Modifier
                    .width(180.dp)
                    .height(18.dp)
                    .background(brush = brush)
            )
        }

        Card(
            modifier = Modifier.padding(top = 8.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Spacer(
                modifier = Modifier
                    .width(180.dp)
                    .height(18.dp)
                    .background(brush = brush)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ShimmerUserLoadingPreview() {
    ShimmerAnimation {
        ShimmerUser(brush = it)
    }
}

@Preview
@Composable
fun CardUserShimmerPreview() {
    ShimmerAnimation {
        ShimmerCardUserItem(brush = it)
    }
}