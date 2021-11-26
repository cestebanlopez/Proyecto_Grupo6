package com.dansan.frontend;

import com.dansan.bean.HistoricoBean;
import com.dansan.database.BDManagement;
import com.dansan.fechahora.FechaHora;
import com.dansan.ticket.TicketSalida;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author dansan
 */
public class VentanaSalida extends javax.swing.JDialog {

    BDManagement bdm = new BDManagement();

    
    public VentanaSalida(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setVisible(true);
    }

    
    @SuppressWarnings("unchecked")    
    private void initComponents() {

        labelPatente = new javax.swing.JLabel();
        campoPatente = new javax.swing.JFormattedTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPatente.setText("Patente");

        try {
            campoPatente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UUU###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(labelPatente, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(campoPatente, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(119, 119, 119)
                                        .addComponent(btnAceptar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCancelar)))
                        .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelPatente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(campoPatente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAceptar)
                                .addComponent(btnCancelar))
                        .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }            

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {
        //Compruebo si es una patente valida
        String patente = campoPatente.getText();
        if (patente.length() == 6) {
            //Obtengo bean
            HistoricoBean bean = bdm.obtenerCoche(patente);

            //Seteo las fechas del ticket
            Date date = new Date();
            FechaHora fechahora = new FechaHora(date);
            String hora = fechahora.obtenerFechaHora();
            Date dateEntrada = new Date((long) bean.getHora_ocupacion());
            FechaHora fechaEntrada = new FechaHora(dateEntrada);
            String horaEntrada = fechaEntrada.obtenerFechaHora();

            //Obtango importe ,graba en historico y borro registro de cocheras
            double tarifa = bdm.obtenerTarifa(bean.getTipo_auto());
            double importe = calcularImporte(tarifa, dateEntrada.getTime(), date.getTime());
            bdm.grabarHistorico(patente, bean.getHora_ocupacion(), bean.getTipo_auto(), bean.getPiso(), importe);
            bdm.borrarCocheras(patente);

            //imprimo ticket
            imprimirTicket(horaEntrada, bean.getPiso(), bean.getTipo_auto(), patente, hora, importe);
            JOptionPane.showMessageDialog(new JFrame(), "Operacion Satisfactoria", "", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void imprimirTicket(String hora, int piso, char tipo, String patente, String hora_salida, double importe) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new TicketSalida(hora, piso, tipo, patente, hora_salida, importe));
        PageFormat pageFormat = new PageFormat();
        pageFormat = job.pageDialog(pageFormat);
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(VentanaEntrada.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private double calcularImporte(double tarifa, long entrada, long salida) {
        double tiempo = salida - entrada;
        double segundos = tiempo / 1000;
        double minutos = segundos / 60;
        double horas = minutos / 60;
        if (horas < 1) {
            horas = 1; // aunque sea se cobra una hora que es el minimo
        }
        double importe = tarifa * horas;
        return importe;
    }
    
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JFormattedTextField campoPatente;
    private javax.swing.JLabel labelPatente;
}
