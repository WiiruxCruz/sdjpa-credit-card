package guru.springframework.creditcard.listeners;

import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

import guru.springframework.creditcard.services.EncryptionService;

@Component
public class PreUpdateListener extends AbstractEncryptionListener implements PreUpdateEventListener {

	public PreUpdateListener(EncryptionService encryptionService) {
		super(encryptionService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		// TODO Auto-generated method stub
		System.out.println("In Pre Update");
		
		this.encrypt(event.getState(), event.getPersister().getPropertyNames(), event.getEntity());
		
		return false;
	}

}
