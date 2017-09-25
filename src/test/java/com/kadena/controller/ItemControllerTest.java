/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kadena.controller;

import com.kadena.model.Item;
import com.kadena.service.ItemService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author Alvin
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class, secure = false)
public class ItemControllerTest {
  
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItemService itemService;
  
  @Test
	public void testGetAllItem() throws Exception {
    List<Item> arrItemTest = new ArrayList<Item>();
    Item itemTest = new Item();
    itemTest.setId((long)1);
    itemTest.setItemCode("IT0001");
    itemTest.setItemName("Sepatu");
    itemTest.setUserIdCreated((long)1);
    arrItemTest.add(itemTest);
    
		Mockito.when(itemService.find()).thenReturn(arrItemTest);
    
		RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("http://localhost:8080/item/")
            .accept(MediaType.APPLICATION_JSON);
    
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    
		String expected = "[{\"id\":1,\"itemCode\":\"IT0001\",\"itemName\":\"Sepatu\",\"userIdCreated\":1}]";
    
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
  
	@Test
	public void testAddItem() throws Exception {
		Item itemTest = new Item();
    itemTest.setId((long)1);
    itemTest.setItemCode("IT0001");
    itemTest.setItemName("Sepatu");
    itemTest.setUserIdCreated((long)1);
    
		Mockito.when(itemService.save(Mockito.any(Item.class))).thenReturn(itemTest);

    String postJson = "{\"itemCode1\":\"IT0001\",\"itemName\":\"Sepatu\",\"userCreatedId\":1}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("http://localhost:8080/item/save")
				.accept(MediaType.APPLICATION_JSON).content(postJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
    
		String expected = "{\"id\":1,\"itemName\":\"Sepatu\",\"itemCode\":\"IT0001\",\"userIdCreated\":1}";
    System.out.println(expected+" \n"+result.getResponse().getContentAsString());
		JSONAssert.assertEquals(expected, response.getContentAsString(), false);

	}

}
