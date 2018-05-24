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
    private final JButton ADDPARTICIPANT_BTN_SEND;
    private final JButton ADDREGATE_BTN_SEND;
    private final JButton ADDREGATE_BTN_ADDPARTICIPANT;
    private final JButton ADDSHIP_BTN_SEND;
    private final JButton MODIFREGATE_BTN_VALIDER;
    private final JButton MODIFREGATE_BTN_SEND;
    private final JButton DELETREGATE_BTN_DELET;

    private final JMenuItem MENU_ITEM_ADDREGATE;
    private final JMenuItem MENU_ITEM_RUNREGATE;
    private final JMenuItem MENU_ITEM_ADDPARTICIPANT;
    private final JMenuItem MENU_ITEM_CHANGEREGATE;
    private final JMenuItem MENU_ITEM_DELETREGATE;
    private final JMenuItem MENU_ITEM_CLASSEMENT;
    private final JMenuItem MENU_ITEM_MODIFYPARTICIPANT;
    private final JMenuItem MENU_ITEM_ADDSHIP;
    


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
        MENU_ITEM_MODIFYPARTICIPANT = views.getWindow().getMntmModifyParticipant();
        MENU_ITEM_CHANGEREGATE = views.getWindow().getMntmModifyRegate();
        MENU_ITEM_DELETREGATE = views.getWindow().getMntmDeleteRgate();
        MENU_ITEM_CLASSEMENT = views.getWindow().getMntmClassementRegate();
        MENU_ITEM_ADDSHIP = views.getWindow().getmntmAddShip();
       
        ADDPARTICIPANT_BTN_SEND = views.getAp().getBtnSend();
        
        ADDREGATE_BTN_SEND = views.getAr().getBtnSendAjoutRegate();
        ADDREGATE_BTN_ADDPARTICIPANT = views.getAr().getBtnAddParticipant();
        MODIFREGATE_BTN_VALIDER = views.getAr().getBtnSelRegate();
        MODIFREGATE_BTN_SEND = views.getAr().getBtnSendModifRegate();
        DELETREGATE_BTN_DELET = views.getSr().getBtnDelRegate();
        
        ADDSHIP_BTN_SEND =views.getAb().getBtnSend();
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
        views.getAb().getBtnSend().addActionListener(this);
        views.getAr().getBtnSendAjoutRegate().addActionListener(this);
        views.getAr().getBtnSelRegate().addActionListener(this);
        views.getAr().getBtnAddParticipant().addActionListener(this);
        views.getAr().getBtnSendModifRegate().addActionListener(this);
        views.getSr().getBtnDelRegate().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == RUNREGATE_BTN_END) {
            controller.runRegStopChrono();
        }else if (e.getSource() == RUNREGATE_BTN_VALIDATE) {
           controller.runRegValidate();
           controller.selRegate();
        }else if (e.getSource() == RUNREGATE_BTN_START) {
            controller.runRegRunChrono();
        }else if (e.getSource() == RUNREGATE_BTN_REINIT) {
            controller.runRegReinitChrono();
        }
        
        else if (e.getSource() == MENU_ITEM_ADDREGATE) {
            controller.showView("ADD_REGATE");
        }else if (e.getSource() == MENU_ITEM_RUNREGATE) {
        	controller.showView("RUN_REGATE");
        }else if (e.getSource() == MENU_ITEM_ADDPARTICIPANT) {
        	controller.showView("ADD_PARTICIPANT");
        }else if (e.getSource() == MENU_ITEM_MODIFYPARTICIPANT) {
        	controller.showView("MODIF_PARTICIPANT");
        }else if (e.getSource() == MENU_ITEM_CHANGEREGATE) {
            controller.showView("MODIF_REGATE");
        }else if (e.getSource() == MENU_ITEM_CLASSEMENT) {
            controller.showView("CLASSEMENT");
        }else if (e.getSource() == MENU_ITEM_ADDSHIP) {
        	controller.showView("ADD_SHIP");
        }else if (e.getSource() == MENU_ITEM_DELETREGATE) {
        	controller.showView("SUPPR_REGATE");
        }
        
        else if (e.getSource() == ADDPARTICIPANT_BTN_SEND) {
        	controller.bddAddParticipant();
        }
        
        else if (e.getSource() == ADDREGATE_BTN_SEND) {
        	controller.bddAddRegate();
        	controller.bddLinkRegateToPart();
        }else if (e.getSource() == ADDREGATE_BTN_ADDPARTICIPANT) {
        	controller.ajoutParticipantTable();
        }else if (e.getSource() == ADDSHIP_BTN_SEND) {
        	controller.bddAddShip();
        }else if (e.getSource() == MODIFREGATE_BTN_VALIDER) {
        	controller.selRegateToModif();
        }else if (e.getSource() == MODIFREGATE_BTN_SEND) {
        	controller.bddUpdateRegate();
        }else if(e.getSource() == DELETREGATE_BTN_DELET) {
        	controller.bddDeletRegate();
        }
    }
}