package com.muf.revamp.controller;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.muf.revamp.service.CancelTodoListService;

@RestController
public class CancelTodoListController {
	
	@Autowired
	private CancelTodoListService service;

	@PostMapping(value = "/publisher")
	public ResponseEntity<Map<String, Object>> sendMessageToTopic(@RequestBody String message) {
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("data", service.sendMessage(message));
			response.put("status", true);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("data", e.getMessage());
			response.put("status", false);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public String index() throws Exception {
		String result = "";
		try {
			result = "OK -Nens";
		}catch (Exception e) {
			result = "Koneksi Gagal !!<br>" + e.getMessage();
		}
		return result;
	}

}
