import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:scannerbr/scannerbr.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {

  const MyApp({Key key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home:const ScannerTest(),
    );
  }
}


class ScannerTest extends StatefulWidget {
  const ScannerTest({Key key}) : super(key: key);

  @override
  _ScannerTestState createState() => _ScannerTestState();
}

class _ScannerTestState extends State<ScannerTest> {
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
    return Scaffold(
      appBar: AppBar(
        title: const Text('Plugin example app'),
        actions: <Widget>[IconButton(
          onPressed: (){
            setState(() {

            });
          },
          icon: Icon(Icons.face),
        )],
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
      body: Container(
        child: ScannerViewBr(
          onValue: (e){
            print(e);
          },
          onScannerViewCreated: (ScannerService scannerService) async {
            _scannerService = scannerService;
            //   await _scannerService.start();
          },
        ),
      ),
    );
  }
}
