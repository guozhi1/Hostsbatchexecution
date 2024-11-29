package org.example;

import org.example.ButtoMeno.CommandsMenu;
import org.example.ButtoMeno.IPlistMenu;
import org.example.ButtoMeno.base64Menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class monitor implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) { //多个方法菜单调用
        Button source = (Button) e.getSource();
        if(source.getLabel().equals("添加IP主机")){
            new IPlistMenu().Restart_Button();
        } else if (source.getLabel().equals("执行命令")) {
            new CommandsMenu().Restart_Button();
        } else if (source.getLabel().equals("Bser64编码魔改")) {
            new base64Menu().Restart_Button();
        }
    }
}
