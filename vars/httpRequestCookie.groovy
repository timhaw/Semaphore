import groovy.json.JsonOutput

def String httpRequestCookie() {
    def credentials = JsonOutput.toJson([auth: 'admin', password: 'admin'])
    def cookie = httpRequest acceptType: 'APPLICATION_JSON', consoleLogResponseBody: true, contentType: 'APPLICATION_JSON', httpMode: 'POST', requestBody: credentials, url: "http://localhost:3000/api/auth/login"
    def cookieContent = cookie.headers.get("Set-Cookie")    
    return cookieContent
}
