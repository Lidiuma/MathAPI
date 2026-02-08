package org.lidiuma.math.api;

import rife.bld.Project;
import java.util.List;
import static rife.bld.dependencies.Repository.*;

public final class MathApiBuild extends Project {

    public MathApiBuild() {

        module = "lidiuma.math.api";
        pkg = "org.lidiuma.math.api";
        name = "MathApi";
        version = version(0,1,0);
        javaRelease = 17;
        downloadSources = true;
        repositories = List.of(MAVEN_CENTRAL, RIFE2_RELEASES);
    }

    public static void main(String[] args) {
        new MathApiBuild().start(args);
    }
}