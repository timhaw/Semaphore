#!/usr/bin/env groovy
package com.intercress

class Semaphore {
                  
    static String FindProject(String projects, String name) {
        def String id = new JSONParser().parseProjects(projects, name)
        return id
    }

    static String FindTemplate(String templates, int project, String name) {
        def String id = new JSONParser().parseTemplates(templates, project, name)
        return id
    }
}
