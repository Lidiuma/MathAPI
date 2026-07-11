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
import rife.bld.Project;
import java.io.File;
import java.util.List;
import static org.lidiuma.math.api.Util.addAttributesToJar;
import static rife.bld.dependencies.Repository.MAVEN_CENTRAL;
import static rife.bld.dependencies.Repository.RIFE2_RELEASES;
import static rife.bld.dependencies.Scope.compile;

public final class TraitsModule extends Project {

    public TraitsModule() {

        module = "lidiuma.math.api.traits";
        pkg = "org.lidiuma.math.api.traits";
        name = "MathTraits";
        version = snapshot(0,1,0);
        javaRelease = 17;
        downloadSources = true;
        repositories = List.of(MAVEN_CENTRAL, RIFE2_RELEASES);
        assignSourcesDirectory();

        final var apiDir = workDirectory()
                .toPath()
                .relativize(MathApi.API.buildDistDirectory().toPath()); // Use absolute when bld 2.3.1 releases.
        scope(compile)
                .include(module("org.jspecify", "jspecify", version(1, 0, 0)))
                .include(localModule(apiDir.toString()));

        addAttributesToJar(jarOperation(), version());
        addAttributesToJar(jarSourcesOperation(), version());
    }

    private void assignSourcesDirectory() {
        final var moduleDir = new File(srcDirectory(), "traits");
        srcMainDirectory = new File(moduleDir, "main");
        srcTestDirectory = new File(moduleDir, "test");
        buildMainDirectory = new File(buildDirectory(), "traits");
        buildDistDirectory = new File(buildDirectory(), "dist/traits");
    }

    @Override
    public void compile() throws Exception {
        MathApi.API.jar(); // I compile the api module jar since a dependency.
        super.compile();
    }
}
