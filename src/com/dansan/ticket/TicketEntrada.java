package com.dansan.ticket;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

/**
 *
 * @author dansan
 */
public class TicketEntrada implements Printable {

    private String hora_entrada;
    private int piso;
    private char tipo;
    private String patente;

    public TicketEntrada(String hora, int piso, char tipo, String patente) {
        this.hora_entrada = hora;
        this.piso = piso;
        this.tipo = tipo;
        this.patente = patente;

    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
        if (pageIndex == 0) {
            graphics.drawString("De Entrada", 200, 80);
            graphics.drawString("Entrada", 120, 120);
            graphics.drawString(hora_entrada, 180, 120);
            graphics.drawString("Piso", 120, 140);
            graphics.drawString("" + piso, 180, 140);
            graphics.drawString("Tipo", 120, 160);
            graphics.drawString("" + tipo, 180, 160);
            graphics.drawString("Patente", 120, 180);
            graphics.drawString(patente, 180, 180);
            return PAGE_EXISTS;
        } else {
            return NO_SUCH_PAGE;
        }
    }

}
