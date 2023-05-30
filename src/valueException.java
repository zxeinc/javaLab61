
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class valueException extends Exception{

    public valueException() {
    }

    public valueException(String message) {
        super(message);
        
    }

    public valueException(String message, Throwable cause) {
        super(message, cause);
    }

    public valueException(Throwable cause) {
        super(cause);
    }

    public valueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
}
