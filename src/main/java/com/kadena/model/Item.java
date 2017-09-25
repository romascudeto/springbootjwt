package com.kadena.model;

import com.kadena.dto.TokenData;
import com.kadena.helper.MyAuthHelper;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@Entity
@FilterDef(name="userIdFilter", parameters={
        @ParamDef( name="userId", type="long" )
})
@Filters( {
        @Filter(name="userIdFilter", condition="user_id_created = :userId")
})
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
  
	private String itemName;
	private String itemCode;
	private Long userIdCreated;

  public Item() {
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  public Long getUserIdCreated() {
    return userIdCreated;
  }

  public void setUserIdCreated(Long userIdCreated) {
    this.userIdCreated = userIdCreated;
  }
  
  @PrePersist
  public void onCreate() {
    TokenData tokenData = MyAuthHelper.userInfoAuth();
    userIdCreated = tokenData.getSub();
  }

  public void setId(int i) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
