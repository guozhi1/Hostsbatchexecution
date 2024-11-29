package org.example.Tools;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;

public interface TipsBox {
    public static void Tips(String tips)
    {
        JOptionPane.showConfirmDialog(null, tips, "Tips", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
}
