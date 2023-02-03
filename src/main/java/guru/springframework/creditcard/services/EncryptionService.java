package guru.springframework.creditcard.services;

public interface EncryptionService {
	public String encrypt(String freeText);
	
	public String decrypt(String encryptedText);
}
