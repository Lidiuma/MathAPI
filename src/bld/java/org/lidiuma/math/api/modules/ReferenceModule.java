/*
 * Copyright (c) 2026 Xasmedy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lidiuma.math.api.modules;

import org.lidiuma.math.api.MathApi;
import org.lidiuma.math.api.MathApiModule;
import rife.bld.dependencies.Scope;
import java.util.List;
import static org.lidiuma.math.api.Util.addAttributesToJar;
import static rife.bld.dependencies.Repository.*;

public final class ReferenceModule extends MathApiModule {

    public ReferenceModule() {

        pkg = "org.lidiuma.math.api.reference";
        module = pkg;
        version = version(0,1,0);
        name = "MathReference";
        javaRelease = 17;
        downloadSources = true;
        repositories = List.of(MAVEN_CENTRAL, RIFE2_RELEASES);
        assignModuleDirectories("reference");

        scope(Scope.compile).include(localModule(MathApi.TRAITS.buildDistDirectory().getPath()));

        addAttributesToJar(jarOperation(), version());
        addAttributesToJar(jarSourcesOperation(), version());

        // By keeping the parameters names in the compiled classes,
        // I make it easier by implementors and people reading the API to understand clearly what the variables are.
        compileOperation().compileOptions().parameters();
    }

    @Override
    public void compile() throws Exception {
        MathApi.TRAITS.jar(); // I need the traits as a dependency.
        super.compile();
    }
}
