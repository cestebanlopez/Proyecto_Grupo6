package com.dansan.frontend;

import com.dansan.database.BDManagement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dansan
 */
public class VentanaInformes extends javax.swing.JDialog {

    BDManagement bdm = new BDManagement();

 
    public VentanaInformes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarTablas();
        this.setVisible(true);
    }

 
    @SuppressWarnings("unchecked")    
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        scrollPaneTabla = new javax.swing.JScrollPane();
        tablaHistorial = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        scrollPaneCocheras = new javax.swing.JScrollPane();
        tablaCocheras = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        scrollPaneTarifas = new javax.swing.JScrollPane();
        tablaTarifas = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaHistorial.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null}
                },
                new String[]{
                    "patente", "tipo de auto", "piso", "hora_entrada", "hora de salida", "importe"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tablaHistorial.setToolTipText("");
        scrollPaneTabla.setViewportView(tablaHistorial);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPaneTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPaneTabla, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Historial", jPanel1);

        tablaCocheras.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "patente", "tipo de auto", "piso", "hora de entrada"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        scrollPaneCocheras.setViewportView(tablaCocheras);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPaneCocheras, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPaneCocheras, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Cocheras", jPanel2);

        tablaTarifas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null}
                },
                new String[]{
                    "Tipo de auto", "Tarifa"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        scrollPaneTarifas.setViewportView(tablaTarifas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPaneTarifas, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPaneTarifas, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Tarifas", jPanel3);

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
                .addComponent(jTabbedPane1)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }            

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void cargarTablas() {
        //carga historial
        ResultSet rs = bdm.listarHistorico();
        DefaultTableModel tm33 = (DefaultTableModel) tablaHistorial.getModel();
        tm33.setRowCount(0);
        try {
            while (rs.next()) {
                Double dOcup = (Double) rs.getObject("hora_ocupacion");                
                Double dSal = (Double) rs.getObject("hora_ocupacion");
                Date dateEntrada = new Date(dOcup.longValue());   
                Date dateSalida = new Date(dSal.longValue());   
                Object rowData[] = {rs.getObject("patente"), rs.getObject("tipo_auto"), rs.getObject("piso"), dateEntrada.toString(), dateSalida.toString(), rs.getObject("importe")};
                tm33.addRow(rowData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentanaInformes.class.getName()).log(Level.SEVERE, null, ex);
        }
        //carga cocheras
        ResultSet rs2 = bdm.listarCocheras();
        DefaultTableModel tm2 = (DefaultTableModel) tablaCocheras.getModel();
        tm2.setRowCount(0);
        try {
            while (rs2.next()) {
                Double d2 = (Double) rs2.getObject("hora_ocupacion");                
                Date dateEntrada = new Date(d2.longValue());                                
                Object rowData2[] = {rs2.getObject("patente"), rs2.getObject("tipo_auto"), rs2.getObject("piso"), dateEntrada.toString()};
                tm2.addRow(rowData2);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentanaInformes.class.getName()).log(Level.SEVERE, null, ex);
        }
        //carga tarifas
        ResultSet rs3 = bdm.listarTarifas();
        DefaultTableModel tm3 = (DefaultTableModel) tablaTarifas.getModel();
        tm3.setRowCount(0);
        try {
            while (rs3.next()) {
                Object rowData[] = {rs3.getObject("tipo_auto"), rs3.getObject("tarifa")};
                tm3.addRow(rowData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentanaInformes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    private javax.swing.JButton btnCerrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JScrollPane scrollPaneCocheras;
    private javax.swing.JScrollPane scrollPaneTabla;
    private javax.swing.JScrollPane scrollPaneTarifas;
    private javax.swing.JTable tablaCocheras;
    private javax.swing.JTable tablaHistorial;
    private javax.swing.JTable tablaTarifas;
}
