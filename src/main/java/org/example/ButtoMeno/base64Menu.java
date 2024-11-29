package org.example.ButtoMeno;
import org.example.Tools.CustomBase64;
import org.example.shared.variableshared;
import org.example.Buttonimpost;
import org.example.Graphicalpage;

import javax.lang.model.element.VariableElement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class base64Menu implements Buttonimpost {
    static Panel panel1 =  Graphicalpage.getPan();

    public static String Baser64Data;
    @Override
    public void Restart_Button() {
        panel1.removeAll();
        Button sendText = new_Button("编码");
        Button RetPan = new_Button("返回");
        JTextArea JtextField =  new JTextArea(); //文本框(多行) //明文
        JTextArea enJtextField =  new JTextArea(); //密文
        enJtextField.setEnabled(true); //不可编辑
        panel1.add(JtextField);
        panel1.add(sendText);
        panel1.add(enJtextField);

        panel1.add(RetPan);
        panel1.revalidate();
        panel1.repaint();
        sendText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Button source = (Button) e.getSource();
                Baser64Data = JtextField.getText(); //获取明文
                //加密显示密文文字
                enJtextField.setText(CustomBase64.encode(Baser64Data.getBytes()));
            }
        });
        RetPan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Graphicalpage().Restart_Button(); //返回面板
            }
        });
    }

    @Override
    public Button new_Button(String Button_title) {
        return new Button(Button_title);
    }
}
