package easyb

import com.google.inject.Guice
import exchange.gui.controller.IClientController
import exchange.gui.view.IClientView
import exchange.guiceBinding.ModuleTestGuice
import java.awt.Frame
import org.fest.swing.fixture.FrameFixture

def setUp()
{
  def injector = Guice.createInjector(new ModuleTestGuice())
  def clientView = injector.getInstance(IClientView.class)
  return window = new FrameFixture((Frame) clientView);
}

scenario "User try to connect to the administrator part with wrong password", {
  given "Client UI shown", {
    window = setUp()
    window.show()
  }
  when "user enter the password 'gwrg'", {
    window.textBox(IClientView.ADMIN_FIED).enterText("gwrg");
  }
  and "and validate", {
    window.button(IClientView.BUTTON_CONNECT_ADMIN).click();
  }
  then "a message of incorrect password is shown", {
    window.optionPane().requireErrorMessage().requireMessage(IClientController.INCORRECT_PASSWORD);
  }
  and "the UI shutdowns itself", {
    window.cleanUp()
  }
}