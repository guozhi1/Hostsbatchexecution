package org.example;
import org.example.shared.variableshared;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Graphicalpage  graphicalpage = new Graphicalpage();
        graphicalpage.GUIinit(); //初始化面板
        graphicalpage.Restart_Button(); //创建容器
    }
}