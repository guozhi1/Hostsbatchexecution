package org.example.ButtoMeno;

import com.google.gson.Gson;
import org.example.Buttonimpost;
import org.example.Graphicalpage;
import org.example.Tools.Instructionissuance;
import org.example.shared.variableshared;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class IPlistMenu implements Buttonimpost {
    static Panel panel1 =  Graphicalpage.getPan(); //容器
    public String Ipdata ;
    @Override
    public void Restart_Button() {
        panel1.removeAll();
        Button sendText = new_Button("保存并推出");
        JTextArea JtextField =  new JTextArea(); //文本框(多行)
        panel1.add(JtextField);
        panel1.add(sendText);
        panel1.revalidate();
        panel1.repaint();
        sendText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ipdata = JtextField.getText(); //ip列表
                variableshared.Iplistdata = Ipdata;//赋值到公共变量
                new Graphicalpage().Restart_Button(); //返回面板
            }
        });
    }

    @Override
    public Button new_Button(String Button_title) {
        return new Button(Button_title);
    }
}
