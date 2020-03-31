import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:scannerbr/scanner_br_service/scanner_br_controller.dart';
import 'package:scannerbr/scanner_br_service/scanner_br_service.dart';
export 'package:scannerbr/scanner_br_service/scanner_br_service.dart';
export 'package:scannerbr/scanner_br_service/scanner_br_controller.dart';

typedef void ScannerViewCreatedCallback(ScannerService controller);

class ScannerViewBr extends StatefulWidget {
  final void Function(String value) onValue;
  const ScannerViewBr({
    Key key,
    this.onScannerViewCreated, this.onValue,
  }) : super(key: key);

  final ScannerViewCreatedCallback onScannerViewCreated;

  @override
  State<StatefulWidget> createState() => _ScannerViewBrState();
}

class _ScannerViewBrState extends State<ScannerViewBr> {
  @override
  Widget build(BuildContext context) {
    if (defaultTargetPlatform == TargetPlatform.android) {
      return AndroidView(
        viewType: 'plugins.flutterplatform/scannerbr',
        onPlatformViewCreated: _onPlatformViewCreated,
      );
    }
    return Text(
        '$defaultTargetPlatform is not yet supported by the text_view plugin');
  }

  void _onPlatformViewCreated(int id) {
    if (widget.onScannerViewCreated == null) {
      return;
    }
    widget.onScannerViewCreated(
      new ScannerController(

        new MethodChannel("plugins.flutterplatform/scannerbr_$id"),
        onValue:widget.onValue,
      ),
    );
    setState(() {

    });
  }
}
