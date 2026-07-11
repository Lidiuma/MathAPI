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

import rife.bld.publish.PublishDeveloper;
import rife.bld.publish.PublishLicense;
import static java.lang.String.format;

public final class PublishUtil {

    public static String GITHUB_DOMAIN = "github.com";
    public static String GITHUB_URL = "https://" + GITHUB_DOMAIN;
    /* === LICENSES === */
    public static PublishLicense APACHE_V2_LICENSE = new PublishLicense()
            .name("The Apache License, Version 2.0")
            .url("https://www.apache.org/licenses/LICENSE-2.0.txt");
    /* === DEVELOPERS === */
    public static PublishDeveloper XASMEDY_DEV = makeDeveloperGithub("Xasmedy", "xasmedy@pm.me");

    public static PublishDeveloper makeDeveloperGithub(String developerName, String developerEmail) {
        return new PublishDeveloper()
                .id(developerName.toLowerCase())
                .name(developerName)
                .email(developerEmail)
                .url(format("%s/%s", GITHUB_URL, developerName));
    }

    private PublishUtil() {}
}
