import com.intercress.*
import httpRequestCookie

def call(String playbook) {
  
  //  def wibble = Semaphore.foo
  def wibble = new Semaphore()
  
  // retval = wibble.Semapi(playbook)
  retval = new httpRequestCookie().request()
  
  echo "Hello, ${retval}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
