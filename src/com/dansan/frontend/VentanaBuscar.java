package com.dansan.frontend;

import com.dansan.bean.HistoricoBean;
import com.dansan.database.BDManagement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dansan
 */
public class VentanaBuscar extends javax.swing.JDialog {

    BDManagement bdm = new BDManagement();

    public VentanaBuscar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")    
    private void initComponents() {

        labelPatente = new javax.swing.JLabel();
        labelPiso = new javax.swing.JLabel();
        campoPatente = new javax.swing.JFormattedTextField();
        campoPiso = new javax.swing.JFormattedTextField();
        btnBuscarPatente = new javax.swing.JButton();
        btnBuscarPiso = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tablaCoches = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPatente.setText("Patente");

        labelPiso.setText("Piso");

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

        btnBuscarPatente.setText("Buscar");
        btnBuscarPatente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPatenteActionPerformed(evt);
            }
        });

        btnBuscarPiso.setText("Buscar");
        btnBuscarPiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPisoActionPerformed(evt);
            }
        });

        tablaCoches.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null}
                },
                new String[]{
                    "Patente", "Piso", "Hora de Ingreso", "Tipo de Auto"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        scrollTabla.setViewportView(tablaCoches);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(labelPatente, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                                .addComponent(labelPiso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(campoPatente)
                                                .addComponent(campoPiso, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btnBuscarPatente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnBuscarPiso, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelPatente)
                                .addComponent(campoPatente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscarPatente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelPiso)
                                .addComponent(campoPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscarPiso))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(btnCerrar))
        );

        pack();
    }

    
    private void btnBuscarPatenteActionPerformed(java.awt.event.ActionEvent evt) {
        if (campoPatente.getText().trim().length()==6) {
            HistoricoBean bean = bdm.obtenerCoche(campoPatente.getText());            
            Double d2 = bean.getHora_ocupacion();
            Date dateEntrada = new Date(d2.longValue());
            DefaultTableModel tm33 = (DefaultTableModel) tablaCoches.getModel();
            tm33.setRowCount(0);
            if (bean.getPiso()>0) {
                Object rowData[] = {bean.getPatente(), bean.getPiso(), dateEntrada.toString(), bean.getTipo_auto()};
                tm33.addRow(rowData);
            }
        }
    }

    private void btnBuscarPisoActionPerformed(java.awt.event.ActionEvent evt) {
        if (!campoPiso.getText().equals("  ")) {
            int piso = Integer.parseInt(campoPiso.getText());
            ArrayList<HistoricoBean> beanes = bdm.obtenerCoche(piso);
            if (beanes.size() > 0) {
                DefaultTableModel tm33 = (DefaultTableModel) tablaCoches.getModel();
                tm33.setRowCount(0);
                for (int i = 0; i < beanes.size(); i++) {
                    HistoricoBean bean = beanes.get(i);
                    Double d2 = bean.getHora_ocupacion();
                    Date dateEntrada = new Date(d2.longValue());
                    Object rowData[] = {bean.getPatente(), bean.getPiso(), dateEntrada.toString(), bean.getTipo_auto()};
                    tm33.addRow(rowData);
                }
            }
        }
    }

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
                      
    private javax.swing.JButton btnBuscarPatente;
    private javax.swing.JButton btnBuscarPiso;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JFormattedTextField campoPatente;
    private javax.swing.JFormattedTextField campoPiso;
    private javax.swing.JLabel labelPatente;
    private javax.swing.JLabel labelPiso;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tablaCoches;    
}
