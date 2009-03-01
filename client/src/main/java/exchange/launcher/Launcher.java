/*
 * Copyright (C) 2009 Anthonin Bonnefoy and David Duponchel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package exchange.launcher;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import exchange.gui.view.IGlobalFrame;
import exchange.guiceBinding.MainModule;

public class Launcher {

    private IGlobalFrame globalFrame;

    public static void main(java.lang.String[] args) {
        Launcher launcher = new Launcher();
        bootstrap(launcher);
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

    @Inject
    public void setGlobalFrame(IGlobalFrame globalFrame) {
        this.globalFrame = globalFrame;
    }
}