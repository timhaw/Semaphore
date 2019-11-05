#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonOutput

class Semaphore {    

    static Map projectContent(String name) {
        def schema = [:]
        schema.name = name
        def tasks = [:]
        tasks.schema = JsonOutput.toJson(schema)
        tasks.url = "http://localhost:3000/api/projects"
            
        return tasks
    }

    static Map keyContent(String name, String project, String key, String secret) {
        def schema = [:]
        schema.name = name
        schema.type = 'ssh'
        schema.project_id = project.toInteger()
        schema.key = key
        schema.secret = secret
        def tasks = [:]
        tasks.schema = JsonOutput.toJson(schema)
        tasks.url = "http://localhost:3000/api/project/${project}/keys"
            
        return tasks
    }

    static Map repositoryContent(String name, String project, String url) {
        def schema = [:]
        schema.name = name
        schema.project_id = project.toInteger()
        schema.git_url = url
        schema.ssh_key_id = 1
        def tasks = [:]
        tasks.schema = JsonOutput.toJson(schema)
        tasks.url = "http://localhost:3000/api/project/${project}/repositories"
            
        return tasks
    }

    static Map inventoryContent(String name, String project, String inventory) {
        def schema = [:]
        schema.name = name
        schema.project_id = project.toInteger()
        schema.inventory = inventory
        schema.ssh_key_id = 1
        schema.type = 'file'
        def tasks = [:]
        tasks.schema = JsonOutput.toJson(schema)
        tasks.url = "http://localhost:3000/api/project/${project}/inventory"
            
        return tasks
    }

    static Map templateContent(String alias, String project, String template, String playbook) {
        def schema = [:]
        schema.ssh_key_id = 1
        schema.project_id = project.toInteger()
        schema.inventory_id = 1
        schema.repository_id = 1
        schema.environment_id = 1
        schema.alias = alias
        schema.playbook = playbook
        schema.arguments = ''
        def tasks = [:]
        tasks.schema = JsonOutput.toJson(schema)
        tasks.url = "http://localhost:3000/api/project/${project}/templates"
            
        return tasks
    }

    static Map taskContent(String project, String template, String playbook) {
        def schema = [:]
        schema.template_id = template.toInteger()
        schema.debug = false
        schema.dry_run = false
        schema.playbook = playbook
        schema.environment = ''
        def tasks = [:]
        tasks.schema = JsonOutput.toJson(schema)
        tasks.url = "http://localhost:3000/api/project/${project}/tasks"
            
        return tasks
    }
    
    static Map buildHeader() {
        def requestParams = [:]
        requestParams.acceptType = 'APPLICATION_JSON'
        requestParams.contentType = 'APPLICATION_JSON'
        requestParams.consoleLogResponseBody = true
        return requestParams
    }

    static Map addCookie(Map requestParams, String cookie) {
        def cookieHeader = [:]
        cookieHeader.name = 'Cookie'
        cookieHeader.value = cookie
        requestParams.customHeaders = [cookieHeader]
        return requestParams
    }

    static Map addContent(Map requestParams, Map content) {
        requestParams.httpMode = 'POST'
        requestParams.requestBody = content.schema
        requestParams.url = content.url
        return requestParams
    }

    static Map requestCookie(String username, String password) {
        def credentials = JsonOutput.toJson([auth: username, password: password])
        def requestParams = buildHeader()
        requestParams.httpMode = 'POST'
        requestParams.requestBody = credentials
        requestParams.url = 'http://localhost:3000/api/auth/login'
        return requestParams
    }

    static String getProjects(String cookie) {
        def requestParams = buildHeader()
        requestParams = addCookie(requestParams, cookie)
        requestParams.httpMode = 'GET'
        requestParams.url = 'http://localhost:3000/api/projects'
        return requestParams
    }

    static String getTemplates(String cookie, String project) {
        def requestParams = buildHeader()
        requestParams = addCookie(requestParams, cookie)
        requestParams.httpMode = 'GET'
        requestParams.url = "http://localhost:3000/api/project/${project}/templates?sort=alias&order=asc"
        return requestParams
    }

    static String sendProject(String name) {
        def content = projectContent(name)
        def requestParams = buildHeader()
        requestParams = addCookie(requestParams, cookie)
        requestParams = addContent(requestParams, content)
        return requestParams
    }

    static String sendKey(String name, String project, String key, String secret) {
        def content = keyContent(name, project, key, secret)
        def requestParams = buildHeader()
        requestParams = addCookie(requestParams, cookie)
        requestParams = addContent(requestParams, content)
        return requestParams
    }

    static String sendRepository(String name, String project, String url) {
        def content = repositoryContent(name, project, url)
        def requestParams = buildHeader()
        requestParams = addCookie(requestParams, cookie)
        requestParams = addContent(requestParams, content)
        return requestParams
    }

    static String sendInventory(String name, String project, String inventory) {
        def content = inventoryContent(name, project, inventory)
        def requestParams = buildHeader()
        requestParams = addCookie(requestParams, cookie)
        requestParams = addContent(requestParams, content)
        return requestParams
    }

    static String sendTemplate(String alias, String project, String template, String playbook) {
        def content = templateContent(alias, project, template, playbook)
        def requestParams = buildHeader()
        requestParams = addCookie(requestParams, cookie)
        requestParams = addContent(requestParams, content)
        return requestParams
    }

//    static String sendTask(String cookie, String project, String template, String playbook) {
//        def content = taskContent(project, template, playbook)
//        def requestParams = buildHeader()
//        requestParams = addCookie(requestParams, cookie)
//        requestParams = addContent(requestParams, content)
//        return requestParams
//    }

    static String sendTask(String cookie, String project, String template, String playbook) {
        def content = taskContent(project, template, playbook)
        def requestParams = buildHeader()
        requestParams = addCookie(requestParams, cookie)
        requestParams.httpMode = 'POST'
        requestParams.requestBody = content.schema
        requestParams.url = content.url
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
