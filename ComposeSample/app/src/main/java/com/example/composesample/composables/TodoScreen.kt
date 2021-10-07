package com.example.composesample.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.composesample.Todo
import kotlin.reflect.KFunction1


@Composable
fun TodoPage(
    items: List<Todo>,
    currentlyEditing: Todo?,
    onAddItem: (Todo) -> Unit,
    onRemoveItem: (Todo) -> Unit,
    onStartEdit: (Todo) -> Unit,
    onEditItemChange: (Todo) -> Unit,
    onEditDone: () -> Unit
) {
    Column() {
        CreateNewTodo(submit = onAddItem)
        LazyColumn(
            contentPadding = PaddingValues(top = 8.dp),

            ) {
            items(items) { todo ->
                if (currentlyEditing?.id == todo.id)
                    TodoItemInlineEditor(
                        item = currentlyEditing,
                        onEditItemChange = onEditItemChange,
                        onEditDone = onEditDone,
                        onRemoveItem = { onRemoveItem(todo) }
                    )
                else
                    TodoItem(todo = todo, onItemClick = onStartEdit)
            }
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateNewTodo(
    submit: (Todo) -> Unit
) {
    val data = remember {
        mutableStateOf("")

    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .height(IntrinsicSize.Min)
    ) {

        TextField(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            textStyle = MaterialTheme.typography.body1.copy(fontSize = 25.sp),
            value = data.value, onValueChange = { data.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                submit(Todo(data = data.value))
                data.value = ""
                keyboardController?.hide()
            }),
        )
        Button(
            modifier = Modifier.align(Alignment.CenterVertically),
            onClick = {
                submit(Todo(data = data.value))
                data.value = ""
            },
            enabled = data.value.isNotBlank(),
        ) {
            Text(
                text = "Add",
                style = MaterialTheme.typography.body1.copy(fontSize = 25.sp),
            )
        }
    }
}

@Composable
fun TodoItemInlineEditor(
    item: Todo,
    onEditItemChange: (Todo) -> Unit,
    onEditDone: () -> Unit,
    onRemoveItem: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(horizontal = 16.dp)
            .height(IntrinsicSize.Min)

    ) {
        TodoInputText(text = item.data, onTextChange = {
            onEditItemChange(item.copy(data = it))
        })
        TextButton(
            onClick = onEditDone,
            enabled = item.data.isNotBlank(),
//            modifier =Modifier
//                .fillMaxWidth( )
//                .widthIn(20.dp)
            // ,
        ) {
            Text(
                text = "\uD83D\uDCBE",
                textAlign = TextAlign.End,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        TextButton(
            onClick = onRemoveItem,
//            modifier =
//             Modifier.fillMaxWidth( )
//            .widthIn(20.dp)
        ) {
            Text(
                text = "❌",
                textAlign = TextAlign.End,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }

}

@Composable
fun TodoInputText(
    text: String,
    onTextChange: (String) -> Unit,
) {
    TextField(

        value = text,
        onValueChange = onTextChange,
        // modifier = Modifier.fillMaxWidth(0.8f) ,
        textStyle = MaterialTheme.typography.body1.copy(fontSize = 25.sp),
        maxLines =5,
    )

}

@Composable
fun TodoItem(todo: Todo, onItemClick: (Todo) -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onItemClick(todo) }
            .padding(horizontal = 16.dp)
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = todo.data,

            style = MaterialTheme.typography.body1.copy(fontSize = 25.sp),
        )
    }
}