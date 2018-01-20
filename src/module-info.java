module Pwd.java {
  requires java.base;
  requires java.desktop;
  requires jdk.unsupported;
  requires sequence.radix.converter;
  uses sequence.radix.converter.SequenceRadixConverterFabricInteger;
  uses sequence.radix.converter.SequenceRadixConverterInteger;
  requires java.xml.ws;
  requires java.xml.ws.annotation;
  requires java.se.ee;

  opens configuration;
  exports form1;
  opens form1;
}