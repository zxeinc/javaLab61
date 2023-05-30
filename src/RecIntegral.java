/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JOptionPane;
import java.io.Serializable;
/**
 *
 * @author user
 */
public class RecIntegral implements Serializable {
    public double start;
    public double end;
    public double step;
    public double result ;

    public RecIntegral(){

    }

    public RecIntegral(double _start, double _end, double _step) throws valueException{
           
         if(CheckMaxMin(_start, 0.000001, 1000000)==false ||
           CheckMaxMin(_end, 0.000001, 1000000)==false ||
           CheckMaxMin(_step, 0.000001, 1000000)==false)   
         {
             JOptionPane.showMessageDialog(null, "missing range", "Error", JOptionPane.ERROR_MESSAGE);
             throw new valueException("missing range"); 
         } 
         else if((_step > _start - _end)){
             JOptionPane.showMessageDialog(null, "step can't be higher than interval", "Error", JOptionPane.ERROR_MESSAGE);
             throw new valueException("step can't be higher than interval"); 
         } 
         else if (_start < _end){
             JOptionPane.showMessageDialog(null, "end can't be higher than start", "Error", JOptionPane.ERROR_MESSAGE);
             throw new valueException("end can't be higher than start"); 
         } 
         else{
             start = _start;
             end = _end;
             step = _step;
         }
    }
    private boolean CheckMaxMin(double number, double min, double max){
        if(number > min && number < max){
            return true;
        }
        return false;

    }
}
