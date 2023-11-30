// PostAdapter.java
package com.example.p2psocial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.p2psocial.main.backend.blockchain.Post;

import java.util.List;

public class PostAdapter extends ArrayAdapter<Post> {

    public PostAdapter(Context context, List<Post> posts) {
        super(context, 0, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.post_item, parent, false);
        }

        // Get the data item for this position
        Post post = getItem(position);

        // Lookup view for data population
        TextView textSender = convertView.findViewById(R.id.textSender);
        TextView textTopic = convertView.findViewById(R.id.textTopic);
        TextView textData = convertView.findViewById(R.id.textData);

        // Populate the data into the template view using the data object
        textSender.setText("Sender: " + post.getRecipeint());
        textTopic.setText("Topic: " + post.readHeader().getMsgType());
        textData.setText("Data: " + post.getData());

        return convertView;
    }
}
