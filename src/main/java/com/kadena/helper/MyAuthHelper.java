/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kadena.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kadena.dto.TokenData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Alvin
 */

public class MyAuthHelper {
    public static TokenData userInfoAuth(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String claims = auth.getDetails().toString();
        Gson gson = new GsonBuilder().create();
        TokenData tokenData = gson.fromJson(claims, TokenData.class);
        return tokenData;
    }
}
