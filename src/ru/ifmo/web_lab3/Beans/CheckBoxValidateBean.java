package ru.ifmo.web_lab3.Beans;


import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.component.html.*;

//need to determine actions in case validation has passed
public class CheckBoxValidateBean{

    FormInputBean inputBean;

    public void setInputBean(FormInputBean inputBean) {
        this.inputBean = inputBean;
    }

    public void validate(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        UIComponent components = event.getComponent();
        int num = 0;
        for (int i = 0; i < 7; i++) {
            HtmlSelectBooleanCheckbox checkbox = (HtmlSelectBooleanCheckbox) components.findComponent("x" + String.valueOf(i));
            boolean value = (boolean) checkbox.getValue();
            if (value)
                num += 1;
        }

//            if ((bo(((SelectBooleanCheckbox)components.findComponent("x" + String.valueOf(i))).getValue() != false)
//                num++;



        if (num != 1) {
            String msg = num < 1 ? "Выберите X" : "Выберите только одно значение X";

            String clientId = components.findComponent("x").getClientId();
            //String clientId = event.getComponent().getClientId();
            FacesMessage message = new FacesMessage(msg);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(clientId, message);
            fc.addMessage(components.findComponent("checkResultOutput").getClientId(), new FacesMessage(""));
            inputBean.setResultStr("");
            fc.renderResponse();
        }

    }

}