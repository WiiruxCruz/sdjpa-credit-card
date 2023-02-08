package guru.springframework.creditcard.listeners;

import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

@Component
public class PreUpdateListener implements PreUpdateEventListener {

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		// TODO Auto-generated method stub
		System.out.println("In Pre Update");
		return false;
	}

}
