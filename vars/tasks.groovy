import com.intercress.*

def call(String playbook = 'local.yml') {
    def newString = new Semaphore().Semapi(playbook)
    echo "Hello, ${playbook}."
    println newstring
}
