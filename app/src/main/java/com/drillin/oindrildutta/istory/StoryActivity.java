package com.drillin.oindrildutta.istory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

public class StoryActivity extends AppCompatActivity {
    private static final String TAG = StoryActivity.class.getSimpleName();
    private String nextId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        final HashMap<String, SlideChunk> story = (HashMap<String, SlideChunk>) getIntent().getSerializableExtra("slidestory");
        final SlideChunk slideChunk = story.get(getIntent().getStringExtra("chunkid"));

        final ImageView storyImg = (ImageView)findViewById(R.id.storyImg);
        final TextView storyText = (TextView)findViewById(R.id.storyText);
        final Button choiceA = (Button)findViewById(R.id.storyChoiceA);
        final Button choiceB = (Button)findViewById(R.id.storyChoiceB);

        storyImg.setImageResource(slideChunk.getImgId());
        storyText.setText(String.format(slideChunk.getStoryChunk(), slideChunk.getCharacters()[0]));

        if(slideChunk.getChunkType() < 2) {
            choiceA.setText("");
            if(slideChunk.getChunkType() == 0) {
                choiceB.setText("Try again");
                nextId = "0";
            }
            else {
                choiceB.setText("Continuing...");
                nextId = (""+(Integer.parseInt(getIntent().getStringExtra("chunkid"))+1));
            }
        } else {
            choiceA.setText(slideChunk.getChoices()[0]);
            choiceB.setText(slideChunk.getChoices()[1]);
            nextId = slideChunk.getProgression()[1];
            choiceA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), StoryActivity.class);
                    i.putExtra("slidestory", story);
                    i.putExtra("chunkid", slideChunk.getProgression()[0]);
                    startActivity(i);
                }
            });
        }
        choiceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), StoryActivity.class);
                i.putExtra("slidestory", story);
                i.putExtra("chunkid", nextId);
                startActivity(i);
            }
        });
        if (nextId == "0") {
            choiceB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("name", slideChunk.getCharacters()[0]);
                    startActivity(i);
                }
            });
        }

        /*
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Hi, "+slideChunk.getCharacters()[0]+"!", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "something");
        */
    }

}