package com.joezzy.formvalidation.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CustomOutlinedTextField(
    value:String,
    onValueChanged:(String)-> Unit,
    label: String ="",
    leadingIconImageVector: ImageVector,
    leadingIconDescription: String="",
    isPasswordField: Boolean=false,
    isPasswordVisible: Boolean=false,
    onVisibilityChanged: (Boolean)-> Unit = {},
    keyBoardOptions: KeyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Text),
    keyBoardActions: KeyboardActions = KeyboardActions.Default,
    showError:Boolean=false,
    errorMessage: String=""
){

Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center)
        {
      OutlinedTextField(
          value = value,
          onValueChange = onValueChanged,
           modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
          label={Text(label)},
          leadingIcon = {
              Icon(
                  imageVector = leadingIconImageVector,
                  contentDescription = leadingIconDescription,
                  tint=if (showError) MaterialTheme.colors.error else MaterialTheme.colors.onSurface
              )
          },

          isError = showError,
          trailingIcon = {
              if (showError && !isPasswordField) Icon(imageVector = Icons.Filled.Error , contentDescription = "Show error icon")
              if(isPasswordField){
                    IconButton(onClick = { onVisibilityChanged(!isPasswordVisible) }) {
                    Icon(
                        imageVector = if(isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                    }
                }
          },
          visualTransformation = when{
              isPasswordField && isPasswordVisible->VisualTransformation.None
              isPasswordField -> PasswordVisualTransformation()
              else -> VisualTransformation.None
          },

          keyboardOptions =  keyBoardOptions
//          KeyboardOptions(keyboardType = KeyboardType.Text),
//          keyboardActions =  KeyboardActions.Default,
//          keyBoardOptions = keyBoardOptions,
//          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//                  keyBoardActions = KeyboardActions.Default
//          keyBoardActions = keyBoardActions,
//          singleLine = true

          )

            if(showError){
                Text(
                     text = errorMessage,
                    color=MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.
                            padding(start=8.dp)
                        .offset(y=(-8).dp)
                        .fillMaxWidth(0.9f
                        )
                )
            }


     }


}