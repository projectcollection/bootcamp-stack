package com.snva.springboot.bootcamp.util;

import jep.MainInterpreter;
import jep.SharedInterpreter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PythonUtils {

    public void sayHello(String name) throws IOException {
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("java -jar map.jar time.rel test.txt debug");
    }
}
