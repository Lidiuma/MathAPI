package org.lidiuma.math.api;

import rife.bld.Project;
import rife.bld.operations.CompileOperation;
import rife.bld.operations.JavadocOperation;
import rife.bld.operations.PublishOperation;
import rife.bld.publish.PublishDeveloper;
import rife.bld.publish.PublishInfo;
import rife.bld.publish.PublishLicense;
import java.util.List;
import static java.lang.String.format;
import static org.lidiuma.math.api.Util.GITHUB_URL;
import static org.lidiuma.math.api.Util.addAttributesToJar;
import static rife.bld.dependencies.Repository.*;
import static rife.bld.dependencies.Scope.compile;

public final class MathApiBuild extends Project {

    public MathApiBuild() {

        module = "lidiuma.math.api";
        pkg = "org.lidiuma.math.api";
        name = "MathAPI";
        version = snapshot(1,0,0);
        javaRelease = 17;
        downloadSources = true;
        repositories = List.of(MAVEN_CENTRAL, RIFE2_RELEASES);

        scope(compile).include(module("org.jspecify", "jspecify", version(1, 0, 0)));

        addAttributesToJar(jarOperation(), version());
        addAttributesToJar(jarSourcesOperation(), version());
    }

    public static void main(String[] args) {
        new MathApiBuild().start(args);
    }

    private void patchPublishJSpecify() {
        // Gradle does not support Maven 4 new types, so I'm forced to patch the type, making it `jar` instead of `modular-jar`.
        scope(compile).clear();
        scope(compile).include(dependency("org.jspecify", "jspecify", version(1, 0, 0)));
    }

    private PublishInfo publishInfo() {

        final var projectInfo = ProjectInfo.github("Lidiuma", name());

        final var license = new PublishLicense()
                .name("The Apache License, Version 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0.txt");

        final String devName = "Xasmedy";
        final var developer = new PublishDeveloper()
                .id(devName.toLowerCase())
                .name(devName)
                .email("xasmedy@pm.me")
                .url(format("%s/%s", GITHUB_URL, devName));

        return new PublishInfo()
                .groupId("org.lidiuma.math")
                .artifactId("math-api")
                .version(version())
                .name("Math API")
                .description("Standard Math API for Libraries and Frameworks")
                .url(projectInfo.url())
                .developer(developer)
                .license(license)
                .scm(projectInfo.scm())
                .signKey(property("sign.key"))
                .signPassphrase(property("sign.passphrase"));
    }

    @Override
    public void publish() throws Exception {
        patchPublishJSpecify();
        super.publish();
    }

    @Override
    public PublishOperation publishOperation() {
        final var op = super.publishOperation();
        op.repositories(CENTRAL_SNAPSHOTS.withCredentials(
                property("sonatype.username"),
                property("sonatype.password")
        )).info(publishInfo());
        return op;
    }

    @Override
    public CompileOperation compileOperation() {
        final var options = super.compileOperation();
        options.compileOptions().parameters();
        return options;
    }

    @Override
    public JavadocOperation javadocOperation() {
        final var options = super.javadocOperation().javadocOptions();
        options.tag("apiNote", "a", "API Note:");
        options.tag("implNote", "a", "Implementation Note:");
        return super.javadocOperation();
    }
}