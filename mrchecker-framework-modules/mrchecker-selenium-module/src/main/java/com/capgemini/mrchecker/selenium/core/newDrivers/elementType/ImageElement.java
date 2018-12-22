package com.capgemini.mrchecker.selenium.core.newDrivers.elementType;

import org.openqa.selenium.By;

/**
 * Created by TTRZCINSKI on 19.10.2018.
 */
public class ImageElement extends BasicElement {

    public ImageElement(By cssSelector) {
        super(ElementType.IMAGE, cssSelector);
    }
}