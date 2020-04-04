
package presentacion;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Controlador implements MouseListener{
    
    private final VistaPrincipal ventana;
    private JLabel etiqueta ;
    JButton boton;
    String texto;
    int c,k;
    
    public Controlador(VistaPrincipal aThis) {
        ventana = aThis;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        ventana.getModelo().calcularSudoku();
        if(e.getSource()instanceof JLabel){ //Se revisa el tipo, si es JLabel
            etiqueta =(JLabel) e.getSource();
            int i,j;
            for(i=0;i<9;i++){
                for(j=0;j<9;j++){
                    if(etiqueta== ventana.getLblCajon(i,j)){
                        c=i;
                        k=j;  
                    }
                }
            }
        }
        if(e.getSource()instanceof JButton){ // Se revisa si es JButton
            boton =(JButton) e.getSource();  
            if(boton==ventana.getBtn1()){
                texto=ventana.getBtn1().getText();
                ventana.getModelo().llenarTablero(texto,c,k);
            }else{
                if(boton==ventana.getBtn2()){
                    texto=ventana.getBtn2().getText();
                    ventana.getModelo().llenarTablero(texto,c,k);
                }else{
                    if(boton==ventana.getBtn3()){
                        texto=ventana.getBtn3().getText();
                        ventana.getModelo().llenarTablero(texto,c,k);
                    }else{
                        if(boton==ventana.getBtn4()){
                            texto=ventana.getBtn4().getText();
                            ventana.getModelo().llenarTablero(texto,c,k);
                        }else{
                            if(boton==ventana.getBtn5()){
                                texto=ventana.getBtn5().getText();
                                ventana.getModelo().llenarTablero(texto,c,k);
                            }else{
                                if(boton==ventana.getBtn6()){
                                    texto=ventana.getBtn6().getText();
                                    ventana.getModelo().llenarTablero(texto,c,k);
                                }else{
                                    if(boton==ventana.getBtn7()){
                                        texto=ventana.getBtn7().getText();
                                        ventana.getModelo().llenarTablero(texto,c,k);
                                    }else{
                                        if(boton==ventana.getBtn8()){
                                            texto=ventana.getBtn8().getText();
                                            ventana.getModelo().llenarTablero(texto,c,k);
                                        }else{
                                            if(boton==ventana.getBtn9()){
                                                texto=ventana.getBtn9().getText();
                                                ventana.getModelo().llenarTablero(texto,c,k);
                                            }  
                                        } 
                                    }
                                }                  
                            }
                        }
                    }
                }         
            }
        }
    }
    

    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) { 
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        ventana.getModelo().calcularSudoku();
    }
    @Override
    public void mouseExited(MouseEvent e) {
        ventana.getModelo().calcularSudoku();
    }

}
