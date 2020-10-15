import net.kodein.appGlobalStyle
import net.kodein.renderApp
import styled.injectGlobal


fun main() {
    injectGlobal(appGlobalStyle)
    renderApp()
}
