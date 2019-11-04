#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonSlurper

class JSONParser {
    static parseJson() {
        def slurper = new JsonSlurper()
        return slurper.parseText('{"person":{"name":"Guillaume","age":33,"pets":["dog","cat"]}}')
    }
}
