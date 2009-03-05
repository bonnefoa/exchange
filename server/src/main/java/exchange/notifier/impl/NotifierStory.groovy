import exchange.guiceBinding.ServerBinding
import exchange.ejb.SONotifier
import exchange.model.StockOption
import javax.jms.*
import javax.naming.*
import com.google.inject.*

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 5 mars 2009
 * Time: 20:34:53
 * To change this template use File | Settings | File Templates.
 */
def setUp() {
  def injector = Guice.createInjector(new ServerBinding())
  return injector.getInstance(SONotifier.class)
}

def message

scenario "User get a new StockOption everytime it's updated", {
  given "A working server", {
    def notifier = setUp()
  }
  and "a topic which is listened", {

    Properties properties = new Properties();
    properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
    jndiContext = new InitialContext(properties);
    def connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/StockOptionConnectionFactory");
    def dest = (Destination) jndiContext.lookup("jms/StockOptionTopic");

    Connection connection = connectionFactory.createConnection();
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    consumer = session.createConsumer(dest);
    connection.start();
  }
  when "a stockoption is updated", {
    def stockOption = new StockOption()
    stockOption.setQuote(123.456)
    notifier.update(stockOption);
  }
  then "the stockoption is sent on the topic", {
    message = consumer.receive(1);
    message.shouldBeAn ObjectMessage
  }
  and "the quote is the boog one", {
    message.getQuote().shouldBe 123.456
  }
}