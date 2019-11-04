#!/usr/bin/env groovy
package com.intercress

class Semaphore {
                  
    static String FindProject(String projects, String name) {
        def String project = new JSONParser().parseJson(projects, name)
        return project
    }

    static String FindTemnplate(String templates, String id) {
        def String project = new JSONParser().parseJson(templates, id)
        return template
    }
}
