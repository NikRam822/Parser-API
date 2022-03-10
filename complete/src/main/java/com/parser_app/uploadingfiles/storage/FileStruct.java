package com.parser_app.uploadingfiles.storage;


public class FileStruct {
    private String name;
    private String content;
    private int deep;

    public FileStruct(String name, String content, int deep) {
        this.name = name;
        this.content = content;
        this.deep = deep;
    }
}
