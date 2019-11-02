    static def myMethod() {
        httpRequest acceptType: 'APPLICATION_JSON', consoleLogResponseBody: true, contentType: 'APPLICATION_JSON', httpMode: 'POST', url: "http://localhost:3000/api/auth/login"
    }
