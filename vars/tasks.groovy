import com.intercress.*

def call(String playbook) {
  echo "Hello, ${Semaphore.foo}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
