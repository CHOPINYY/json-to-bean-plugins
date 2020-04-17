package com.github.mgljava.generator;

import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

import com.github.mgljava.entity.ClassEntity;
import com.github.mgljava.utils.FileUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

public class JavaClassGenerator {

  private final Template template;

  public JavaClassGenerator() throws IOException {
    Configuration configuration = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    configuration.setClassForTemplateLoading(this.getClass(), "/");
    template = configuration.getTemplate("java.ftl");

  }

  public void generate(String rootPath, String packageName, ClassEntity classEntity) throws IOException, TemplateException {
    String targetPath = rootPath + packageName.replace(".", "/") + "/"
        + classEntity.getClassName() + ".java";
    FileUtils.mkdirs(targetPath);
    Writer writer = new OutputStreamWriter(new FileOutputStream(targetPath), Charset.defaultCharset());
    template.process(classEntity, writer);
  }
}
