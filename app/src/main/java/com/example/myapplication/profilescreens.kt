package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.data.Post
import com.example.myapplication.ui.theme.*

@Composable
fun PostsScreen(
    viewModel: PostsViewModel
) {
    val posts by viewModel.posts.collectAsStateWithLifecycle()

    var showPostDialog by remember { mutableStateOf(false) }
    var selectedPost by remember { mutableStateOf<Post?>(null) }

    PostsScreen(
        posts = posts,

        onAddClick = {
            selectedPost = null
            showPostDialog = true
        },

        onEditClick = { post ->
            selectedPost = post
            showPostDialog = true
        },

        onDeleteClick = { post ->
            viewModel.deletePost(post)
        }
    )

    if (showPostDialog) {
        PostDialog(
            post = selectedPost,

            onDismiss = {
                showPostDialog = false
            },

            onSave = { content ->

                if (selectedPost == null) {
                    viewModel.addPost(content)
                } else {
                    viewModel.editPost(
                        selectedPost!!,
                        content
                    )
                }

                showPostDialog = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(
    posts: List<Post>,
    onAddClick: () -> Unit,
    onEditClick: (Post) -> Unit,
    onDeleteClick: (Post) -> Unit
) {
    Scaffold(
        containerColor = BackgroundWhite,

        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "MySocial",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Text(
                            text = "Share your thoughts",
                            fontSize = 12.sp,
                            color = Color.White.copy(alpha = 0.85f)
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryBlue
                )
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = PrimaryBlue,
                contentColor = Color.White,
                shape = RoundedCornerShape(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Post"
                )
            }
        }
    ) { paddingValues ->

        if (posts.isEmpty()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "No posts yet",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Tap + to create your first post.",
                        fontSize = 14.sp,
                        color = TextGray
                    )
                }
            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),

                contentPadding = PaddingValues(
                    top = 18.dp,
                    bottom = 100.dp
                ),

                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                item {

                    Text(
                        text = "Recent Posts",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBlue
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = "${posts.size} posts",
                        fontSize = 14.sp,
                        color = TextGray
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )
                }

                items(
                    items = posts,
                    key = { it.id }
                ) { post ->

                    PostCard(
                        post = post,

                        onEditClick = {
                            onEditClick(post)
                        },

                        onDeleteClick = {
                            onDeleteClick(post)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PostCard(
    post: Post,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .background(
                            color = LightBlue,
                            shape = CircleShape
                        ),

                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "M",
                        color = PrimaryBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                Column {

                    Text(
                        text = "MySocial User",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )

                    Text(
                        text = "Just now",
                        fontSize = 12.sp,
                        color = TextGray
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = post.content,
                fontSize = 16.sp,
                color = TextDark,
                lineHeight = 24.sp
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            HorizontalDivider(
                color = BorderBlue
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                TextButton(
                    onClick = onEditClick
                ) {

                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = PrimaryBlue,
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(5.dp)
                    )

                    Text(
                        text = "Edit",
                        color = PrimaryBlue
                    )
                }

                TextButton(
                    onClick = onDeleteClick
                ) {

                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = DeleteRed,
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(5.dp)
                    )

                    Text(
                        text = "Delete",
                        color = DeleteRed
                    )
                }
            }
        }
    }
}

@Composable
fun PostDialog(
    post: Post?,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
    var postText by remember(post?.id) {
        mutableStateOf(post?.content ?: "")
    }

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text(
                text = if (post == null) "Create Post" else "Edit Post",
                fontWeight = FontWeight.Bold,
                color = DarkBlue
            )
        },

        text = {

            Column {

                Text(
                    text = if (post == null) {
                        "Share something with everyone!"
                    } else {
                        "Update your post."
                    },
                    color = TextGray,
                    fontSize = 14.sp
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                OutlinedTextField(
                    value = postText,

                    onValueChange = {
                        postText = it
                    },

                    label = {
                        Text("What's on your mind?")
                    },

                    placeholder = {
                        Text("Type your post here...")
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),

                    shape = RoundedCornerShape(14.dp),

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryBlue,
                        focusedLabelColor = PrimaryBlue,
                        cursorColor = PrimaryBlue
                    )
                )
            }
        },

        confirmButton = {

            Button(
                onClick = {
                    if (postText.isNotBlank()) {
                        onSave(postText.trim())
                    }
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue
                )
            ) {

                Text(
                    if (post == null) "Post" else "Save"
                )
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {

                Text(
                    text = "Cancel",
                    color = PrimaryBlue
                )
            }
        }
    )
}

@Composable
fun ProfileScreen(
    postsViewModel: PostsViewModel,
    themeViewModel: ThemeViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Profile Screen",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBlue
        )
    }
}