#!/usr/bin/env groovy
package com.intercress

class Semaphore {
                  
    static String FindProject(String projects, String name) {
        def String project = new JSONParser().parseProjects(projects, name)
        return project
    }

    static String FindTemplate(String templates, String id, String name) {
        def String template = new JSONParser().parseTemplates(templates, id, name)
        return template
    }
}
