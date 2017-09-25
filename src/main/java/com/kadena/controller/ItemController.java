package com.kadena.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kadena.dto.TokenData;
import com.kadena.model.Item;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kadena.model.User;
import com.kadena.service.ItemService;
import com.kadena.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Item save(@RequestBody Item item) {
		return itemService.save(item);
	}
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Item> find() {
		return itemService.find();
	}
  
  @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
	public Item findById(@PathVariable long itemId) {
		return itemService.findById(itemId);
	}
  
  @RequestMapping(value = "/auth", method = RequestMethod.GET)
	public TokenData aboutMe(HttpServletRequest request) {
    String req = "";
    req = request.getAttribute("claims").toString();
    Gson gson = new GsonBuilder().create();
    TokenData tokenData = gson.fromJson(req, TokenData.class);

    return tokenData;
	}

}
