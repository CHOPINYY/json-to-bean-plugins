package com.github.mgljava;

import com.github.mgljava.generator.JavaClassGenerator;
import com.github.mgljava.entity.ClassEntity;
import com.github.mgljava.processor.JsonProcessor;
import java.util.List;

public class Main {

  public static void generate(String rootPath, String packageName, String rootClassName, String jsonStr) {
    JsonProcessor processor = new JsonProcessor(rootClassName, packageName);
    List<ClassEntity> classEntities = processor.parserJsonToClass(jsonStr);
    try {
      JavaClassGenerator generator = new JavaClassGenerator();
      for (ClassEntity model : classEntities) {
        generator.generate(rootPath, packageName, model);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
