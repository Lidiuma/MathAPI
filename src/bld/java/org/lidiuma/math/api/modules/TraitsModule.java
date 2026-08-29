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

import org.lidiuma.math.api.MathApiModule;
import org.lidiuma.math.api.ProjectInfo;
import rife.bld.publish.PublishInfo;
import java.util.List;
import static org.lidiuma.math.api.MathApi.API;
import static org.lidiuma.math.api.PublishUtil.*;
import static org.lidiuma.math.api.Util.addAttributesToJar;
import static rife.bld.dependencies.Repository.*;
import static rife.bld.dependencies.Scope.compile;

public final class TraitsModule extends MathApiModule {

    public TraitsModule() {

        module = "lidiuma.math.api.traits";
        pkg = "org.lidiuma.math.api.traits";
        name = "MathTraits";
        version = version(0,1,2);
        javaRelease = 17;
        downloadSources = true;
        repositories = List.of(MAVEN_CENTRAL, CENTRAL_SNAPSHOTS, RIFE2_RELEASES);
        assignModuleDirectories("traits");

        scope(compile)
                .include(module("org.jspecify", "jspecify", version(1, 0, 0)))
                .include(module("org.lidiuma.math", "math-api", version(1, 0, 0, "rc3")));

        addAttributesToJar(jarOperation(), version());
        addAttributesToJar(jarSourcesOperation(), version());

        // By keeping the parameters names in the compiled classes,
        // I make it easier by implementors and people reading the API to understand clearly what the variables are.
        compileOperation().compileOptions().parameters();

        // The credentials for publishing.
        publishOperation().repositories(CENTRAL_RELEASES.withCredentials(
                property("sonatype.username"),
                property("sonatype.password")
        )).info(publishInfo());
        // These are not standard tags, so I need to tell the compiler to use them.
        javadocOperation().javadocOptions()
                .tag("apiNote", "a", "API Note:")
                .tag("implNote", "a", "Implementation Note:");
    }

    private PublishInfo publishInfo() {
        final var projectInfo = ProjectInfo.github("Lidiuma", API.name());
        return new PublishInfo()
                .groupId("org.lidiuma.math")
                // I prefer the prefix since the final jar will be math-traits.jar instead of traits.jar.
                .artifactId("math-traits")
                .version(version())
                .name("Math Traits")
                .description("Math Behaviors for Libraries and Frameworks")
                .url(projectInfo.url())
                .developer(XASMEDY_DEV)
                .license(APACHE_V2_LICENSE)
                .scm(projectInfo.scm())
                .signKey(property("sign.key"))
                .signPassphrase(property("sign.passphrase"));
    }

    @Override
    public void publish() throws Exception {
        patchDependencies(this);
        super.publish();
    }
}
