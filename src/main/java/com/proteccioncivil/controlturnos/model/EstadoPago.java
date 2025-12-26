package com.proteccioncivil.controlturnos.model;

import java.time.YearMonth;

public class EstadoPago {

    private YearMonth mes;   // Ej: 2025-01
    private boolean pagado;

    public EstadoPago() {}

    public EstadoPago(YearMonth mes, boolean pagado) {
        this.mes = mes;
        this.pagado = pagado;
    }

    public YearMonth getMes() {
        return mes;
    }

    public void setMes(YearMonth mes) {
        this.mes = mes;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }
}
