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

scenario "scenario", {
  given "Given_statement", {
  }

  when "When_statement", {
  }

  then "Then_statement", {
  }
}