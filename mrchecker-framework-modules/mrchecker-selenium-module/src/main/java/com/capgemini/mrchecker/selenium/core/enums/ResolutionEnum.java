package com.capgemini.mrchecker.selenium.core.enums;

public enum ResolutionEnum implements IResolutionList {
	w320(320, 240),
	w480(480, 320),
	w568(568, 320),
	w768(768, 576),
	w960(960, 720),
	w1024(1024, 768),
	w1140(1140, 760),
	w1280(1280, 1024),
	w1366(1366, 768),
	w1600(1600, 1200),
	w1800(1800, 1440),
	w1920(1920, 1080);
	
	private int width;
	private int height;
	
	private ResolutionEnum(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public String toString() {
		return "Widht:" + getWidth() + " Height:" + getHeight();
		
	}
}
