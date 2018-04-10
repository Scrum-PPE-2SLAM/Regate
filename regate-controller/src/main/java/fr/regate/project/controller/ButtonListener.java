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
    private Window window;

    private final JButton RUNREGATE_BTN_END;
    private final JButton RUNREGATE_BTN_VALIDATE;
    private final JButton RUNREGATE_BTN_START;
    private final JButton RUNREGATE_BTN_REINIT;

    private final JMenuItem MENU_ITEM_ADDREGATE;
    private final JMenuItem MENU_ITEM_RUNREGATE;
    private final JMenuItem MENU_ITEM_ADDPARTICIPANT;
    private final JMenuItem MENU_ITEM_CHANGEREGATE;
    private final JMenuItem MENU_ITEM_CLASSEMENT;
    


    public ButtonListener(LoadView views) {
        window = (Window) views.getAllViews().get("Window");
        ap = (AjoutParticipant) views.getAllViews().get("AddParticipant");
        ar = (AjoutRegate) views.getAllViews().get("AddRegate");
        cla = (Classement) views.getAllViews().get("Classement");
        runRegate = (LancementRegate) views.getAllViews().get("RunRegate");
        mr = (ModifRegate) views.getAllViews().get("ModifRegate");
        controller = new Controller(views);

        RUNREGATE_BTN_END = runRegate.getBtnEnd();
        RUNREGATE_BTN_START = runRegate.getBtnStart();
        RUNREGATE_BTN_VALIDATE = runRegate.getBtnSelect();
        RUNREGATE_BTN_REINIT = runRegate.getBtnReinit();

        MENU_ITEM_ADDREGATE = window.getMntmNewRegate();
        MENU_ITEM_RUNREGATE = window.getMntmRunRegate();
        MENU_ITEM_ADDPARTICIPANT = window.getMntmAddParticipant();
        MENU_ITEM_CHANGEREGATE = window.getMntmModifyRegate();
        MENU_ITEM_CLASSEMENT = window.getMntmClassementPerCatgorie();
        
        this.listeners();
    }

    public void listeners() {
        for (int i = 0; i < runRegate.getAllBtn().size(); i++) {
            runRegate.getAllBtn().get(i).addActionListener(this);
        }

        for (int i = 0; i < window.getMenuItemList().size(); i++) {
            window.getMenuItemList().get(i).addActionListener(this);
        }

        ap.getBtnSend().addActionListener(this);
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
        }else if (e.getSource() == MENU_ITEM_ADDREGATE) {
            controller.printAddRegateView();
        }else if (e.getSource() == MENU_ITEM_RUNREGATE) {
        	controller.showView("RUN_REGATE");
        }else if (e.getSource() == MENU_ITEM_ADDPARTICIPANT) {
        	controller.printAddParticipantView();
        }else if (e.getSource() == MENU_ITEM_CHANGEREGATE) {
        	controller.printChangeRegateView();
        }else if (e.getSource() == MENU_ITEM_CLASSEMENT) {
        	controller.printClassementView();
        }
    }
}