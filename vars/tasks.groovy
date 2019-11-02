import com.intercress.*

def call(String playbook) {
  
  def wibble = Semaphore().foo
  
  echo "Hello, ${wibble}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
