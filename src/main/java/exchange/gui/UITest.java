package exchange.gui;

import exchange.gui.view.View;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * User: sora
 * Date: 12 févr. 2009
 * Time: 18:33:48
 */
public class UITest
{
    private FrameFixture window;

    @Before
    public void setUp()
    {
        window = new FrameFixture(new View());
        window.show();
    }

    @After
    public void tearDown()
    {
        window.cleanUp();
    }

    @Test
    public void testSomething()
    {
        assertTrue(true);
    }
}