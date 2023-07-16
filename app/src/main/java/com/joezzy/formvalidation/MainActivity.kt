package com.joezzy.formvalidation

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joezzy.formvalidation.ui.theme.FormvalidationTheme
import com.joezzy.formvalidation.ui.composables.CustomOutlinedTextField

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormvalidationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    ShowForm()

                }
            }
        }
    }
}



@Composable
fun ShowForm(){
    val context = LocalContext.current
    val focusManager= LocalFocusManager.current
    val scrollState= rememberScrollState()

    var firstname by rememberSaveable{ mutableStateOf(value = "") }
    var surname by rememberSaveable{ mutableStateOf(value = "") }
    var email by rememberSaveable{ mutableStateOf(value = "") }
    var phone by rememberSaveable{ mutableStateOf(value = "") }
    var password by rememberSaveable{ mutableStateOf(value = "") }
    var confirmPassword by rememberSaveable{ mutableStateOf(value = "") }

    var validateName by rememberSaveable { mutableStateOf(value = true) }
    var validateSurname by rememberSaveable { mutableStateOf(value = true) }
    var validateEmail by rememberSaveable { mutableStateOf(value = true) }
    var validatePhone by rememberSaveable { mutableStateOf(value = true) }
    var validatePassword by rememberSaveable { mutableStateOf(value = true) }
    var validateConfirmPassword by rememberSaveable { mutableStateOf(value = true) }
    var validatePasswordsEquals by rememberSaveable { mutableStateOf(value = true) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(value = false) }
    var isConfirmPasswordVisible by rememberSaveable { mutableStateOf(value = false) }


    val validateNameError="Please, enter a valid name"
    val validateSurameError="Please, input a valid surname"
    val validateEmailError="please, enter a valid emaiil"
    val validatePhoneError="please, enter a valid phone"
    val validatePasswordError="please, enter a valid password with(Capital letter, small letter, number and special character, at least 6 character)"
    val validateEqualPasswordError="Password must match"

    fun validateData(
        firstname: String,
        surname: String,
        email:String,
        phone:String,
        password:String,
        confirmPassword:String
    ):Boolean{
        val passwordRegex="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+]).{8,}".toRegex()

        validateName=firstname.isNotBlank();
        validateSurname=surname.isNotBlank();
        validateEmail= Patterns.EMAIL_ADDRESS.matcher(email).matches();
        validatePhone= Patterns.PHONE.matcher(phone).matches();
        validatePassword=passwordRegex.matches(password);
        validateConfirmPassword=passwordRegex.matches(confirmPassword);

        return validateName && validateSurname && validateEmail && validatePhone &&
                validatePassword && validateConfirmPassword

    }

    fun register(
        firstname: String,
        surname: String,
        email:String,
        phone:String,
        password:String,
        confirmPassword:String
    ){
        if(validateData( firstname, surname, email, phone, password, confirmPassword)){
            Toast.makeText(context,"Fields validated", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Please fill fields  correctly ", Toast.LENGTH_SHORT).show()

        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
        .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        Text(
            text = "Register to Wagwan",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(vertical = 20.dp),
            color = Color.Blue
        )

        CustomOutlinedTextField(
            value =firstname,
            onValueChanged = { firstname = it },
            label="First name",
            showError = !validateName,
            errorMessage = validateNameError,
            leadingIconImageVector = Icons.Default.PermIdentity,
            keyBoardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyBoardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down)}
            )
        )

        CustomOutlinedTextField(
            value =surname,
            onValueChanged = { surname = it },
            label="Last name",
            showError = !validateSurname,
            errorMessage = validateSurameError,
            leadingIconImageVector = Icons.Default.PermIdentity,
            keyBoardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyBoardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down)}
            )
        )


        CustomOutlinedTextField(
            value =email,
            onValueChanged = { email = it },
            label="Email",
            showError = !validateEmail,
            errorMessage = validateEmailError,
            leadingIconImageVector = Icons.Default.AlternateEmail,
            keyBoardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyBoardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down)}
            )
        )


        CustomOutlinedTextField(
            value =phone,
            onValueChanged = { phone = it },
            label="Phone",
            showError = !validatePhone,
            errorMessage = validatePhoneError,
            leadingIconImageVector = Icons.Default.Phone,
            keyBoardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyBoardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down)}
            )
        )



        CustomOutlinedTextField(
            value =password,
            onValueChanged = {password = it },
            label="Password",
            showError = !validatePassword,
            errorMessage = validatePasswordError ,
            isPasswordField = true,
            isPasswordVisible = isPasswordVisible,
            onVisibilityChanged = {isPasswordVisible=it},
            leadingIconImageVector = Icons.Default.Password,
            keyBoardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyBoardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down)}
            )
        )


        CustomOutlinedTextField(
            value =confirmPassword,
            onValueChanged = {confirmPassword = it },
            label="Confirm password ",
            showError = !validateConfirmPassword || !validatePasswordsEquals ,
            errorMessage = if(!validateConfirmPassword) validatePasswordError else validateEqualPasswordError  ,
            isPasswordField = true,
            isPasswordVisible = isConfirmPasswordVisible,
            onVisibilityChanged = {isConfirmPasswordVisible = it},
            leadingIconImageVector = Icons.Default.Password,
            keyBoardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyBoardActions = KeyboardActions(
                onDone = { focusManager.clearFocus()}
            )
        )

        Button(
            onClick = {
                register( firstname , surname, email, phone, password, confirmPassword)
            },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            colors= ButtonDefaults.buttonColors(backgroundColor = Color.Blue,
                contentColor = Color.White)
        ) {
            Text(text = "Register", fontSize = 20.sp)
        }


    }





}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FormvalidationTheme {
        ShowForm()
    }
}