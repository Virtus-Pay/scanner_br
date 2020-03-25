import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:scannerbr/scannerbr.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  ScannerService _scannerService;
  bool enabled = false;

  @override
  void initState() {


    SystemChrome.setPreferredOrientations([
      DeviceOrientation.landscapeRight,
      DeviceOrientation.landscapeLeft,
    ]);

    super.initState();
  }

  @override
  void dispose() {

    SystemChrome.setPreferredOrientations([
      DeviceOrientation.landscapeRight,
      DeviceOrientation.landscapeLeft,
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
    ]);


    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        floatingActionButton: FloatingActionButton(onPressed: () async {
          if (enabled) {
            await _scannerService.stop();
            setState(() {
              enabled = !enabled;
            });
          } else {
            await _scannerService.start();
            setState(() {
              enabled = !enabled;
            });
          }
        }),
        body: Center(
          child: Container(
            width: 300,
            height: 300,
            child: ScannerViewBr(
              onScannerViewCreated: (ScannerService scannerService) async {
                _scannerService = scannerService;
                await _scannerService.start();
              },
            ),
          ),
        ),
      ),
    );
  }
}
