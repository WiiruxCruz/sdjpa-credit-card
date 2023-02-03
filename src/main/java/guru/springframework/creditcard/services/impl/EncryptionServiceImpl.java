package guru.springframework.creditcard.services.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.stereotype.Service;

import guru.springframework.creditcard.services.EncryptionService;

@Service
public class EncryptionServiceImpl implements EncryptionService{

	@Override
	public String encrypt(String freeText) {
		// TODO Auto-generated method stub
		return Base64.getEncoder().encodeToString(freeText.getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public String decrypt(String encryptedText) {
		// TODO Auto-generated method stub
		return new String(Base64.getDecoder().decode(encryptedText));
	}

}
