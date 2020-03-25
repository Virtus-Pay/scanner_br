import 'package:flutter/services.dart';
import 'package:scannerbr/scanner_br_service/scanner_br_service.dart';

class ScannerController implements ScannerService {
  final MethodChannel _methodChannel;

  ScannerController(this._methodChannel);

  Future<void> start() => _methodChannel.invokeMethod("start");

  Future<void> stop() => _methodChannel.invokeMethod("stop");
}
