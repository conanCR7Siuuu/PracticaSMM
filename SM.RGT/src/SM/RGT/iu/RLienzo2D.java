/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package SM.RGT.iu;

import SM.RGT.graficos.RLinea2D;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.QuadCurve;

/**
 *
 * @author Ramon
 */
public class RLienzo2D extends javax.swing.JPanel {

    /**
     * Creates new form RLienzo2D
     */
    public RLienzo2D() {
        initComponents();
        vShape = new ArrayList();
    }
    
    private Point2D punto = null;
    private Color color = Color.BLACK;
    private boolean relleno = false;
    private Herramientas hDibujo = Herramientas.TRAZO;
    private Stroke stroke = new BasicStroke();
    private boolean mover = false;
    List<Shape> vShape = new ArrayList();
    private boolean transparencia = false;
    private boolean alisar = false;
    Shape seleccionado = null;
    Line2D linea = null;
    Ellipse2D elipse= null;
    
    private int coordenadaX = 0;
    private int coordenadaY = 0;
    
    BufferedImage image = null;

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(int coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(int coordenadaY) {
        this.coordenadaY = coordenadaY;
    }
    
    
    
    public void setMover(Boolean b){
        mover = b;
    }

    public List<Shape> getvShape() {
        return vShape;
    }

    public void setvShape(List<Shape> vShape) {
        this.vShape = vShape;
    }
    
    
    public Stroke getTrazo(){
        return stroke;
    }

    public BufferedImage getImage() {
        return image;
    }
    
    public BufferedImage getImage(boolean drawVector) {
        if (drawVector) {
            BufferedImage imgout = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
            Graphics2D g2dImagen = imgout.createGraphics();
            this.paint(imgout.createGraphics());
            return (imgout);
        }
        return image;
    }
    
    public void setImage(BufferedImage image){
        this.image = image;
        if(image!=null){
            setPreferredSize (new Dimension(image.getWidth(), image.getHeight()));
        }
    }
    
    public void setTrazo(Stroke s){
        stroke = s;
    }
    public Boolean getMover(){
        return mover;
    }
    
    public void setColor(Color c){
        this.color = c;
    }

    public Color getColor() {
        return color;
    }

    public boolean getRelleno() {
        return relleno;
    }

    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    public void sethDibujo(Herramientas hDibujo) {
        this.hDibujo = hDibujo;
    }
    
     @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if(image != null) {
            g2d.drawImage(image, 0, 0,this);
        }
        g2d.setPaint(color);
        g2d.setStroke(stroke);
        for (Shape s : vShape) {
            if (relleno) {
                g2d.fill(s);
            }
            if (transparencia){
                Composite comp;
                comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
                g2d.setComposite(comp);
            }
            if (alisar){
                 RenderingHints render;
                 render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
                 g2d.setRenderingHints(render);
            }

            
            g2d.draw(s);
        }
    }
    

    private Shape getSelectedShape(Point2D p) {
        for (Shape s : vShape) {
            
            if (s.contains(p)) {
                return s;
            }
            
            
        }
        return null;
}
    
    private void moverS(Point2D p){
        if (seleccionado instanceof RectangularShape){
            ((Rectangle) seleccionado).setLocation((Point) p);
        }else if (seleccionado instanceof RLinea2D){
            ((RLinea2D)linea).setLocation(p);
        }else if (seleccionado instanceof Ellipse2D){
            //((Ellipse2D)elipse).setLocation(p);
        }
            
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setForeground(new java.awt.Color(255, 255, 255));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        
        
        if(mover){
            seleccionado = this.getSelectedShape(evt.getPoint());
        }else{
            punto = evt.getPoint();
        switch(hDibujo){
            
            case LINEA: 
                    linea = new RLinea2D(punto, punto);
                    vShape.add(linea);
                
                break;
            case RECTANGULO:                         
                        Rectangle rectangulo = new Rectangle((Point) punto);
                        vShape.add(rectangulo);
                        
                break;
            case ELIPSE: 
                        elipse = new Ellipse2D.Double(punto.getX(),punto.getY(),1,1);
                        vShape.add(elipse);
                break;
            case TRAZO:
                    Path2D trazo = new Path2D.Float();
                    trazo.moveTo(punto.getX(), punto.getY());
                    vShape.add(trazo);
                    
                break;
            case CURVA:
                    QuadCurve arco = new QuadCurve();
                    //vShape.add(arco);
        }
        }
        
        
        
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        
        
        if(mover){
            this.moverS(evt.getPoint());
        }else{
            Shape s = vShape.get(vShape.size() - 1);
          if (s instanceof RLinea2D)
            ((RLinea2D) s).setLine(punto, evt.getPoint());
        else if (s instanceof RectangularShape)
            ((RectangularShape) s).setFrameFromDiagonal(punto, evt.getPoint());
        else if (s instanceof Ellipse2D)
            ((Ellipse2D) s).setFrameFromDiagonal(punto, evt.getPoint());
        else if (s instanceof Path2D)
            ((Path2D) s).lineTo(evt.getPoint().getX(), evt.getPoint().getY());
        this.repaint();  
        }
        
        this.repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        this.coordenadaX = evt.getX();
        this.coordenadaY = evt.getY();
    }//GEN-LAST:event_formMouseMoved

    public void setTransparencia(boolean selected) {
        transparencia = selected;
    }

    public void setAlisar(boolean selected) {
        alisar = selected;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
