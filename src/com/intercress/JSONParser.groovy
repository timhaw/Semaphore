#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonSlurper

class JSONParser {
    static String jsonSlurper = new JsonSlurper()
    return jsonSlurper().parseText(String text)
}
