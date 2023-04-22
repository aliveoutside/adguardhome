package ru.toxyxd.common.feature.addserver

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf
import ru.toxyxd.common.extension.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddServerScreen(
    onGoBack: () -> Unit
) {
    val viewModel = getViewModel<AddServerViewModel> { parametersOf() }
    val coroutineScope = rememberCoroutineScope()

    var name by remember { mutableStateOf(TextFieldValue("")) }
    var domain by remember { mutableStateOf(TextFieldValue("")) }
    var port by remember { mutableStateOf(TextFieldValue("")) }
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var showPassword by remember { mutableStateOf(true) }
    val showAddButton by remember { mutableStateOf(true) }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Add server") }, navigationIcon = {
            IconButton(onClick = onGoBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack, "Go to home"
                )
            }
        })
    }) { paddings ->
        Box {
            Column(
                modifier = Modifier.padding(paddings).fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                TextField(
                    value = domain,
                    onValueChange = { domain = it },
                    label = { Text("Domain (IP)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri)
                )
                TextField(
                    value = port,
                    onValueChange = { port = it },
                    label = { Text("Port") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = if (showPassword) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(
                                imageVector = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (showPassword) "Hide password" else "Show password"
                            )
                        }
                    }
                )
            }
            AnimatedVisibility(
                visible = showAddButton,
                modifier = Modifier.align(Alignment.BottomEnd),
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                AddServerButton {
                    coroutineScope.launch {
                        viewModel.addServer(
                            name.text,
                            domain.text,
                            port.text.toInt(),
                            username.text,
                            password.text
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AddServerButton(
    onAddServer: () -> Unit
) {
    FloatingActionButton(
        onClick = onAddServer,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add, contentDescription = "Add a server"
        )
    }
}