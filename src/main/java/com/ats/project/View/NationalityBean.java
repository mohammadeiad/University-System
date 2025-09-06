package com.ats.project.View;

import com.ats.project.model.Nationality;
import com.ats.project.service.NationalityService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("nationalityBean")
@ViewScoped
public class NationalityBean implements Serializable {

    private final NationalityService service;

    private List<Nationality> list;
    private Nationality selected;

    @Inject
    public NationalityBean(NationalityService service) {
        this.service = service;
    }

    @PostConstruct
    public void init() {
        refresh();
        resetForm();
    }

    public void save() {
        try {
            service.saveOrUpdate(selected);
            addMsg(FacesMessage.SEVERITY_INFO, "Saved", "Nationality saved successfully");
            refresh();
            resetForm();
        } catch (Exception e) {
            addMsg(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        }
    }

    public void edit(Nationality n) {
        if (n != null && n.getId() != null) {
            this.selected = service.findById(n.getId()); // returns Nationality directly
        }
    }


    public void prepareAdd() {
        resetForm();
    }

    public void delete(Nationality n) {
        try {
            if (n != null && n.getId() != null) {
                service.deleteById(n.getId());
                addMsg(FacesMessage.SEVERITY_INFO, "Deleted", "Nationality removed");
                refresh();
            }
        } catch (Exception e) {
            addMsg(FacesMessage.SEVERITY_ERROR, "Delete failed", e.getMessage());
        }
    }

    private void refresh() {
        list = service.findAll();
    }

    private void resetForm() {
        selected = new Nationality();
    }

    private void addMsg(FacesMessage.Severity s, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(s, summary, detail));
    }

    // getters/setters
    public List<Nationality> getList() { return list; }
    public Nationality getSelected() { return selected; }
    public void setSelected(Nationality selected) { this.selected = selected; }
}
