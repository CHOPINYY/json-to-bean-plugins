package com.github.mgljava.listener;

import com.github.mgljava.utils.GenerateUtils;
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
  private final JTextArea jsonInputArea;

  public MyGenerateListener(JTextField rootPathField, JTextField packageNameField,
      JTextField rootClassNameField, JTextField generateResultField, JTextArea jsonInputArea) {
    this.rootPathField = rootPathField;
    this.packageNameField = packageNameField;
    this.rootClassNameField = rootClassNameField;
    this.generateResultField = generateResultField;
    this.jsonInputArea = jsonInputArea;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String word = this.jsonInputArea.getText();
    if (StringUtils.isBlank(word)) {
      this.jsonInputArea.setText("请输入需要转换的Json字符串!");
      return;
    }
    String rootPath = this.rootPathField.getText() + "/";
    String packageName = this.packageNameField.getText();
    String rootClassName = this.rootClassNameField.getText();
    String jsonStr = jsonInputArea.getText();
    GenerateUtils.generate(rootPath, packageName, rootClassName, jsonStr);
    // 处理结果
    this.generateResultField.setText("代码生成成功");
  }
}
