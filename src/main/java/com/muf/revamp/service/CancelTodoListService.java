package com.muf.revamp.service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.muf.revamp.utils.CryptoMngr;

@Service
public class CancelTodoListService {
	
	@Value("${spring.kafka.producer.topic}")
	private String kafkaTopic;
	
	private static final Logger logger = LoggerFactory.getLogger(CancelTodoListService.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public String sendMessage(String message) throws Exception {
		String response = "";
		try {
			logger.info(String.format("#### -> Producing message -> " + message));
			
			//send topic
			kafkaTemplate.send(kafkaTopic, message);
			response = "OK ";
		} catch (Exception e) {
			e.printStackTrace();
			response = "Data Gagal Diproses : " + e.getMessage();
			throw new Exception(e.getMessage());
		}
		return response;
	}
	
}
