package guru.springframework.creditcard.domain;

import guru.springframework.creditcard.config.SpringContextHelper;
import guru.springframework.creditcard.services.EncryptionService;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class CreditCardJPACallback {
	
	@PrePersist
	@PreUpdate
	public void beforeInsertOrUpdate(CreditCard creditCard) {
		System.out.println("before update was called...");
		creditCard.setCreditCardNumber(getEncryptionService().encrypt(creditCard.getCreditCardNumber()));
	}
	
	@PostPersist
	@PostLoad
	@PostUpdate
	public void postLoad(CreditCard creditCard) {
		System.out.println("Post load was called...");
		creditCard.setCreditCardNumber(getEncryptionService().decrypt(creditCard.getCreditCardNumber()));
	}
	
	private EncryptionService getEncryptionService() {
		return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
	}
}
