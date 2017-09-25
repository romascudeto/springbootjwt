/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kadena.dto;

import java.io.Serializable;

/**
 *
 * @author Alvin
 */
public class TokenData implements Serializable{
  private long sub;
  private String username;

  public TokenData(){

  }

  public long getSub() {
    return sub;
  }

  public void setSub(long sub) {
    this.sub = sub;
  }
 
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
    
}
