package exchange;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Junit class
 */
public abstract class BaseClass {

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(getModule());
        injector.injectMembers(this);
    }

    public abstract Module getModule();

}
