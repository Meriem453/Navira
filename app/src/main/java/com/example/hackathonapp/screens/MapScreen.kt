import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackathonapp.R
import com.example.hackathonapp.items.Quay
import com.example.hackathonapp.model.Quay
import com.example.hackathonapp.model.Ship

@Composable
fun MapScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {


        Image(
            painter = painterResource(id = R.drawable.mapscreenbg),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .matchParentSize()

        )

        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "background",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(50.dp)
                )

                Spacer(Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.notif),
                    contentDescription = "background",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(50.dp)

                )
            }
            Column(
                modifier = Modifier.padding(
                    top = 20.dp,
                    start = 20.dp
                ),

                ) {
                Text(
                    text = "Navire",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontSize = 46.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Spots Map",
                    color = Color.White,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

            }
        }

    }

}


@Preview
@Composable
private fun MapScreenPrev() {
    MapScreen()
}

@Preview
@Composable
private fun portmapprev() {
    InteractivePortMap(sampleShips,sampleQuays)
}


// Sample data (replace with your actual data source)
val sampleShips = listOf(
    Ship(1, "Cargo Ship 1", "Active", 2, 50),
    Ship(2, "Petroleum Tanker 2", "Docked", 1, 75),
    Ship(3, "Passenger Cruise 3", "Waiting", 3, 100)
)

val sampleQuays = listOf(
    Quay("Q1", true, null),
    Quay("Q2", false, "2"), // Quay occupied by ship with ID 2
    Quay("Q3", false, null),
    Quay("Q4", false, null)
)


@Composable
fun InteractivePortMap(ships: List<Ship>, quays: List<Quay>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        items(quays.chunked(2)) { quayPair -> // Chunk quays into pairs
            Column(modifier = Modifier.padding(4.dp)) {
                QuayPair(ships = ships, quays = quayPair) // Display two quays vertically
                Spacer(modifier = Modifier.height(8.dp)) // Add vertical spacing between quay pairs
            }
        }
    }
}

@Composable
fun QuayPair(ships: List<Ship>, quays: List<Quay>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        QuayCard(quay = quays[0], ships = ships) // Display first quay
        Divider( // Add horizontal divider between quays
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Color.Gray
        )
        QuayCard(quay = quays[1], ships = ships) // Display second quay
    }
}

@Composable
fun QuayCard(quay: Quay, ships: List<Ship>, modifier: Modifier = Modifier) {
    val shipName = getShipName(ships, quay.occupiedBy)
    Card(
        modifier = modifier
            .padding(4.dp)
            .width(100.dp)
            .clip(RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            if (quay.available) {
                Quay(quay = quay)
            } else {
                Image( // Display ship image if occupied
                    painter = painterResource(id = R.drawable.ship),
                    contentDescription = "Ship on Quay",
                    modifier = Modifier.size(100.dp)
                )
            }
            Text(text = if (quay.available) "Quay ${quay.id}" else "Occupied")
            if (!quay.available) {
                Text(text = "By: $shipName")
            }
        }
    }
}


// Function to retrieve ship name based on occupiedBy ID (assuming unique IDs)
fun getShipName(ships: List<Ship>, occupiedBy: String?): String {
    return ships.find { it.name == occupiedBy }?.name ?: ""
}