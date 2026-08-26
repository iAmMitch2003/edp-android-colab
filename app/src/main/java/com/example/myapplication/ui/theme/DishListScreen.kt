package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DishListScreen(
    viewModel: DishViewModel,
    onDishClick: (Int) -> Unit
) {
    val dishes by viewModel.dishes.collectAsStateWithLifecycle()

    var newDishName by remember {
        mutableStateOf("")
    }

    var dishBeingEdited by remember {
        mutableStateOf<Dish?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "My Dishes",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = newDishName,
                onValueChange = {
                    newDishName = it
                },
                label = {
                    Text("New dish name")
                },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Button(
                onClick = {
                    val name = newDishName.trim()

                    if (name.isNotBlank()) {
                        viewModel.addDish(name)
                        newDishName = ""
                    }
                }
            ) {
                Text("Add")
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(
                items = dishes,
                key = { dish -> dish.id }
            ) { dish ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onDishClick(dish.id)
                        }
                ) {

                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = dish.name,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = "${dish.recipes.size} step(s)",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        TextButton(
                            onClick = {
                                dishBeingEdited = dish
                            }
                        ) {
                            Text("Edit")
                        }

                        TextButton(
                            onClick = {
                                viewModel.deleteDish(dish.id)
                            }
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }

    val editingDish = dishBeingEdited

    if (editingDish != null) {

        EditDialog(
            title = "Rename dish",
            initialText = editingDish.name,

            onConfirm = { newName ->

                viewModel.updateDish(
                    dishId = editingDish.id,
                    newName = newName
                )

                dishBeingEdited = null
            },

            onDismiss = {
                dishBeingEdited = null
            }
        )
    }
}

@Composable
fun EditDialog(
    title: String,
    initialText: String,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var text by remember(initialText) {
        mutableStateOf(initialText)
    }

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text(title)
        },

        text = {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        },

        confirmButton = {
            TextButton(
                onClick = {
                    if (text.trim().isNotBlank()) {
                        onConfirm(text.trim())
                    }
                }
            ) {
                Text("Save")
            }
        },

        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        }
    )
}
