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
    private Controller controller;

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
        controller = new Controller(views);

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
            controller.runRegStopChrono();
        }else if (e.getSource() == RUNREGATE_BTN_VALIDATE) {
           controller.runRegValidate();
        }else if (e.getSource() == RUNREGATE_BTN_START) {
            controller.runRegRunChrono();
        }else if (e.getSource() == RUNREGATE_BTN_REINIT) {
            controller.runRegReinitChrono();
        }
    }
}