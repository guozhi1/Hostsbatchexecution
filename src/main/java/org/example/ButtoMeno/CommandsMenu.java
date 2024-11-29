package org.example.ButtoMeno;
import org.example.Graphicalpage ;
import org.example.Buttonimpost;
import org.example.Tools.Instructionissuance;
import org.example.shared.variableshared;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandsMenu implements Buttonimpost {
    static Panel panel1 =  Graphicalpage.getPan(); //容器
    public static String command ;
    @Override
    public void Restart_Button() {
        panel1.removeAll();
        Button sendText = new_Button("发送");
        TextField textField =  new TextField(); //文本框
        Button retPan = new_Button("返回");
        panel1.add(textField);
        panel1.add(sendText);
        panel1.add(retPan);
        panel1.revalidate();
        panel1.repaint();
        sendText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command = textField.getText(); //命令
                variableshared.Commandvar = command;//赋值到公共变量
                new Instructionissuance();
                new Graphicalpage().Restart_Button();

            }
        });
        retPan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Graphicalpage().Restart_Button();
            }
        });
    }

    @Override
    public Button new_Button(String Button_title) {
        return new Button(Button_title);
    }
}
