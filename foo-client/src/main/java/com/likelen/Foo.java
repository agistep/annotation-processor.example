package com.likelen;


import com.likelen.annotation.Getter;

@Getter
public class Foo {

    private String name = "foo";

    public Foo(String name) {
        this.name = name;
        Math.abs(1);
    }

    public String testFunction(){
        return "xx";
    }
}
