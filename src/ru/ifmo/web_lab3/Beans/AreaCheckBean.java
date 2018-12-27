package ru.ifmo.web_lab3.Beans;

import org.primefaces.PrimeFaces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.Map;

public class AreaCheckBean {

    private HistoryBean historyBean;

    public HistoryBean getHistoryBean() {
        return historyBean;
    }

    public void setHistoryBean(HistoryBean historyBean) {
        this.historyBean = historyBean;
    }

    public void pointCheck() {
        Map<String,String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        if (!params.containsKey("x") || !params.containsKey("y") || !params.containsKey("r")) {
            PrimeFaces.current().ajax().addCallbackParam("validationFailed", true);
            return;
        }

        try {
            double x = Double.parseDouble(params.get("x").replace(",", "."));
            double y = Double.parseDouble(params.get("y").replace(",", "."));
            double r = Double.parseDouble(params.get("r").replace(",", "."));

            boolean checkResult = isInArea(x, y, r);
            PrimeFaces.current().ajax().addCallbackParam("x", x);
            PrimeFaces.current().ajax().addCallbackParam("y", y);
            PrimeFaces.current().ajax().addCallbackParam("isInArea", checkResult);

            if (!historyBean.addResult(x, y, r, checkResult))
                System.err.println("Failed to add history entry.");
        }
        catch (Exception e) {
            PrimeFaces.current().ajax().addCallbackParam("validationFailed", true);
        }
    }

    public static boolean isInArea(double x, double y, double r) {
        if (x <= r/2 && x >= 0 && y <= 0 && y >= -r)
            return true; // In rectangle
        if (x <= 0 && y >= 0 && (x*x + y*y) <= r*r/4)
            return true; // In quarter-circle
        if (x >= 0 && y >= 0 && Math.abs(x) + Math.abs(y) <= r)
            return true;

        return false;
    }
}
