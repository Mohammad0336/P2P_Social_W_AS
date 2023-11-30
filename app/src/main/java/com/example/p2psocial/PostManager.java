package com.example.p2psocial;

import com.example.p2psocial.main.backend.blockchain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostManager {
    private static final List<Post> postList = new ArrayList<>();

    private PostManager() {
        // Private constructor to prevent instantiation
    }

    public static List<Post> getPostList() {
        return postList;
    }

    public static void addPost(Post post) {
        postList.add(post);
    }
}

