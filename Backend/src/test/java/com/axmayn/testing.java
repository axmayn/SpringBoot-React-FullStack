package com.axmayn;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testing {

    @Test
    void testClass(){
        System.out.println("inside test class");
    }

    @BeforeEach
    void beforeEachMethod(){
        System.out.println("before each");
    }

    @BeforeAll
    static void beforeAllMethod(){
        System.out.println(a);
        System.out.println("in the bdfore all");
    }

    public void normal(){
        System.out.println("no9tmal 1");
    }


    public void notrmal2(){
        System.out.println("notrma2");
    }


    static int a = 6;




}
