import com.intercress.*

def call(String playbook) {
  echo "Hello, ${playbook}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
