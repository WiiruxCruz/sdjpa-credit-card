package guru.springframework.creditcard.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import guru.springframework.creditcard.domain.CreditCard;
import guru.springframework.creditcard.services.EncryptionService;

@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCartRepositoryTest {
	
	final String CREDIT_CARD = "12345678900000";
	
	@Autowired
	CreditCardRepository ccr;
	
	@Autowired
	EncryptionService es;

	@Test
	void testSaveAndStoreCreditCard() {
		CreditCard creditCard = new CreditCard();
		creditCard.setCreditCardNumber(CREDIT_CARD);
		creditCard.setCvv("123");
		creditCard.setExpirationDate("12/2028");
		
		CreditCard savedCC = ccr.saveAndFlush(creditCard);
		
		System.out.println("Getting CC from database " + creditCard.getCreditCardNumber());
		
		System.out.println("CC At Rest");
		System.out.println("CC Encrypted: "+ es.encrypt(CREDIT_CARD));
		
		CreditCard fetchedCC = ccr.findById(savedCC.getId()).get();
		
		assertThat(savedCC.getCreditCardNumber()).isEqualTo(fetchedCC.getCreditCardNumber());
	}

}
