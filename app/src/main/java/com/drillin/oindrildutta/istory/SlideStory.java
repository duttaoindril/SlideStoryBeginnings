package com.drillin.oindrildutta.istory;

import java.io.Serializable;
import java.util.HashMap;

public class SlideStory implements Serializable {
    private HashMap<String, SlideChunk> storymap;
    private SlideChunk[] storyline;

    public SlideStory() {
        storymap = new HashMap<>();
        storyline = new SlideChunk[0];
    }

    public SlideStory(SlideChunk[] chunks) {
        storyline = chunks;
        storymap = new HashMap<>();
        for (SlideChunk chunk : chunks) storymap.put(chunk.getId(), chunk);
    }

    public SlideStory(HashMap<String, SlideChunk> chunks) {
        storymap = chunks;
        storyline = new SlideChunk[chunks.size()];
        int i = 0;
        for (HashMap.Entry<String, SlideChunk> entry : chunks.entrySet())
            storyline[i] = entry.getValue();
    }

    public SlideChunk getChunk(int index) {
        return storyline[index];
    }

    public SlideChunk getChunk(String key) {
        return storymap.get(key);
    }
}