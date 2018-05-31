package com.capgemini.mrchecker.selenium.core.enums;

import java.awt.Color;

public enum ColorsEnum {
	
	BLUE_POSITION(15, 87, 194, 1),
	BLACK_TEXT(0, 0, 0, 1),
	WHITE_TEXT(255, 255, 255, 1),
	GREEN_TEXT(0, 102, 0, 1),
	GREEN_MULTACCPOPUP_HEADING(64, 136, 0, 1),
	GREEN_BAR(102, 102, 102, 1),
	GREEN_BAR2(192, 192, 192, -1),
	GREY_HIGHLIGHT(240, 240, 240, 1),
	GREY_SIDEBAR(153, 153, 153, 1),
	RED_TEXT(204, 0, 0, 1),
	RED_BAR(204, 204, 204, 1),
	DARK_BROWN_BACKGROUND(147, 40, 49, 1),
	DARK_YELLOW_BACKGROUND(239, 177, 24, 1),
	DARK_GREEN_BACKGROUND(33, 93, 49, 1),
	YELLOW_COLUMN(255, 255, 0, 1);
	
	private String value;
	private int r;
	private int g;
	private int b;
	private int alpha;
	
	private ColorsEnum(int r, int g, int b, int alpha) {
		this.r = r;
		this.g = g;
		this.b = b;
		if (alpha < 0) {
			this.alpha = alpha;
			this.value = String.format("rgb(%d,%d,%d)", r, g, b);
		} else {
			this.alpha = alpha * 255;
			this.value = String.format("rgba(%d, %d, %d, %d)", r, g, b, alpha);
		}
	}
	
	/**
	 * @return Color value as 'rgba(r, g, b, alpha)' or 'rgb(r, g, b)' string
	 */
	public String toString() {
		return value;
	}
	
	/**
	 * @param css
	 * @return {@code ColorsEnum} constant for given CSS value
	 */
	public static ColorsEnum forCss(String css) {
		for (ColorsEnum constant : ColorsEnum.values()) {
			if (constant.toString()
					.equals(css)) {
				return constant;
			}
		}
		throw new IllegalArgumentException("No enum constant for CSS value: " + css);
	}
	
	/**
	 * @return Red value in color
	 * @author
	 */
	public int getR() {
		return r;
	}
	
	/**
	 * @return Green value in color
	 * @author
	 */
	public int getG() {
		return g;
	}
	
	/**
	 * @return Blue value in color
	 * @author
	 */
	public int getB() {
		return b;
	}
	
	/**
	 * @return Alpha value in color
	 * @author
	 */
	public int getAlpha() {
		return alpha;
	}
	
	/**
	 * @return {@link Color} for specified RGB values
	 * @author
	 */
	public Color getColor() {
		return new Color(r, g, b, alpha);
	}
}
