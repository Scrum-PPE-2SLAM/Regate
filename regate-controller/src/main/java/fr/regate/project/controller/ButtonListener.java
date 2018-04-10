package fr.regate.project.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private Controller controller;
    private LoadView views;

    private final JButton RUNREGATE_BTN_END;
    private final JButton RUNREGATE_BTN_VALIDATE;
    private final JButton RUNREGATE_BTN_START;
    private final JButton RUNREGATE_BTN_REINIT;

    private final JMenuItem MENU_ITEM_ADDREGATE;
    private final JMenuItem MENU_ITEM_RUNREGATE;
    private final JMenuItem MENU_ITEM_ADDPARTICIPANT;
    private final JMenuItem MENU_ITEM_CHANGEREGATE;
    private final JMenuItem MENU_ITEM_CLASSEMENT;
    


    ButtonListener(LoadView views) {
        this.views = views;
        controller = new Controller(views);

        RUNREGATE_BTN_END = views.getLr().getBtnEnd();
        RUNREGATE_BTN_START = views.getLr().getBtnStart();
        RUNREGATE_BTN_VALIDATE = views.getLr().getBtnSelect();
        RUNREGATE_BTN_REINIT = views.getLr().getBtnReinit();

        MENU_ITEM_ADDREGATE = views.getWindow().getMntmNewRegate();
        MENU_ITEM_RUNREGATE = views.getWindow().getMntmRunRegate();
        MENU_ITEM_ADDPARTICIPANT = views.getWindow().getMntmAddParticipant();
        MENU_ITEM_CHANGEREGATE = views.getWindow().getMntmModifyRegate();
        MENU_ITEM_CLASSEMENT = views.getWindow().getMntmClassementPerCatgorie();
        
        this.listeners();
    }

    private void listeners() {
        for (int i = 0; i < views.getLr().getAllBtn().size(); i++) {
            views.getLr().getAllBtn().get(i).addActionListener(this);
        }

        for (int i = 0; i < views.getWindow().getMenuItemList().size(); i++) {
            views.getWindow().getMenuItemList().get(i).addActionListener(this);
        }

        views.getAp().getBtnSend().addActionListener(this);
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
            controller.showView("ADD_REGATE");
        }else if (e.getSource() == MENU_ITEM_RUNREGATE) {
        	controller.showView("RUN_REGATE");
        }else if (e.getSource() == MENU_ITEM_ADDPARTICIPANT) {
        	controller.showView("ADD_PARTICIPANT");
        }else if (e.getSource() == MENU_ITEM_CHANGEREGATE) {
            controller.showView("MODIF_REGATE");
        }else if (e.getSource() == MENU_ITEM_CLASSEMENT) {
            controller.showView("CLASSEMENT");
        }
    }
}