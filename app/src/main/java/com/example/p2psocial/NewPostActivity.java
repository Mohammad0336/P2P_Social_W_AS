package com.example.p2psocial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.p2psocial.main.backend.blockchain.BlockHeader;
import com.example.p2psocial.main.backend.blockchain.Post;

import backend.crypt.KeyGen;

public class NewPostActivity extends AppCompatActivity {

    backend.crypt.KeyGen keyGen = new KeyGen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpost_activity);
    }

    public void insertPost(View view) {
        // Add a new post using PostManager
        Post newPost = new Post("previousHash", "Post Data", new BlockHeader(11, "joe", "public", "Momo" ), keyGen.getPrivatKey());

        // Return to the main activity
        returnHome(view);
    }

    public void returnHome(View view) {
        Intent intent = new Intent(NewPostActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
