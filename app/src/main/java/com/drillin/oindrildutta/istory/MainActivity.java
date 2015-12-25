package com.drillin.oindrildutta.istory;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private HashMap<String, SlideChunk> story;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        story = new HashMap<>();
        story.put("0", new SlideChunk("0", "", R.drawable.page0, "On your return trip from studying Saturn's rings, you hear a distress signal that seems to be coming from the surface of Mars. It's strange because there hasn't been a colony there in years. Even stranger, it's calling you by name: \"Help me, %1$s, you're my only hope.\"", "Stop and investigate,Continue home to Earth", "1,2"));
        story.put("1", new SlideChunk("1", "", R.drawable.page1, "You deftly land your ship near where the distress signal originated. You didn't notice anything strange on your fly-by, but there is a cave in front of you. Behind you is an abandoned rover from the early 21st century.", "Explore the cave,Explore the rover", "3,4"));
        story.put("2", new SlideChunk("2", "", R.drawable.page2, "You continue your course to Earth. Two days later, you receive a transmission from HQ saying that they have detected some sort of anomaly on the surface of Mars near an abandoned rover. They ask you to investigate, but ultimately the decision is yours because your mission has already run much longer than planned and supplies are low.", "Head back to Mars to investigate,Continue home to Earth", "4,6"));
        story.put("3", new SlideChunk("3", "", R.drawable.page3, "Your EVA suit is equipped with a headlamp, which you use to navigate the cave. After searching for a while your oxygen levels are starting to get pretty low. You know you should go refill your tank, but there's a very faint light up ahead.", "Refill at ship and explore the rover,Continue towards the faint light", "4,5"));
        story.put("4", new SlideChunk("4", "", R.drawable.page4, "The rover is covered in dust and most of the solar panels are broken. But you are quite surprised to see the on-board system booted up and running. In fact, there is a message on the screen: \"%1$s, come to 28.543436, -81.369031.\" Those coordinates aren't far, but you don't know if your oxygen will last there and back.", "Explore the coordinates,Return to Earth", "5,6"));
        story.put("5", new SlideChunk("5", "", R.drawable.page5, "After a long walk slightly uphill, you end up at the top of a small crater. You look around, and are overjoyed to see your favorite android, %1$s-S1124. It had been lost on a previous mission to Mars! You take it back to your ship and fly back to Earth.", "", ""));
        story.put("6", new SlideChunk("6", "", R.drawable.page6, "You arrive home on Earth. While your mission was a success, you forever wonder what was sending that signal. Perhaps a future mission will be able to investigate...", "", ""));

        final EditText nameField = (EditText)findViewById(R.id.editText);
        final Button startButton = (Button)findViewById(R.id.startButton);

        if(getIntent().getStringExtra("name") != null)
            nameField.setText(getIntent().getStringExtra("name"));

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameField.getText().toString();
                if(name.length() > 2) {
                    for (HashMap.Entry<String, SlideChunk> entry : story.entrySet())
                        story.put(entry.getKey(), entry.getValue().setCharacters(name));
                    Intent i = new Intent(getApplicationContext(), StoryActivity.class);
                    i.putExtra("slidestory", story);
                    i.putExtra("chunkid", "0");
                    startActivity(i);
                }
                else if(name.length() == 0)
                    Toast.makeText(getApplicationContext(), "Please enter a name!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Please enter an ACTUAL name!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
