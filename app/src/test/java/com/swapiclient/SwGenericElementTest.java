package com.swapiclient;

import com.swapiclient.model.SwGenericElement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jeremy on 24/11/2016.
 */

public class SwGenericElementTest {

    SwGenericElement element;

    @Before
    public void before (){
        element = new SwGenericElement();
    }


    @Test
    public void testGetType() throws Exception {
        element.setUrl("http://swapi.co/api/films/6/");
        Assert.assertTrue(element.getType().equals("films"));
    }
}
