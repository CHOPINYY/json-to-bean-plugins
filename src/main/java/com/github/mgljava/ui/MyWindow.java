package com.github.mgljava.ui;

import com.github.mgljava.Main;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

public class MyWindow implements ToolWindowFactory, ActionListener {

  private JPanel mPanel1;
  private JTextField rootPathField;
  private JTextField packageNameField;
  private JTextField rootClassNameField;
  private JTextArea mResultArea;

  @Override
  public void init(ToolWindow window) {
    this.mPanel1 = new JPanel();
    this.rootPathField = new JTextField("d:/test/", 25);
    this.packageNameField = new JTextField("com.mgl", 25);
    this.rootClassNameField = new JTextField("Test", 25);
    JButton mSubmitButton = new JButton("generate");
    this.mResultArea = new JTextArea(5, 47);
    this.rootPathField.registerKeyboardAction(this, KeyStroke.getKeyStroke(10, 0, true), 0);
    this.packageNameField.registerKeyboardAction(this, KeyStroke.getKeyStroke(10, 0, true), 0);
    this.rootClassNameField.registerKeyboardAction(this, KeyStroke.getKeyStroke(10, 0, true), 0);
    mSubmitButton.addActionListener(this);
    mPanel1.add(new JButton("root path"));
    mPanel1.add(rootPathField);
    mPanel1.add(new JButton("package name"));
    mPanel1.add(packageNameField);
    mPanel1.add(new JButton("root class name"));
    mPanel1.add(rootClassNameField);
    mPanel1.add(mSubmitButton);
    mPanel1.add(mResultArea);
  }

  @Override
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
    Content content = contentFactory.createContent(this.mPanel1, "", false);
    toolWindow.getContentManager().addContent(content);
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
    Main.generate(rootPath, packageName, rootClassName , jsonStr);
    // 处理结果
    this.rootPathField.setText("success");
  }
}
