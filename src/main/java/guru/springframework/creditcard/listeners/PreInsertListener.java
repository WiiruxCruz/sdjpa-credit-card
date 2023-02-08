package guru.springframework.creditcard.listeners;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

import guru.springframework.creditcard.services.EncryptionService;

@Component
public class PreInsertListener extends AbstractEncryptionListener implements PreInsertEventListener {

	public PreInsertListener(EncryptionService encryptionService) {
		super(encryptionService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		// TODO Auto-generated method stub
		System.out.println("In Pre Insert");
		
		this.encrypt(event.getState(), event.getPersister().getPropertyNames(), event.getEntity());
		
		return false;
	}

}
