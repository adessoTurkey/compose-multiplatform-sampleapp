import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init(){
        MoveeApplicationKt.doInitKoin()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
