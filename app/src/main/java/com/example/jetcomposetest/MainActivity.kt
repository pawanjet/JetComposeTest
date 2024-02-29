package com.example.jetcomposetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetcomposetest.ui.theme.JetComposeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetComposeTestTheme {
                // A surface container using the 'background' color from the theme
                //portfolioSurface()

                DollarCounter()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun portfolioSurface(){

    var isOpen = remember {
        mutableStateOf(false)
    }

    Surface(shadowElevation = 8.dp,
        color = Color.White,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(12.dp)) {

            Image(painter = painterResource(id = R.drawable.user_profile),
                contentDescription = "User Profile Image" ,
                Modifier.size(60.dp))

            Spacer(modifier = Modifier.height(8.dp))

            Divider()

            Text(text = "Pawan jha",
                style = TextStyle(color = Color.Green, fontSize = 20.sp,
                fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = 0.dp, top = 5.dp, bottom = 0.dp, end = 0.dp))

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Android compose developer", style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(horizontal = 0.dp, vertical = 5.dp))

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {

                Image(painter = painterResource(id = R.drawable.insta_icon), contentDescription = "Insta icon", Modifier.size(20.dp))

                Text(text = "/Insta-Id", style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(8.dp))

            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {

                Image(painter = painterResource(id = R.drawable.github_icon), contentDescription = "github icon", Modifier.size(20.dp))

                Text(text = "/Github-Id", style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(8.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { isOpen.value = !isOpen.value }, modifier = Modifier.padding(horizontal = 0.dp, vertical = 5.dp)) {
                Text(text = "My Project")
            }

            if(isOpen.value) {
                LazyColumn {
                    items(getProjects()) {
                        ProjectItem(it)
                    }
                }
            }
        }


    }

}

@Composable
fun ProjectItem(project: Project){

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)) {

        Image(painter = painterResource(id = R.drawable.user_profile), contentDescription = "project icon",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape))

        Spacer(modifier = Modifier.width(5.dp))

        Column {

            Text(text = project.project, style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold))
            Text(text = project.projectDescription, style = TextStyle(fontSize = 12.sp))
        }
    }
}

fun getProjects() : List<Project>{

    return listOf(
        Project(project = "Social media app", projectDescription = "Connect with your friends"),
        Project(project = "Media player app", projectDescription = "Listen music endlessly"),
        Project(project = "Gaming app", projectDescription = "God of War"),
    )
}

data class Project(val project: String, val projectDescription: String)
