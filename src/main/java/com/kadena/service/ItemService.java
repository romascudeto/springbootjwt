package com.kadena.service;

import com.kadena.dao.ItemDao;
import com.kadena.dto.TokenData;
import com.kadena.helper.MyAuthHelper;
import com.kadena.model.Item;
import java.util.List;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class ItemService {
  @PersistenceContext
  private EntityManager entityManager;
  
	@Autowired
	private ItemDao itemDao;

	public Item save(Item item) {
		return itemDao.save(item);
	}
  
	public List<Item> find() {
        TokenData tokenData = MyAuthHelper.userInfoAuth();

        Filter filter = (Filter)entityManager.unwrap(Session.class).enableFilter("userIdFilter");
        filter.setParameter("userId", tokenData.getSub());
        List<Item> itemList = itemDao.findAllItem();
        entityManager.unwrap(Session.class).disableFilter("userIdFilter");
		return itemList;
	}
  
	public Item findById(long id) {
    return itemDao.findOne(id);
	}
}
