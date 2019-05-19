package com.example.alumno.parcial;

public class formulas
{
    public String formule;
    public String qty;

    @Override
    public String toString() {
        return "formulas{" +
                "formule='" + formule + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }

    public String getFormule() {
        return formule;
    }

    public void setFormule(String formule) {
        this.formule = formule;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
