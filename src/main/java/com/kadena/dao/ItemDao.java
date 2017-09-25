package com.kadena.dao;

import com.kadena.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kadena.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ItemDao extends CrudRepository<Item, Long> {
	Item save(Item item);
  
  @Query("select i from Item i")
  public List<Item> findAllItem();

}
