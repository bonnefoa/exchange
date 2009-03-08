import exchange.guiceBinding.ServerBinding
import exchange.ejb.SONotifierLocal
import exchange.model.StockOption
import javax.jms.*
import javax.naming.*
import com.google.inject.*
import exchange.model.Variation
import exchange.ejb.SONotifierLocal

/**
 * Created by IntelliJ IDEA.
 * User: dev
 * Date: 5 mars 2009
 * Time: 20:34:53
 * To change this template use File | Settings | File Templates.
 */
def setUp() {
  def injector = Guice.createInjector(new ServerBinding())
  return injector.getInstance(SONotifierLocal.class)
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
    def stockOption = new StockOption("company's title", "company's name", 123.456)
    stockOption.setVariation(Variation.DOWN) 
    notifier.update(stockOption);
  }
  then "the stockoption is sent on the topic", {
    message = consumer.receive(1);
    message.shouldBeAn ObjectMessage
  }
  and "the quote is the good one", {
    message.getQuote().shouldBe 123.456
    message.getVaration().shouldBe Variation.DOWN
    message.getTitle().shouldBe "company's title"
    message.getCompany().shouldBe "company's name"

  }
}