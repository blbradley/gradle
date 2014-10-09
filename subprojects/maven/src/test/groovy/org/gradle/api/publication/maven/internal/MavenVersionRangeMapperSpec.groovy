/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.publication.maven.internal

import spock.lang.Specification
import spock.lang.Unroll

class MavenVersionRangeMapperSpec extends Specification {
    def mapper = new MavenVersionRangeMapper()

    @Unroll
    def "maps '#input' to '#output'"() {
        expect:
        mapper.map(input) == output
        where:
        input    | output
        "1.0"    | "1.0"
        "1+"     | "[1,2)"
        "1.+"    | "[1,2)"
        "1.5.+"  | "[1.5,1.6)"
        "1.5+"   | "[1.5,1.6)"
        "1.100+" | "[1.100,1.101)"
        "10.1+"  | "[10.1,10.2)"

// TODO map LATEST & RELEASE (first support on our pom parsing must be added)
//        "latest.integration"       |       "LATEST"
//        "latest.release"           |       "RELEASE"
    }
}