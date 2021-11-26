package com.dansan.ticket;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.text.DecimalFormat;

/**
 *
 * @author dansan
 */
public class TicketSalida implements Printable {

    private String hora_entrada;
    private int piso;
    private char tipo;
    private String patente;
    private String hora_salida;
    private double importe;

    public TicketSalida(String hora, int piso, char tipo, String patente, String hora_salida, double importe) {
        this.hora_entrada = hora;
        this.piso = piso;
        this.tipo = tipo;
        this.patente = patente;
        this.hora_salida = hora_salida;
        this.importe = importe;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
        if (pageIndex == 0) {
            graphics.drawString("De Salida", 200, 80);
            graphics.drawString("Entrada", 120, 120);
            graphics.drawString(hora_entrada, 180, 120);
            graphics.drawString("Piso", 120, 140);
            graphics.drawString("" + piso, 180, 140);
            graphics.drawString("Tipo", 120, 160);
            graphics.drawString("" + tipo, 180, 160);
            graphics.drawString("Patente", 120, 180);
            graphics.drawString(patente, 180, 180);
            graphics.drawString("Salida", 120, 200);
            graphics.drawString(hora_salida, 180, 200);
            graphics.drawString("Importe", 120, 220);
            DecimalFormat df = new DecimalFormat("#.##");
            graphics.drawString("" + df.format(importe), 180, 220);

            return PAGE_EXISTS;
        } else {
            return NO_SUCH_PAGE;
        }
    }

}
