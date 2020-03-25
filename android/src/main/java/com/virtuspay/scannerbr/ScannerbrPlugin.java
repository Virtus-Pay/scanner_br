package com.virtuspay.scannerbr;

import io.flutter.plugin.common.PluginRegistry.Registrar;


public class ScannerbrPlugin{
    public static void registerWith(Registrar registrar) {

      registrar
              .platformViewRegistry()
              .registerViewFactory(
                      "plugins.flutterplatform/scannerbr", new ScannerBrPlatformViewFactory(registrar));
    }

}
