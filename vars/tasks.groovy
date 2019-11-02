import com.intercress.*

def call(String playbook) {
  
  def wibble = ${Semaphore.foo}
  
  echo "Hello, ${Semaphore.foo}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
