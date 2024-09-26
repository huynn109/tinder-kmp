import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    var body: some Scene {
        let backDispatcher = BackDispatcherKt.BackDispatcher()
        WindowGroup {
            ContentView(backDispatcher: backDispatcher)
        }
    }
}
