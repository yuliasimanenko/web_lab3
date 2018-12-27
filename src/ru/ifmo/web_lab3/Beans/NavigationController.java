package ru.ifmo.web_lab3.Beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

public class NavigationController implements Serializable {
    public String viewMainPage() {
        return "page";
    }

    public String viewHomePage() {
        return "page";
    }
}
