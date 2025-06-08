package com.francisco.compose.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import kotlin.random.Random

@Serializable
data class NoteDetailScreen(val id: Int) : NavKey

@Serializable
data object NoteListScreen : NavKey

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(NoteListScreen)
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator()
        ),
        sceneStrategy = TwoPaneSceneStrategy(),
        entryProvider = { key ->
            when(key) {
                is NoteListScreen -> {
                    NavEntry(
                        key = key,
                        metadata = TwoPaneScene.twoPane()
                    ) {
                        NoteListScreenUi(
                            onNoteClick = { noteId ->
                                backStack.add(NoteDetailScreen(noteId))
                            }
                        )
                    }
                }
                is NoteDetailScreen -> {
                    NavEntry(
                        key = key,
                        metadata = TwoPaneScene.twoPane()
                    ) {
                        NoteDetailScreenUi(
                            viewModel = koinViewModel {
                                parametersOf(key.id)
                            }
                        )
                    }
                }
                else -> throw RuntimeException("Invalid NavKey.")
            }
        },
    )
}

@Composable
fun NoteListScreenUi(
    onNoteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing),
    ) {
        items(sampleNotes) { note ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(note.color)
                    .clickable {
                        onNoteClick(note.id)
                    }
            ) {
                Text(
                    text = note.title,
                    fontSize = 18.sp
                )
            }
        }
    }
}

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val color: Color
)

@Composable
fun NoteDetailScreenUi(
    modifier: Modifier = Modifier,
    viewModel: NoteDetailViewModel = koinViewModel()
) {
    val noteState by viewModel.noteState.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(noteState.color)
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Text(
            text = noteState.title,
            fontSize = 26.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = noteState.content,
            fontSize = 18.sp
        )
    }
}

class NoteDetailViewModel(
    private val noteId: Int
) : ViewModel() {

    private val _noteState = MutableStateFlow(
        sampleNotes.first { it.id == noteId }
    )
    val noteState = _noteState.asStateFlow()
}

val sampleNotes = List(100) {
    Note(
        id = it,
        title = "Note $it",
        content = "Content $it",
        color = Color(Random.nextLong(0xFFFFFFFF)).copy(alpha = 0.5f)
    )
}