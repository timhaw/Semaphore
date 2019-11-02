import com.intercress.*

def call(String playbook) {
  
//  def wibble = Semaphore.foo
  def wibble = new Semaphore()
  
  echo "Hello, ${wibble.foo}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
