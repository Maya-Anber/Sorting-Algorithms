/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import javax.swing.JOptionPane;

public abstract class AlertGUI {

    public static void showAlert(String str) {
        JOptionPane.showMessageDialog(null, str);
    }

}