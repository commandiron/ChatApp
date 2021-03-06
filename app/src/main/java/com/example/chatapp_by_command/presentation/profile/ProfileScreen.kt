package com.example.chatapp_by_command.view

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.chatapp_by_command.core.SnackbarController
import com.example.chatapp_by_command.domain.model.MyUser
import com.example.chatapp_by_command.domain.model.enumclasses.UserStatus
import com.example.chatapp_by_command.presentation.bottomnavigation.BottomNavItem
import com.example.chatapp_by_command.presentation.common_components.ProfileCustomTextField
import com.example.chatapp_by_command.presentation.common_components.LogOutCustomText
import com.example.chatapp_by_command.presentation.common_components.ProfileCustomText
import com.example.chatapp_by_command.presentation.profile.ClickableToGalleryProfilePictureImage
import com.example.chatapp_by_command.presentation.profile.ProfileAppBar
import com.example.chatapp_by_command.presentation.profile.ProfileViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    keyboardController: SoftwareKeyboardController) {

    //Set SnackBar
    val toastMessage = profileViewModel.toastMessage.value
    LaunchedEffect(key1 = toastMessage){
        if(toastMessage != ""){
            SnackbarController(this).showSnackbar(snackbarHostState,toastMessage, "Close")
        }
    }

    //Set Progress Indicator
    var isLoading by remember {
        mutableStateOf(false)
    }
    isLoading = profileViewModel.isLoading.value

    //Set User Data From Firebase
    var userDataFromFirebase by remember {mutableStateOf(MyUser())}
    userDataFromFirebase = profileViewModel.userDataStateFromFirebase.value

    var email by remember { mutableStateOf("") }
    email = userDataFromFirebase.userEmail

    var name by remember { mutableStateOf("") }
    name = userDataFromFirebase.userName

    var surName by remember { mutableStateOf("")}
    surName = userDataFromFirebase.userSurName

    var bio by remember { mutableStateOf("")}
    bio = userDataFromFirebase.userBio

    var phoneNumber by remember { mutableStateOf("")}
    phoneNumber = userDataFromFirebase.userPhoneNumber

    var userDataPictureUrl by remember {mutableStateOf("")}
    userDataPictureUrl = userDataFromFirebase.userProfilePictureUrl

    //SignOut Navigate
    val isUserSignOut = profileViewModel.isUserSignOutState.value
    LaunchedEffect(key1 = isUserSignOut){
        if(isUserSignOut){
            navController.popBackStack()
            navController.navigate(BottomNavItem.SignIn.fullRoute)
        }
    }

    //Compose Components
    Column {
        ProfileAppBar {
            profileViewModel.setUserStatusToFirebaseAndSignOut(UserStatus.OFFLINE)
        }

        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxSize()
                .focusable(true)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { keyboardController.hide() })
                }
                .padding(20.dp)) {

            if(isLoading){
                Box(modifier = Modifier.size(20.dp),contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = MaterialTheme.colors.onSurface)
                }
            }else{
                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){
                    item {

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(text = "Mail: " + email)

                        ClickableToGalleryProfilePictureImage(userDataPictureUrl){
                            if(it != null){
                                profileViewModel.uploadPictureToFirebase(it)
                            }
                        }

                        ProfileCustomTextField(name,"Name",{name = it},{
                            profileViewModel.updateProfileToFirebase(MyUser(userName = name))
                        })

                        Spacer(modifier = Modifier.height(4.dp))

                        ProfileCustomTextField(surName,"Surname",{surName = it},{
                            profileViewModel.updateProfileToFirebase(MyUser(userSurName = surName))
                        })

                        Spacer(modifier = Modifier.height(6.dp))


                        ProfileCustomText(
                            text ="Enter your name and last name.",
                            modifier = Modifier
                                .padding(10.dp, 0.dp, 0.dp, 0.dp)
                                .fillMaxSize()
                                .align(Alignment.Start))

                        Spacer(modifier = Modifier.height(24.dp))

                        ProfileCustomTextField(bio,"Bio",{bio = it},{
                            profileViewModel.updateProfileToFirebase(MyUser(userBio = bio))
                        })

                        Spacer(modifier = Modifier.height(6.dp))

                        ProfileCustomText(
                            text ="Any details about you.",
                            modifier = Modifier
                                .padding(10.dp, 0.dp, 0.dp, 0.dp)
                                .fillMaxSize()
                                .align(Alignment.Start))

                        Spacer(modifier = Modifier.height(24.dp))

                        ProfileCustomTextField(phoneNumber,"Phone Number",{phoneNumber = it},{
                            profileViewModel.updateProfileToFirebase(MyUser(userPhoneNumber = phoneNumber))
                        }, keyboardType = KeyboardType.Phone)

                        Spacer(modifier = Modifier.height(6.dp))

                        ProfileCustomText(
                            text ="Enter your phone number.",
                            modifier = Modifier
                                .padding(10.dp, 0.dp, 0.dp, 0.dp)
                                .fillMaxSize()
                                .align(Alignment.Start))

                        Spacer(modifier = Modifier.height(140.dp))

                        LogOutCustomText{
                            profileViewModel.setUserStatusToFirebaseAndSignOut(UserStatus.OFFLINE)
                        }

                        Spacer(modifier = Modifier.height(360.dp))
                    }
                }
            }
        }
    }
}





