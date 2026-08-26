package com.example.myapplication

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
fun DishDetailScreen(
    dishId: Int,
    viewModel: DishViewModel,
    onBack: () -> Unit
) {
    val dishes by viewModel.dishes.collectAsStateWithLifecycle()
    val dish = dishes.find { it.id == dishId } ?: return

    var newRecipeText by remember {
        mutableStateOf("")
    }

    var recipeBeingEdited by remember {
        mutableStateOf<Recipe?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = onBack
            ) {
                Text("Back")
            }

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Text(
                text = dish.name,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = newRecipeText,
                onValueChange = {
                    newRecipeText = it
                },
                label = {
                    Text("New recipe step")
                },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Button(
                onClick = {
                    if (newRecipeText.isNotBlank()) {
                        viewModel.addRecipe(dishId, newRecipeText)
                        newRecipeText = ""
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
                items = dish.recipes,
                key = { recipe -> recipe.id }
            ) { recipe ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = recipe.text,
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.bodyLarge
                        )

                        TextButton(
                            onClick = {
                                recipeBeingEdited = recipe
                            }
                        ) {
                            Text("Edit")
                        }

                        TextButton(
                            onClick = {
                                viewModel.deleteRecipe(dishId, recipe.id)
                            }
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }

    val editingRecipe = recipeBeingEdited

    if (editingRecipe != null) {

        EditDialog(
            title = "Edit recipe step",
            initialText = editingRecipe.text,

            onConfirm = { newText ->

                viewModel.updateRecipe(
                    dishId = dishId,
                    recipeId = editingRecipe.id,
                    newText = newText
                )

                recipeBeingEdited = null
            },

            onDismiss = {
                recipeBeingEdited = null
            }
        )
    }
}
