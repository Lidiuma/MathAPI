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
import rife.bld.operations.JavadocOperation;
import rife.bld.publish.PublishInfo;
import java.util.List;
import static org.lidiuma.math.api.PublishUtil.*;
import static org.lidiuma.math.api.Util.addAttributesToJar;
import static rife.bld.dependencies.Repository.*;
import static rife.bld.dependencies.Scope.compile;

public final class ApiModule extends MathApiModule {

    public ApiModule() {

        module = "lidiuma.math.api";
        pkg = "org.lidiuma.math.api";
        name = "MathAPI";
        version = version(1,0,0, "rc1");
        javaRelease = 17;
        downloadSources = true;
        repositories = List.of(MAVEN_CENTRAL, RIFE2_RELEASES);
        assignModuleDirectories("api");

        scope(compile).include(module("org.jspecify", "jspecify", version(1, 0, 0)));

        addAttributesToJar(jarOperation(), version());
        addAttributesToJar(jarSourcesOperation(), version());

        publishConfiguration();

        // By keeping the parameters names in the compiled classes,
        // I make it easier by implementors and people reading the API to understand clearly what the variables are.
        compileOperation().compileOptions().parameters();
    }

    private void publishConfiguration() {
        final var op = super.publishOperation();
        op.repositories(CENTRAL_RELEASES.withCredentials(
                property("sonatype.username"),
                property("sonatype.password")
        )).info(publishInfo());
    }

    private PublishInfo publishInfo() {
        final var projectInfo = ProjectInfo.github("Lidiuma", name());
        return new PublishInfo()
                .groupId("org.lidiuma.math")
                .artifactId("math-api")
                .version(version())
                .name("Math API")
                .description("Standard Math API for Libraries and Frameworks")
                .url(projectInfo.url())
                .developer(XASMEDY_DEV)
                .license(APACHE_V2_LICENSE)
                .scm(projectInfo.scm())
                .signKey(property("sign.key"))
                .signPassphrase(property("sign.passphrase"));
    }

    @Override
    public void publish() throws Exception {
        patchPublishJSpecify(this);
        super.publish();
    }

    @Override
    public JavadocOperation javadocOperation() {
        final var options = super.javadocOperation().javadocOptions();
        options.tag("apiNote", "a", "API Note:");
        options.tag("implNote", "a", "Implementation Note:");
        return super.javadocOperation();
    }
}