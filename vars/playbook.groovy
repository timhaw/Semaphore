import com.intercress.*
import groovy.json.JsonOutput

String httpRequestCookie(String username, String password) {
    def credentials = JsonOutput.toJson([auth: username, password: password])
    def requestParams = [:]
    requestParams.acceptType = 'APPLICATION_JSON'
    requestParams.consoleLogResponseBody = true
    requestParams.contentType = 'APPLICATION_JSON'
    requestParams.httpMode = 'POST'
    requestParams.requestBody = credentials
    requestParams.url = 'http://localhost:3000/api/auth/login'
    def cookie = httpRequest requestParams
    def cookieContent = cookie.headers.get("Set-Cookie")    
    return cookieContent
}

String httpSendTask(String playbook, String cookie) {
    def schema = JsonOutput.toJson([template_id: 1, debug: false, dry_run: false, playbook: playbook, environment: ''])
    def requestParams = [:]
    requestParams.acceptType = 'APPLICATION_JSON'
    requestParams.consoleLogResponseBody = true
    requestParams.contentType = 'APPLICATION_JSON'
    requestParams.customHeaders = [[name: 'Cookie', value: cookie]]
    requestParams.httpMode = 'POST'
    requestParams.requestBody = schema
    requestParams.url = 'http://localhost:3000/api/auth/login'
    def response = httpRequest requestParams
    return response
}

def call(String playbook) {
    node {
        def String cookie
        stage ('authenticate') {
            withCredentials([usernamePassword( \
                credentialsId: 'semaphore', \
                usernameVariable: 'username', \
                passwordVariable: 'password')]) {
                    cookie = httpRequestCookie(username, password)[0]
            }
        }
    
        stage ('playbook') {
            retval = httpSendTask(playbook, cookie)
        }
    
        echo "Hello, ${retval}"
    }
}
