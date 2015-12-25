package com.drillin.oindrildutta.istory;

import java.io.Serializable;

public class SlideChunk implements Serializable {
    private String id;
    private String characters;
    private int imgId; // replace with something more...flexible later.
    private String storyChunk;
    private String choices;
    private String progressionIds;

    public SlideChunk(String pid, String pcharacters, int pimgId, String pstoryChunk, String pchoices, String pprogressionIds) {
        id = pid;
        characters = pcharacters;
        imgId = pimgId;
        storyChunk = pstoryChunk;
        choices = pchoices;
        progressionIds = pprogressionIds;
    }

    public int getChunkType() {
        if(progressionIds.length() == 0)
            return 0;
        else if (choices.length() == 0)
            return 1;
        return 2;
    }

    public String getId() {
        return id;
    }

    public SlideChunk setId(String data) {
        id = data;
        return this;
    }

    public String getRawCharacters() {
        return characters;
    }

    public String[] getCharacters() {
        return characters.split(",");
    }

    public SlideChunk setCharacters(String data) {
        characters = data;
        return this;
    }

    public SlideChunk setCharacters(String[] data) {
        characters = "";
        for (String aData : data) characters += aData + ",";
        return this;
    }

    public int getImgId() {
        return imgId;
    }

    public SlideChunk setImgId(int data) {
        imgId = data;
        return this;
    }

    public String getStoryChunk() {
        return storyChunk;
    }

    public SlideChunk setStoryChunk(String data) {
        storyChunk = data;
        return this;
    }

    public String getRawChoices() {
        return choices;
    }

    public String[] getChoices() {
        return choices.split(",");
    }

    public SlideChunk setChoices(String[] data) {
        choices = "";
        for (String aData : data) choices += aData + ",";
        return this;
    }

    public String getRawProgression() {
        return progressionIds;
    }

    public String[] getProgression() {
        return progressionIds.split(",");
    }

    public SlideChunk setProgression(String[] data) {
        progressionIds = "";
        for (String aData : data) progressionIds += aData + ",";
        return this;
    }
}