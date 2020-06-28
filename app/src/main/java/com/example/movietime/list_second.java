package com.example.movietime;

public class list_second {
    String name;
    String text;
    String productor;
    String date;
    String language;
    public list_second(String name, String text,String date,String language) {
        this.name = name;
        this.text=text;
        this.date=date;
        this.language=language;
    }

    public String getDate() {
        return date;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
