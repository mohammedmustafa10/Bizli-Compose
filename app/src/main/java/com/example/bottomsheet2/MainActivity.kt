package com.example.bottomsheet2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bottomsheet2.ui.theme.BottomSheet2Theme
import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomSheet2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(modifier = Modifier.background(colorResource(id = R.color.colorPrimary))) {
                        BottomSheet()
                    }

                }
            }
        }
    }
}
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun OnboardingScreen(sheetState:ModalBottomSheetState, scope: CoroutineScope) {
    val onboardingPages = listOf(
        OnboardingData("CALCULATOR","Calculator built for your Business Type",R.drawable.daily_reports),
        OnboardingData("DAILY REPORTS","Get your Sales, Profits, Top Stocks & Cash Reports",R.drawable.daily_reports),
        OnboardingData("SALES COUNTER","Quickly make Bills with Inventory",R.drawable.expense),
        OnboardingData("ADD EXPENSE","Add and Manage all your Expenses in a Single App !",R.drawable.expense)
    )


    val pagerState = rememberPagerState()



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = colorResource(id = R.color.colorPrimary)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            pageCount = onboardingPages.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)

        ) { page ->

            val pageData = onboardingPages[page]
            OnboardingPage(pageTitle = pageData.title,
                pageSubTitle = pageData.subTitle,
                image = pageData.imageResourceId,
                currentPage = page,
                onBoardingPages = onboardingPages
            )
        }
        PagerIndicator(items = onboardingPages, currentPage = pagerState.currentPage)

        Spacer(modifier = Modifier.padding(5.dp))


        Button(
            onClick = {
                scope.launch {
                    if(sheetState.isVisible){
                        sheetState.hide()
                    }
                    else{
                        sheetState.show()

                    }
                }

            }, modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(16.dp)) {
            Text(text = "Sign in to Bizli")
        }


    }
}

    @Composable
    fun OnboardingPage(pageTitle: String,
                       pageSubTitle:String,
                       image:Int,
                       currentPage: Int,
                       onBoardingPages: List<OnboardingData>){
        Card(
            modifier = Modifier
                .fillMaxSize(),
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.colorPrimary)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier =Modifier.padding(10.dp))

                Text(
                    text = pageTitle,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color= colorResource(id= R.color.yellow),
                    modifier = Modifier.padding(16.dp)
                )

                Text(
                    text = pageSubTitle,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color= colorResource(id= R.color.white),
                    modifier = Modifier.padding(5.dp)
                )

                Image(
                    painter = painterResource(id = image),
                    contentDescription = "image"
                )

                Spacer(modifier =Modifier.padding(10.dp))



            }

        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun BottomSheet() {

        //val sheetState= rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
        val modalSheetState= rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val context = LocalContext.current
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        }

        val scope= rememberCoroutineScope()
        val sheetVisibleState=remember{mutableStateOf(false)}
        ChangeBarColors()

        ModalBottomSheetLayout(
            sheetState =modalSheetState,
            sheetBackgroundColor = colorResource(id = R.color.colorPrimary),
            sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            sheetElevation = 40.dp,
            sheetContent = {

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.7f),
                    contentAlignment = Alignment.Center
                )

                {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth()
                        .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally ) {

                        Image(modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                            painter = painterResource(id = R.drawable.bizli_logo),
                            contentDescription ="bizlilogo")

                        Spacer(modifier=Modifier.padding(15.dp))

                        Text(text = "Sign In",
                            fontSize = 35.sp,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineLarge,
                            color= Color.White
                        )


                        Spacer(modifier=Modifier.padding(15.dp))

                        Button(
                            colors= ButtonDefaults.buttonColors(containerColor = Color.White),
                            contentPadding = PaddingValues(15.dp),
                            onClick = {val intent = Intent(context, SignUpScreen::class.java)
                                launcher.launch(intent)},
                            modifier = Modifier
                                .fillMaxWidth(),

                        ) {

                                Image(painter = painterResource(id = R.drawable.googlelogo),
                                    contentDescription = "google icon",
                                    modifier = Modifier
                                        .height(20.dp)
                                        .width(20.dp),
                                    alignment = Alignment.CenterStart,
                                    contentScale = ContentScale.Crop
                                )

                                Text(text = "Sign In With Google",
                                    color= colorResource(id = R.color.colorPrimary),
                                    fontSize = 16.sp,
                                )
                        }
                    }
                }
            }) {
            Column(modifier = Modifier
                .background(
                    colorResource(R.color.colorPrimary)
                )
                .fillMaxSize()){

                OnboardingScreen(sheetState = modalSheetState, scope = scope)

                }
            }
        }


@Composable
    fun PagerIndicator(currentPage: Int, items: List<OnboardingData>) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            repeat(items.size) {
                Indicator(isSelected = it == currentPage)
            }
        }
    }

    @Composable
    fun Indicator(isSelected: Boolean) {
        val width = animateDpAsState(targetValue = if (isSelected) 40.dp else 10.dp)
        val selectedColor= colorResource(id = R.color.yellow)
        val unSelectedColor= Color.White
        val color= if(isSelected) selectedColor else unSelectedColor

        Box(
            modifier = Modifier
                .padding(4.dp)
                .height(10.dp)
                .width(width.value)
                .clip(CircleShape)
                .background(color = color)
        )
    }

@Composable
fun ChangeBarColors() {

    rememberSystemUiController().apply {
        setNavigationBarColor(color = colorResource(id = R.color.colorPrimary), darkIcons = true)
        setStatusBarColor(color = colorResource(id = R.color.colorPrimary), darkIcons = true)
        setSystemBarsColor(color = colorResource(id = R.color.colorPrimary), darkIcons = true)
    }

}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomSheet2Theme {

        Surface {
            BottomSheet()
            
        }

        }
}