package fr.regate.project.controller;

import fr.regate.project.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private AjoutParticipant ap;
    private AjoutRegate ar;
    private Classement cla;
    private LancementRegate runRegate;
    private ModifRegate mr;

    private final JButton RUNREGATE_BTN_END;
    private final JButton RUNREGATE_BTN_VALIDATE;
    private final JButton RUNREGATE_BTN_START;
    private final JButton RUNREGATE_BTN_REINIT;


    public ButtonListener(LoadView views) {
        ap = views.getAp();
        ar = views.getAr();
        cla = views.getCla();
        this.runRegate = views.getLr();
        mr = views.getMr();

        RUNREGATE_BTN_END = runRegate.getBtnEnd();
        RUNREGATE_BTN_START = runRegate.getBtnStart();
        RUNREGATE_BTN_VALIDATE = runRegate.getBtnSelect();
        RUNREGATE_BTN_REINIT = runRegate.getBtnReinit();

        this.listeners();
    }

    public void listeners() {
        for (int i = 0; i < runRegate.getAllBtn().size(); i++) {
            runRegate.getAllBtn().get(i).addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == RUNREGATE_BTN_END) {
            System.out.print(e.getSource());
        }else if (e.getSource() != RUNREGATE_BTN_VALIDATE) {
            System.out.print(e.getSource());
        }else if (e.getSource() != RUNREGATE_BTN_START) {
            System.out.print(e.getSource());
        }else if (e.getSource() != RUNREGATE_BTN_REINIT) {
            System.out.print(e.getSource());
        }
    }
}