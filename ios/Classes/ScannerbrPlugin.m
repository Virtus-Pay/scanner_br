#import "ScannerbrPlugin.h"
#if __has_include(<scannerbr/scannerbr-Swift.h>)
#import <scannerbr/scannerbr-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "scannerbr-Swift.h"
#endif

@implementation ScannerbrPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftScannerbrPlugin registerWithRegistrar:registrar];
}
@end
