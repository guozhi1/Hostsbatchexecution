package org.example;

import javafx.scene.SubScene;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Graphicalpage implements Buttonimpost{
    final int GUI_x = 500;
    final int GUI_y = 500;
    final int width = 500; //长宽
    final int height = 500;

    static Frame  frame = new Frame();
    static Panel pan = new Panel(); //容器
    public  void GUIinit(){
        frame.setVisible(true);//
        frame.setLocation(GUI_x, GUI_y); //面板出现位置
        frame.setSize(width, height); //长宽
    }
    public static Panel getPan(){ //容器
        return pan;
    }

    public void refreshInitialPanel(){
    }

    @Override
    public void Restart_Button() {
        pan.removeAll();
        pan.setLayout(new GridLayout(4,5));
        frame.add(pan);
        Button ipList = new_Button("添加IP主机");
        Button command = new_Button("执行命令");
        Button base64 = new_Button("Bser64编码魔改");
        pan.add(ipList);
        pan.add(command);
        pan.add(base64);
        // 重新布局和刷新
        pan.revalidate();
        pan.repaint();
        monitor monitor = new monitor();
        ipList.addActionListener(monitor);
        command.addActionListener(monitor);
        base64.addActionListener(monitor);


    }

    @Override
    public Button new_Button(String Button_title) {
        return new Button(Button_title);
    }
}
