import com.intercress.*

def call(String playbook) {
  
    def common = load "httpRequestCookie"
    //  def wibble = Semaphore.foo
    def wibble = new Semaphore()
  
    // retval = wibble.Semapi(playbook)
    retval = common.httpRequestCookie()
  
    echo "Hello, ${retval}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
