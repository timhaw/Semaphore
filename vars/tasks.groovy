import com.intercress.*

def call(String playbook = 'local.yml') {
    new Semaphore().Semapi(playbook)
}
