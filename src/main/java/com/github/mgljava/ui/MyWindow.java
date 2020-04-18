package com.github.mgljava.ui;

import com.github.mgljava.listener.FileListener;
import com.github.mgljava.listener.MyGenerateListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import java.awt.FlowLayout;
import java.awt.Label;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.jetbrains.annotations.NotNull;

public class MyWindow implements ToolWindowFactory {

  private JPanel mainPanel;
  private JTextField fileText;
  private JButton jButton;

  @Override
  public void init(ToolWindow window) {
    // init
    this.mainPanel = new JPanel();
    JTextField rootPathField = new JTextField("", 18);
    JTextField packageNameField = new JTextField("com.mgl", 25);
    JTextField rootClassNameField = new JTextField("Test", 25);
    JTextField generateResultField = new JTextField("", 25);
    JButton generateButton = new JButton("生成代码");
    JTextArea jsonInputArea = new JTextArea(5, 47);

    // listener
    MyGenerateListener myGenerateListener = new MyGenerateListener(rootPathField, packageNameField,
        rootClassNameField, generateResultField, jsonInputArea);
    generateButton.addActionListener(myGenerateListener);

    Label rootPathLabel = new Label("输出根路径");
    mainPanel.add(rootPathLabel);
    mainPanel.add(rootPathField);
    jButton = new JButton("浏览");// 钮1
    mainPanel.add(jButton);
    jButton.addActionListener(new FileListener(rootPathField));

    Label packageNameLabel = new Label("输出包名称");
    mainPanel.add(packageNameLabel);
    mainPanel.add(packageNameField);

    Label rootClassNameLabel = new Label("输出类命名");
    mainPanel.add(rootClassNameLabel);
    mainPanel.add(rootClassNameField);

    Label generateResultLabel = new Label("生成类结果");
    mainPanel.add(generateResultLabel);
    mainPanel.add(generateResultField);

    mainPanel.add(generateButton);
    mainPanel.add(jsonInputArea);
  }

  public void fileInit() {
    FlowLayout layout = new FlowLayout();// 布局
    JLabel label = new JLabel("请选择文件：");// 标签
    fileText = new JTextField(30);// 文本域


    // 设置布局
    layout.setAlignment(FlowLayout.LEFT);// 左对齐

    mainPanel.add(label);
    mainPanel.add(fileText);
    mainPanel.add(jButton);
  }

  @Override
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
    Content content = contentFactory.createContent(this.mainPanel, "", false);
    toolWindow.getContentManager().addContent(content);
  }
}
