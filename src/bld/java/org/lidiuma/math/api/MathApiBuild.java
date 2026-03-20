package org.lidiuma.math.api;

import rife.bld.Project;
import rife.bld.operations.JavadocOperation;
import rife.bld.operations.PublishOperation;
import rife.bld.publish.PublishDeveloper;
import rife.bld.publish.PublishInfo;
import rife.bld.publish.PublishLicense;
import rife.bld.publish.PublishScm;
import java.util.List;
import static java.lang.String.format;
import static org.lidiuma.math.api.Util.addAttributesToJar;
import static rife.bld.dependencies.Repository.*;
import static rife.bld.dependencies.Scope.compile;

public final class MathApiBuild extends Project {

    public static final String GROUP_ID = "org.lidiuma";

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

    private PublishInfo publishInfo() {

        final String org = "lidiuma";
        final String artifactId = name();
        final String github = "https://github.com";
        final String project = format("%s/%s/%s", github, org, artifactId);

        final var license = new PublishLicense()
                .name("The Apache License, Version 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0.txt");

        final String devName = "Xasmedy";
        final var developer = new PublishDeveloper()
                .id(devName.toLowerCase())
                .name(devName)
                .email("xasmedy@pm.me")
                .url(format("%s/%s", github, devName));

        final var scm = new PublishScm()
                .connection(format("scm:git:%s.git", project))
                .developerConnection(format("scm:git:git@github.com:%s/%s.git", org, artifactId))
                .url(project);

        return new PublishInfo()
                .groupId(GROUP_ID)
                .artifactId(artifactId)
                .version(version())
                .name("Math API")
                .description("Standard Math API for Libraries and Frameworks")
                .url(project)
                .developer(developer)
                .license(license)
                .scm(scm)
                .signKey(property("sign.key"))
                .signPassphrase(property("sign.passphrase"));
    }

    @Override
    public PublishOperation publishOperation() {
        final var op = super.publishOperation();
        op.repositories(CENTRAL_RELEASES.withCredentials(
                property("sonatype.username"),
                property("sonatype.password")
        )).info(publishInfo());
        return op;
    }

    @Override
    public JavadocOperation javadocOperation() {
        final var options = super.javadocOperation().javadocOptions();
        options.tag("apiNote", "a", "API Note:");
        options.tag("implNote", "a", "Implementation Note:");
        return super.javadocOperation();
    }
}