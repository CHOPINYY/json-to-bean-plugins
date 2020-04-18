package com.github.mgljava.listener;

import com.github.mgljava.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.commons.lang.StringUtils;

public class MyGenerateListener implements ActionListener {

  private final JTextField rootPathField;
  private final JTextField packageNameField;
  private final JTextField rootClassNameField;
  private final JTextField generateResultField;
  private final JTextArea mResultArea;

  public MyGenerateListener(JTextField rootPathField, JTextField packageNameField,
      JTextField rootClassNameField, JTextField generateResultField, JTextArea mResultArea) {
    this.rootPathField = rootPathField;
    this.packageNameField = packageNameField;
    this.rootClassNameField = rootClassNameField;
    this.generateResultField = generateResultField;
    this.mResultArea = mResultArea;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String word = this.rootPathField.getText();
    if (StringUtils.isBlank(word)) {
      this.mResultArea.setText("please insert data");
      this.rootPathField.setText("");
    }
    String rootPath = this.rootPathField.getText();
    String packageName = this.packageNameField.getText();
    String rootClassName = this.rootClassNameField.getText();
    // String jsonStr = mResultArea.getText();
    String jsonStr = "{\"user\":{\"name\":\"zhangsan\",\"age\":10}}";
    Main.generate(rootPath, packageName, rootClassName, jsonStr);
    // 处理结果
    this.generateResultField.setText("代码生成成功");
  }
}
