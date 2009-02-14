package exchange.launcher;

import com.google.inject.Guice;
import com.google.inject.Injector;
import exchange.guiceBinding.MainModule;

public class Launcher {

    public static void main(java.lang.String[] args) {
        Launcher launcher = new Launcher();
        bootstrap(launcher);
        launcher.run(); 
    }

    private void run() {
        javax.swing.SwingUtilities.invokeLater(new java.lang.Runnable() {
            public void run() {
                new exchange.gui.view.View();
            }
        });
    }

    /**
     * handle the initial configuration of Guice.
     *
     * @param launcher the launcher to inject.
     */
    public static void bootstrap(Launcher launcher) {
        Injector injector = Guice.createInjector(new MainModule());
        injector.injectMembers(launcher);
    }
}