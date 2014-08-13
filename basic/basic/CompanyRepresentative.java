import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Destination;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Session;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;


public class CompanyRepresentative {

    /**
     * Main line.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Context context = null;
        ConnectionFactory factory = null;
        Connection connection = null;
        String factoryName = "ConnectionFactory";
        String orderQueue = "OrderQueue";
        String approvalQueue = "ApprovalQueue";
        Destination orderQueueDest = null;
        Destination approvalQueueDest = null;
        int count = 1;
        Session session = null;
        MessageConsumer receiver = null;
        MessageProducer sender = null;

        if (args.length != 0) {
            System.out.println("usage: CompanyRepresentative");
            System.exit(1);
        }

        try {
            // create the JNDI initial context
            context = new InitialContext();

            // look up the ConnectionFactory
            factory = (ConnectionFactory) context.lookup(factoryName);

            // look up the Destination
            orderQueueDest = (Destination) context.lookup(orderQueue);
            approvalQueueDest = (Destination) context.lookup(approvalQueue);

            // create the connection
            connection = factory.createConnection();

            // create the session
            session = connection.createSession(
                false, Session.AUTO_ACKNOWLEDGE);

            // create the receiver
            receiver = session.createConsumer(orderQueueDest);

            //create the sender
            sender = session.createProducer(approvalQueueDest);
            
            // start the connection, to enable message receipt
            connection.start();

            // your code to receive the order quantity from OrderQueue goes here
			Message message = receiver.receive();
            if (message instanceof TextMessage) {
                TextMessage text = (TextMessage) message;
                System.out.println("Order quantity requested: " + text.getText());
            } else if (message != null) {
                System.out.println("Message not received");
            }
            // your code to send the approved/non-approved flag to ApprovalQueue goes here
			TextMessage textMessage = session.createTextMessage();
			if(count <= 20){
				textMessage.setText("Approved!");
				count++;
			}
			else{
				textMessage.setText("Non-Approved!");
			}
            sender.send(textMessage);
            System.out.println("Message: " + textMessage.getText());



        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
