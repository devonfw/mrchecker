package com.devonfw.mrchecker.common.data;

public class SampleObject {

  /** Description for this attribute */
  private String attribute1;

  /** Description for this attribute */
  private String attribute2;

  /**
   * Constructor
   * */
  public SampleObject(String attribute1, String attribute2){
    this.attribute1 = attribute1;
    this.attribute2 = attribute2;
  }

  /**
   * Get attribute value
   * */
  public String getAttribute1() {
    return attribute1;
  }

  /**
   * Set attribute value
   * */
  public void setAttribute1(String attribute1) {
    this.attribute1 = attribute1;
  }

  /**
   * Get attribute value
   * */
  public String getAttribute2() {
    return attribute2;
  }

  /**
   * Set attribute value
   * */
  public void setAttribute2(String attribute2) {
    this.attribute2 = attribute2;
  }
}
