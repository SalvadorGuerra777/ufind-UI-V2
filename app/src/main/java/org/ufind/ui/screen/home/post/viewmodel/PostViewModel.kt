package org.ufind.ui.screen.home.post.viewmodel


import android.util.Log
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.ufind.UfindApplication
import org.ufind.data.OptionsRoutes
import org.ufind.data.model.PostModel
import org.ufind.navigation.RouteNavigator
import org.ufind.navigation.UfindNavigator
import org.ufind.network.ApiResponse
import org.ufind.repository.PostRepository

class PostViewModel(
    val repository: PostRepository,
    private val routeNavigator: RouteNavigator = UfindNavigator()
): ViewModel(), RouteNavigator by routeNavigator, DefaultLifecycleObserver {
    private var _posts = MutableStateFlow<List<PostModel>>(listOf())
    val listOfPosts: StateFlow<List<PostModel>> = _posts

//    fun onPostCreated(title: String, description: String ) {
//        _post.add(PostModel(title = title, description = description))
//    }
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        getAll()
    }
    fun getAll() {
        viewModelScope.launch {
            when(val response = repository.getAll()) {
                is ApiResponse.Success -> {
                    Log.d("APP_TAG", response.data.toString())
                    _posts.value = response.data
                }
                is ApiResponse.ErrorWithMessage -> {
                    Log.d("APP_TAG", response.messages.toString())
                }
                is ApiResponse.Error -> {
                    Log.d("APP_TAG", response.exception.toString())
                }
            }
        }
    }
    fun navigateToAddPost() {
        routeNavigator.navigateToRoute(OptionsRoutes.AddPostScreen.route)
    }
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as UfindApplication
                PostViewModel(app.postRepository)
            }
        }
    }
}