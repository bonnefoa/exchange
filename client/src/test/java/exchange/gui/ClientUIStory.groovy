package exchange.gui

import exchange.guiceBinding.ModuleTestGuice
import java.awt.Frame
import org.fest.swing.fixture.FrameFixture

def setUp()
{
  def injector = Guice.createInjector(new ModuleTestGuice())
  def clientView = injector.getInstance(IClientView.class)
  return window = new FrameFixture((Frame) clientView);
}

scenario "A stockOption go up", {
  given "The UI is shown", {
    window = setUp()
    window.show()
  }
  and "Subscribe to both stockOptions", {
    window.list(IClientView.STOCK_LIST).selectItems(0, 1);
    window.button(IClientView.BUTTON_SUBSCRIBE).click();
  }

  when "You wait enough for a quote to goes up", {

  }

  then "Then_statement", {
    
  }
}