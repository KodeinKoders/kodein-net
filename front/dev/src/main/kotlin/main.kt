import kotlinx.browser.document
import net.kodein.App
import react.child
import react.dom.render


fun main() {
    render(document.getElementById("app")) {
        child(App) {
            attrs.isStatic = false
        }
    }
}
