package com.dansan.frontend;

import com.dansan.bean.HistoricoBean;
import com.dansan.database.BDManagement;
import com.dansan.fechahora.FechaHora;
import com.dansan.ticket.TicketEntrada;
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
public class VentanaEntrada extends javax.swing.JDialog {

    private BDManagement bdm = new BDManagement();
    private static final int maxCochesEnCocherasPorPiso = 20;
    private static final int maxPiso = 18;

    public VentanaEntrada(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setVisible(true);
    }

    VentanaEntrada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        labelPatente = new javax.swing.JLabel();
        labelPiso = new javax.swing.JLabel();
        labelTipo = new javax.swing.JLabel();
        campoPatente = new javax.swing.JFormattedTextField();
        campoPiso = new javax.swing.JFormattedTextField();
        campoTipo = new javax.swing.JFormattedTextField();
        labelIndPiso = new javax.swing.JLabel();
        labelIndTipo = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        labelPatente.setText("Patente");

        labelPiso.setText("Piso");

        labelTipo.setText("Tipo de Auto");

        try {
            campoPatente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UUU###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            campoPiso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            campoTipo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("U")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }       

        labelIndPiso.setText("Del 01 al 18");

        labelIndTipo.setText("P, M, G");

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
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelPatente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelPiso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(campoPatente, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(campoTipo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                                                        .addComponent(campoPiso, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(labelIndPiso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(labelIndTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addComponent(btnAceptar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCancelar)))
                        .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelPatente)
                                .addComponent(campoPatente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(campoPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelIndPiso))
                                .addComponent(labelPiso))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(campoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelIndTipo))
                                .addComponent(labelTipo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAceptar)
                                .addComponent(btnCancelar))
                        .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {
        String patente = campoPatente.getText();
        int piso = 1;
        if (!campoPiso.getText().equals("  ")) {
            piso = Integer.parseInt(campoPiso.getText());
        }
        char tipo = campoTipo.getText().charAt(0);
        tipo = Character.toUpperCase(tipo);
        HistoricoBean bean = bdm.obtenerCoche(patente);
        boolean patenteExistente = bean.getPatente() == patente;
        boolean longitudPatente = patente.length() == 6;
        boolean tipoCocheValido = tipo == 'G' || tipo == 'M' || tipo == 'P';
        if (bdm.numCochesEnCocheras(piso) < maxCochesEnCocherasPorPiso && piso <= maxPiso && !patenteExistente && tipoCocheValido && longitudPatente) { //Solo se permiten 20 autos por piso
            bdm.agregarCocheras(patente, piso, tipo);
            Date date = new Date();
            FechaHora fechahora = new FechaHora(date);
            String hora = fechahora.obtenerFechaHora();
            imprimirTicket(hora, piso, tipo, patente);
            JOptionPane.showMessageDialog(new JFrame(), "Operacion Satisfactoria", "", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Piso Ocupado o Error de Piso Inexistente  o Error en los Datos Introducidos", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    public void imprimirTicket(String hora, int piso, char tipo, String patente) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new TicketEntrada(hora, piso, tipo, patente));
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

    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JFormattedTextField campoPatente;
    private javax.swing.JFormattedTextField campoPiso;
    private javax.swing.JFormattedTextField campoTipo;
    private javax.swing.JLabel labelIndPiso;
    private javax.swing.JLabel labelIndTipo;
    private javax.swing.JLabel labelPatente;
    private javax.swing.JLabel labelPiso;
    private javax.swing.JLabel labelTipo;
}
