import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
        
    let backDispatcher: BackDispatcher
    
    init(backDispatcher: BackDispatcher) {
        self.backDispatcher = backDispatcher
    }
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(backDispatcher: backDispatcher)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    
    let backDispatcher: BackDispatcher
    
    init(backDispatcher: BackDispatcher) {
        self.backDispatcher = backDispatcher
    }
    
    var body: some View {
        ComposeView(backDispatcher: backDispatcher)
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
                .edgesIgnoringSafeArea(.all)
    }
}



