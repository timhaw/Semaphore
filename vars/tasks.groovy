import com.intercress.*
import groovy.json.JsonOutput

String httpRequestCookie(String username, String password) {
    def credentials = JsonOutput.toJson([auth: username, password: password])
    def cookie = httpRequest \
        acceptType: 'APPLICATION_JSON', \
        consoleLogResponseBody: true, \
        contentType: 'APPLICATION_JSON', \
        httpMode: 'POST', \
        requestBody: credentials, \
        url: 'http://localhost:3000/api/auth/login'
    def cookieContent = cookie.headers.get("Set-Cookie")    
    return cookieContent
}

String httpSendTask(String playbook, String cookie) {
    def schema = JsonOutput.toJson([template_id: 1, debug: false, dry_run: false, playbook: playbook, environment: ''])
    def response = httpRequest \
        acceptType: 'APPLICATION_JSON', \
        consoleLogResponseBody: true, \
        contentType: 'APPLICATION_JSON', \
        customHeaders: [[name: 'Cookie', value: cookie]], \
        httpMode: 'POST', \
        requestBody: schema, \
        url: 'http://localhost:3000/api/project/1/tasks'
    return response
}

def call(String playbook) {
    
    withCredentials([usernamePassword( \
        credentialsId: 'semaphore', \
        usernameVariable: 'username', \
        passwordVariable: 'password')]) {
            cookie = httpRequestCookie(username, password)[0]
            retval = httpSendTask(playbook, cookie)
    }
    
    echo "Hello, ${retval}"
}
