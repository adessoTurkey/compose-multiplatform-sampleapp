import UIKit
import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
        VStack {
            SwipeGestureViewController {
                onBackGesture()
            }
        }.ignoresSafeArea(.all) // Compose has own keyboard handler
    }
}

class ContainerViewController: UIViewController {
    private let onTouchDown: (CGPoint) -> Void

    init(child: UIViewController, onTouchDown: @escaping (CGPoint) -> Void) {
        self.onTouchDown = onTouchDown
        super.init(nibName: nil, bundle: nil)
        addChild(child)
        child.view.frame = view.frame
        view.addSubview(child.view)
        child.didMove(toParent: self)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        super.touchesBegan(touches, with: event)
        if let startPoint = touches.first?.location(in: nil) {
            onTouchDown(startPoint)
        }
    }
}

struct SwipeGestureViewController: UIViewControllerRepresentable {
    var onSwipe: () -> Void

    func makeUIViewController(context: Context) -> UIViewController {
        let viewController = Main_iosKt.mainViewController()
        let containerController = ContainerViewController(child: viewController) {
            context.coordinator.startPoint = $0
        }

        let swipeGestureRecognizer = UISwipeGestureRecognizer(
            target:
                context.coordinator, action: #selector(Coordinator.handleSwipe)
        )
        swipeGestureRecognizer.direction = .right
        swipeGestureRecognizer.numberOfTouchesRequired = 1
        containerController.view.addGestureRecognizer(swipeGestureRecognizer)

        return containerController
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}

    func makeCoordinator() -> Coordinator {
        Coordinator(onSwipe: onSwipe)
    }

    class Coordinator: NSObject, UIGestureRecognizerDelegate {
        var onSwipe: () -> Void
        var startPoint: CGPoint?

        init(onSwipe: @escaping () -> Void) {
            self.onSwipe = onSwipe
        }

        @objc func handleSwipe(_ gesture: UISwipeGestureRecognizer) {
            if gesture.state == .ended, let startPoint = startPoint, startPoint.x < 50 {
                onSwipe()
            }
        }

        func gestureRecognizer(_ gestureRecognizer: UIGestureRecognizer, shouldRecognizeSimultaneouslyWith otherGestureRecognizer: UIGestureRecognizer) -> Bool {
            true
        }
    }
}
