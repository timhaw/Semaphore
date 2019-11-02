import com.intercress.*

def call(String playbook) {
  echo "Hello, ${playbook}"
//  echo "Hello, ${GlobalVars.foo}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
