package guru.springframework.creditcard.listeners;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

@Component
public class PreInsertListener implements PreInsertEventListener {

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		// TODO Auto-generated method stub
		System.out.println("In Pre Insert");
		return false;
	}

}
