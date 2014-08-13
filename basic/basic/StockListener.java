import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.JMSException;

public class StockListener implements MessageListener {

	public void onMessage(Message message) {

		try {
			if (message instanceof TextMessage) {
				TextMessage text = (TextMessage) message;
				System.out.println("Received: " + text.getText());
			} else if (message != null) {
				System.out.println("Message not received!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
