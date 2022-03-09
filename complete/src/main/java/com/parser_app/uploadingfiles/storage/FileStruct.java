package com.parser_app.uploadingfiles.storage;

import com.google.gson.Gson;

import java.util.List;

public class FileStruct {
    private String name;
    private String content;
   private String childElements;

    public String getName() {
        return name;
    }

    public String addContent(String content){
       return this.content+=content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChildElements() {
        return childElements;
    }

    public void setChildElements(String childElements) {
        this.childElements = childElements;
    }

    public FileStruct(String name, String content, String childElements) {
        this.name = name;
        this.content = content;
        this.childElements = childElements;
    }
}
