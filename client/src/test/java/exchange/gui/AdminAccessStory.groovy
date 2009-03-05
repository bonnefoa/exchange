package exchange.gui

import com.google.inject.Guice
import exchange.gui.view.IClientView
import exchange.guiceBinding.ModuleTestGuice
import java.awt.Frame
import org.fest.swing.fixture.FrameFixture

before "", {}

scenario "User try to connect to the administrator part with wrong password", {


  given "Client UI shown", {
    def injector = Guice.createInjector(new ModuleTestGuice())
    def clientView = injector.getInstance(IClientView.class)
    window = new FrameFixture((Frame) clientView);
    window.show();
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

//scenario "User try to connect to the administrator part", {
//  given "Client UI shown", {
//    window.show();
//  }
//
//  when "When_statement", {
//    System.out.println("When");
//  }
//
//  then "Then_statement", {
//    System.out.println("Then");
//  }
//
//  and "the UI shutdowns itself", {
//    window.cleanUp()
//  }
//}
