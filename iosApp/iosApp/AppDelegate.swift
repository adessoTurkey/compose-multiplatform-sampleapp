//
//  AppDelegate.swift
//  iosApp
//
//  Created by Kilinc, Furkan on 16.08.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {

        self.window = UIWindow(frame: UIScreen.main.bounds)
        self.window?.rootViewController = UIHostingController(rootView: ContentView())
        self.window?.makeKeyAndVisible()

        Logger.shared.doInit()

        return true
    }
}
