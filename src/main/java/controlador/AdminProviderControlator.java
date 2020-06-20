/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.Conexion;
import dao.ProviderDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import vista.AdminProvider;
import vista.AdminRole;
import vista.ChooseRole;
import vista.ProviderRegister;
import vista.ProviderRole;

/**
 *
 * @author jabre
 */
public class AdminProviderControlator implements ActionListener {
    
    private AdminProvider providerRole;
    private AdminRole role = new AdminRole();
    private ProviderRole p=new ProviderRole();
    private ProviderRegister a=new ProviderRegister();
    private ProviderDAO provider=new ProviderDAO();
    private int selection;
    private MouseEvent mouse;
    

    public AdminProviderControlator() {
        
    }
    public AdminProviderControlator(AdminProvider user) {
        this.providerRole=user;
    }
    
    
    public void openUserRegister(){
    providerRole.setTitle("Registo Usuario");
    providerRole.setLocationRelativeTo(null);
    provider.getAllProviders(providerRole.tableProve);
    providerRole.setVisible(true);
    
    this.providerRole.btnUpdate.setActionCommand("btnUpdate");
    this.providerRole.btnUpdate.addActionListener(this);
    this.providerRole.buttonDelete.setActionCommand("buttonDelete");
    this.providerRole.buttonDelete.addActionListener(this);
    this.providerRole.buttonDesactivar.setActionCommand("buttonDesactivar");
    this.providerRole.buttonDesactivar.addActionListener(this);
    this.providerRole.buttonInsert.setActionCommand("buttonInsert");
    this.providerRole.buttonInsert.addActionListener(this);
    this.providerRole.buttonBack.setActionCommand("buttonBack");
    this.providerRole.buttonBack.addActionListener(this);
    
    
    }
    
  
 
    

    @Override
    public void actionPerformed(ActionEvent evento) {
       switch(buttons.valueOf(evento.getActionCommand())){
           case buttonDesactivar:
               break;
           case buttonInsert:
               new ProviderRegisterControlator(a).openProviderRegister("a");
               providerRole.setVisible(false);
               break;
           case btnUpdate:
                selection=providerRole.tableProve.getSelectedRow();
                provider.updateProviderInformation(String.valueOf(providerRole.tableProve.getValueAt(selection, 0)), String.valueOf(providerRole.tableProve.getValueAt(selection, 4)), String.valueOf(providerRole.tableProve.getValueAt(selection, 5)), String.valueOf(providerRole.tableProve.getValueAt(selection, 6)), String.valueOf(providerRole.tableProve.getValueAt(selection, 7)), Integer.parseInt(String.valueOf(providerRole.tableProve.getValueAt(selection, 8))), String.valueOf(providerRole.tableProve.getValueAt(selection, 9)), String.valueOf(providerRole.tableProve.getValueAt(selection, 10)), String.valueOf(providerRole.tableProve.getValueAt(selection, 11)), String.valueOf(providerRole.tableProve.getValueAt(selection, 12)), String.valueOf(providerRole.tableProve.getValueAt(selection, 13)));
               break;
           case buttonDelete:
               provider.deleteProviderInformation(String.valueOf(providerRole.tableProve.getValueAt(selection, 0)));
               provider.deleteProvider(String.valueOf(providerRole.tableProve.getValueAt(selection, 0)));
               
               break;
           case buttonBack:
               new AdminRoleControlator(role).openUserRegister();
               providerRole.setVisible(false);
               break;
           
       
       }
    }
    
    
    
    public enum buttons{
    btnUpdate,buttonDesactivar,buttonInsert,buttonBack,buttonDelete
    }   
}
