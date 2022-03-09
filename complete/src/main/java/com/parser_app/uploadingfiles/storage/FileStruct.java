package com.parser_app.uploadingfiles.storage;


public class FileStruct {
    private String name;
    private String content;

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    private int deep;

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


    public FileStruct(String name, String content,int deep) {
        this.name = name;
        this.content = content;
        this.deep=deep;
    }
}
