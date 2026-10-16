package com.example.perte5tugas

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                BiodataScreen(
                    onSubmit = {
                        Toast.makeText(
                            this,
                            getString(R.string.success_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }
    }
}

@Composable
fun BiodataScreen(onSubmit: () -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    var androidSkill by remember { mutableStateOf(false) }
    var uiuxSkill by remember { mutableStateOf(false) }
    var webSkill by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val purple = colorResource(R.color.purple_primary)
    val formColor = colorResource(R.color.form_background)
    val textColor = colorResource(R.color.text_primary)

    val beginner = stringResource(R.string.experience_beginner)
    val intermediate = stringResource(R.string.experience_intermediate)
    val advanced = stringResource(R.string.experience_advanced)

    val formValid =
        firstName.isNotBlank() &&
                lastName.isNotBlank() &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                birthDate.isNotBlank() &&
                experience.isNotBlank() &&
                (androidSkill || uiuxSkill || webSkill)

    Scaffold(
        containerColor = colorResource(R.color.white)
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Image(
                painter = painterResource(R.drawable.bg_neon_spider),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 180.dp,
                        bottom = 24.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    color = formColor,
                    shadowElevation = 10.dp
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text(
                            text = stringResource(R.string.form_title),
                            color = purple,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(
                                Alignment.CenterHorizontally
                            )
                        )

                        Text(
                            text = stringResource(R.string.form_subtitle),
                            color = colorResource(R.color.text_secondary),
                            fontSize = 11.sp,
                            modifier = Modifier.align(
                                Alignment.CenterHorizontally
                            )
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            OutlinedTextField(
                                value = firstName,
                                onValueChange = { firstName = it },
                                modifier = Modifier.weight(1f),
                                label = {
                                    Text(stringResource(R.string.first_name))
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(12.dp)
                            )

                            OutlinedTextField(
                                value = lastName,
                                onValueChange = { lastName = it },
                                modifier = Modifier.weight(1f),
                                label = {
                                    Text(stringResource(R.string.last_name))
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(12.dp)
                            )
                        }

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(stringResource(R.string.email))
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email
                            ),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp)
                        )

                        OutlinedTextField(
                            value = birthDate,
                            onValueChange = { birthDate = it },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(stringResource(R.string.birth_date))
                            },
                            placeholder = {
                                Text("DD/MM/YYYY")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp)
                        )

                        Box {
                            OutlinedButton(
                                onClick = { expanded = true },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = if (experience.isBlank()) {
                                        stringResource(R.string.experience)
                                    } else {
                                        experience
                                    },
                                    modifier = Modifier.weight(1f),
                                    color = textColor
                                )

                                Text(
                                    text = "⌄",
                                    color = purple
                                )
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = {
                                    expanded = false
                                }
                            ) {
                                DropdownMenuItem(
                                    text = { Text(beginner) },
                                    onClick = {
                                        experience = beginner
                                        expanded = false
                                    }
                                )

                                DropdownMenuItem(
                                    text = { Text(intermediate) },
                                    onClick = {
                                        experience = intermediate
                                        expanded = false
                                    }
                                )

                                DropdownMenuItem(
                                    text = { Text(advanced) },
                                    onClick = {
                                        experience = advanced
                                        expanded = false
                                    }
                                )
                            }
                        }

                        Text(
                            text = stringResource(R.string.skill_title),
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SkillCheck(
                                label = stringResource(R.string.skill_android),
                                checked = androidSkill,
                                onCheckedChange = { androidSkill = it }
                            )

                            SkillCheck(
                                label = stringResource(R.string.skill_uiux),
                                checked = uiuxSkill,
                                onCheckedChange = { uiuxSkill = it }
                            )

                            SkillCheck(
                                label = stringResource(R.string.skill_web),
                                checked = webSkill,
                                onCheckedChange = { webSkill = it }
                            )
                        }

                        Button(
                            onClick = onSubmit,
                            enabled = formValid,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = purple,
                                disabledContainerColor = colorResource(
                                    R.color.disabled_background
                                )
                            )
                        ) {
                            Text(stringResource(R.string.proceed))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SkillCheck(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )

        Text(
            text = label,
            fontSize = 11.sp
        )
    }
}
