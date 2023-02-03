package guru.springframework.creditcard.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
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
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	void testSaveAndStoreCreditCard() {
		CreditCard creditCard = new CreditCard();
		creditCard.setCreditCardNumber(CREDIT_CARD);
		creditCard.setCvv("123");
		creditCard.setExpirationDate("12/2028");
		
		CreditCard savedCC = ccr.saveAndFlush(creditCard);
		
		System.out.println("Getting CC from database " + savedCC.getCreditCardNumber());
		
		System.out.println("CC At Rest");
		System.out.println("CC Encrypted: "+ es.encrypt(CREDIT_CARD));
		
		Map<String, Object> dbRow = jdbcTemplate.queryForMap("SELECT * FROM credit_card "
				+ "WHERE id = " + savedCC.getId());
		
		String dbCardValue = (String) dbRow.get("credit_card_number");
		
		assertThat(savedCC.getCreditCardNumber()).isNotEqualTo(dbCardValue);
		assertThat(dbCardValue).isEqualTo(es.encrypt(CREDIT_CARD));
		
		CreditCard fetchedCC = ccr.findById(savedCC.getId()).get();
		
		assertThat(savedCC.getCreditCardNumber()).isEqualTo(fetchedCC.getCreditCardNumber());
	}

}
