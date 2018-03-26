package fr.regate.project.controller;

import fr.regate.project.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private final LancementRegate runRegate;
    private final JButton LR_BTN_END;
    private final JButton LR_BTN_VALIDATE;
    private final JButton LR_BTN_START;
    private final JButton LR_BTN_REINIT;

    public ButtonListener(LancementRegate runRegate) {
        this.runRegate = runRegate;
        runRegate.getAllBtn().get(0).addActionListener(this);
        LR_BTN_END = runRegate.getBtnEnd();
        LR_BTN_START = runRegate.getBtnStart();
        LR_BTN_VALIDATE = runRegate.getBtnSelect();
        LR_BTN_REINIT = runRegate.getBtnReinit();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == LR_BTN_END) {
            System.out.print(e.getSource());
        }
    }
}
