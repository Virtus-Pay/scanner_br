import 'package:flutter/services.dart';
import 'package:scannerbr/scanner_br_service/scanner_br_service.dart';

class ScannerController implements ScannerService {
  final Function(String value) onValue;
  final MethodChannel _methodChannel;

  ScannerController(this._methodChannel,{ this.onValue }){
    _methodChannel.setMethodCallHandler((call)  async {
      if(onValue != null)
        onValue(call.arguments);
    });
  }

  Future<void> start() => _methodChannel.invokeMethod("start");

  Future<void> stop() => _methodChannel.invokeMethod("stop");

}
