#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonOutput

class Semaphore {               

    static String requestCookie(String username, String password) {
        def credentials = JsonOutput.toJson([auth: username, password: password])
        def requestParams = [:]
        requestParams.acceptType = 'APPLICATION_JSON'
        requestParams.consoleLogResponseBody = true
        requestParams.contentType = 'APPLICATION_JSON'
        requestParams.httpMode = 'POST'
        requestParams.requestBody = credentials
        requestParams.url = 'http://localhost:3000/api/auth/login'
        return requestParams
    }

    static String getProjects(String cookie) {
        def cookieHeader = [:]
        cookieHeader.name = 'Cookie'
        cookieHeader.value = cookie
        def requestParams = [:]
        requestParams.acceptType = 'APPLICATION_JSON'
        requestParams.consoleLogResponseBody = true
        requestParams.contentType = 'APPLICATION_JSON'
        requestParams.customHeaders = [cookieHeader]
        requestParams.httpMode = 'GET'
        requestParams.url = 'http://localhost:3000/api/projects'
        return requestParams
    }

    static String getTemplates(String cookie, String project) {
        def cookieHeader = [:]
        cookieHeader.name = 'Cookie'
        cookieHeader.value = cookie
        def requestParams = [:]
        requestParams.acceptType = 'APPLICATION_JSON'
        requestParams.consoleLogResponseBody = true
        requestParams.contentType = 'APPLICATION_JSON'
        requestParams.customHeaders = [cookieHeader]
        requestParams.httpMode = 'GET'
        requestParams.url = "http://localhost:3000/api/project/${project}/templates?sort=alias&order=asc"
        return requestParams
    }

    static String sendTask(String cookie, String project, String template, String playbook) {
        def schema = JsonOutput.toJson([template_id: template.toInteger(), debug: false, dry_run: false, playbook: playbook, environment: ''])
        def cookieHeader = [:]
        cookieHeader.name = 'Cookie'
        cookieHeader.value = cookie
        def requestParams = [:]
        requestParams.acceptType = 'APPLICATION_JSON'
        requestParams.consoleLogResponseBody = true
        requestParams.contentType = 'APPLICATION_JSON'
        requestParams.customHeaders = [cookieHeader]
        requestParams.httpMode = 'POST'
        requestParams.requestBody = schema
        requestParams.url = "http://localhost:3000/api/project/${project}/tasks"
        return requestParams
    }

    static String FindProject(String projects, String name) {
        def String id = new JSONParser().parseProjects(projects, name)
        return id
    }

    static String FindTemplate(String templates, String project, String name) {
        def String id = new JSONParser().parseTemplates(templates, project, name)
        return id
    }
}
