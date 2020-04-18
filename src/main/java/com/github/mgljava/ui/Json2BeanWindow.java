package com.github.mgljava.ui;

import static com.github.mgljava.contract.Constants.BASIC_CLASS_NAME;
import static com.github.mgljava.contract.Constants.EXPLORE;
import static com.github.mgljava.contract.Constants.GENERATE_CODE;
import static com.github.mgljava.contract.Constants.GENERATE_RESULT;
import static com.github.mgljava.contract.Constants.INPUT_HINT;
import static com.github.mgljava.contract.Constants.PACKAGE_NAME;
import static com.github.mgljava.contract.Constants.ROOT_PATH;

import com.github.mgljava.listener.FileChooserListener;
import com.github.mgljava.listener.MyGenerateListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import java.awt.Label;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.jetbrains.annotations.NotNull;

/**
 * Idea 插件窗口
 * author：mgljava
 */
public final class Json2BeanWindow implements ToolWindowFactory {

  private JPanel mainPanel;

  @Override
  public void init(ToolWindow window) {
    // init
    this.mainPanel = new JPanel();
    JTextField rootPathField = new JTextField("", 18);
    JTextField packageNameField = new JTextField("", 25);
    JTextField rootClassNameField = new JTextField("", 25);
    JTextField generateResultField = new JTextField("", 25);
    generateResultField.setEnabled(false);
    JButton generateButton = new JButton(GENERATE_CODE);
    JTextArea jsonInputArea = new JTextArea(INPUT_HINT, 20, 47);
    // jsonInputArea.setLineWrap(true);
    // listener
    MyGenerateListener myGenerateListener = new MyGenerateListener(rootPathField, packageNameField,
        rootClassNameField, generateResultField, jsonInputArea);
    generateButton.addActionListener(myGenerateListener);

    Label rootPathLabel = new Label(ROOT_PATH);
    mainPanel.add(rootPathLabel);
    mainPanel.add(rootPathField);
    JButton fileBrowse = new JButton(EXPLORE);
    mainPanel.add(fileBrowse);
    fileBrowse.addActionListener(new FileChooserListener(rootPathField));

    Label packageNameLabel = new Label(PACKAGE_NAME);
    mainPanel.add(packageNameLabel);
    mainPanel.add(packageNameField);

    Label rootClassNameLabel = new Label(BASIC_CLASS_NAME);
    mainPanel.add(rootClassNameLabel);
    mainPanel.add(rootClassNameField);

    Label generateResultLabel = new Label(GENERATE_RESULT);
    mainPanel.add(generateResultLabel);
    mainPanel.add(generateResultField);

    mainPanel.add(generateButton);


    JScrollPane jScrollPane = new JScrollPane(jsonInputArea);
    jScrollPane.setHorizontalScrollBarPolicy(
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jScrollPane.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    mainPanel.add(jScrollPane);
  }

  @Override
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
    Content content = contentFactory.createContent(this.mainPanel, "", false);
    toolWindow.getContentManager().addContent(content);
  }
}
