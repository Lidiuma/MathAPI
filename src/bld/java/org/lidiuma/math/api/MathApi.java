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

package org.lidiuma.math.api;

import org.lidiuma.math.api.modules.ApiModule;
import org.lidiuma.math.api.modules.TraitsModule;
import rife.bld.Project;
import java.util.Arrays;

public interface MathApi {

    // Minor code re-use.
    String AVAILABLE = "(Available: \"api\", \"traits\")";
    ApiModule API = new ApiModule();
    TraitsModule TRAITS = new TraitsModule();

    static void main(String... args) {

        if (args.length == 0) {
            System.err.println("Please provide the module in the arguments. " + AVAILABLE);
            return;
        }

        final String module = args[0].toLowerCase();
        final Project project = switch (module) {
            case "api" -> API;
            case "traits" -> TRAITS;
            default -> null;
        };

        if (project == null) {
            System.err.println("Unknown module name \"" + module + "\". " + AVAILABLE);
            return;
        }

        final String[] bldArgs = Arrays.copyOfRange(args, 1, args.length);
        System.out.println("== \"" + module + "\" module selected ==");
        project.start(bldArgs);
    }
}
