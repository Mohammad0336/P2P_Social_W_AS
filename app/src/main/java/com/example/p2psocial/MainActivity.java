package com.example.p2psocial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.p2psocial.main.backend.blockchain.Post;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the list of posts from PostManager
        ListView listView = findViewById(R.id.listView);
        PostAdapter postAdapter = new PostAdapter(this, PostManager.getPostList());
        listView.setAdapter(postAdapter);
    }

    public void newPost(View view) {
        Intent intent = new Intent(MainActivity.this, NewPostActivity.class);
        startActivity(intent);
    }

    public void enterMessenger(View view) {
        Intent intent = new Intent(MainActivity.this, MessengerActivity.class);
        startActivity(intent);
    }

    public void onConnect(View view) {
        Intent intent = new Intent(MainActivity.this, IpActivity.class);
        startActivity(intent);
    }
}