package com.drillin.oindrildutta.istory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Objects;

public class StoryActivity extends AppCompatActivity {
    //private static final String TAG = StoryActivity.class.getSimpleName();
    private HashMap<String, SlideChunk> story;
    private String chunkId;
    private ImageView storyImg;
    private TextView storyText;
    private Button choiceA;
    private Button choiceB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImg = (ImageView)findViewById(R.id.storyImg);
        storyText = (TextView)findViewById(R.id.storyText);
        choiceA = (Button)findViewById(R.id.storyChoiceA);
        choiceB = (Button)findViewById(R.id.storyChoiceB);

        chunkId = getIntent().getStringExtra("chunkid");
        story = (HashMap<String, SlideChunk>) getIntent().getSerializableExtra("slidestory");
        loadChunk(chunkId);

        /*
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Hi, "+slideChunk.getCharacters()[0]+"!", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "something");
        */
    }

    public void loadChunk(String id) {
        final SlideChunk slideChunk = story.get(id);
        storyImg.setImageResource(slideChunk.getImgId());
        storyText.setText(String.format(slideChunk.getStoryChunk(), slideChunk.getCharacters()[0]));
        choiceA.setVisibility(View.VISIBLE);
        if(slideChunk.getChunkType() < 2) {
            choiceA.setVisibility(View.INVISIBLE);
            if(slideChunk.getChunkType() == 0) {
                choiceB.setText(R.string.end_choice);
                id = "finish";
            }
            else {
                choiceB.setText(R.string.only_choice);
                id = ""+(Integer.parseInt(id)+1);
            }
        } else {
            choiceA.setText(slideChunk.getChoices()[0]);
            choiceB.setText(slideChunk.getChoices()[1]);
            id = slideChunk.getProgression()[1];
            choiceA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadChunk(slideChunk.getProgression()[0]);
                }
            });
        }
        final String finalId = id;
        choiceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadChunk(finalId);
            }
        });
        if (Objects.equals(id, "finish"))
            choiceB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("chunkID", chunkId);
        outState.putSerializable("story", story);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        chunkId = savedInstanceState.getString("chunkID");
        story = (HashMap<String, SlideChunk>) savedInstanceState.getSerializable("story");
        loadChunk(chunkId);
    }
}