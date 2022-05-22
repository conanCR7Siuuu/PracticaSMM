/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SM.RGT.imagenes;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 *
 * @author Ramon
 */
public class BordesOp extends BufferedImageOpAdapter {

    private int umbral;
    
    public BordesOp(int umbral){ 
        this.umbral = umbral;
    }

    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        int[] pixelComp = new int[srcRaster.getNumBands()];
        int[] pixelCompDest = new int[srcRaster.getNumBands()];
        int[] pixelActual = new int[srcRaster.getNumBands()];
        int[] pixelIzquierda = new int[srcRaster.getNumBands()]; 
        int[] pixelArriba = new int[srcRaster.getNumBands()];

        for (int x = 1; x < src.getWidth(); x++) {
            for (int y = 1; y < src.getHeight(); y++) {

                srcRaster.getPixel(x, y, pixelComp);
                pixelActual = pixelComp;
                srcRaster.getPixel((x-1), y, pixelIzquierda);
                srcRaster.getPixel(x, y-1, pixelArriba);
                
                int alfaIzquierda;
                alfaIzquierda = Math.abs((pixelActual[0] + pixelActual[1] + pixelActual[2])/3
                        - (pixelIzquierda[0] + pixelIzquierda[1] + pixelIzquierda[2])/3);
                int alfaArriba = Math.abs((pixelActual[0] + pixelActual[1] + pixelActual[2])/3 
                        - (pixelArriba[0] + pixelArriba[1] + pixelArriba[2])/3);
                int alfa = Math.max(alfaIzquierda, alfaArriba);
                
                //System.out.println(alfa+"-"+umbral);
                if (alfa < umbral) {
                    pintarGris(pixelCompDest);
                }else{
                    pixelCompDest[0] = 255;
                    pixelCompDest[1] = 51;
                    pixelCompDest[2] = 153;
                }
                destRaster.setPixel(x, y, pixelCompDest);
            }
        }
        return dest;
    }

    public void pintarGris(int[] pixelComp) {
        int color = pixelComp[0] + pixelComp[1] + pixelComp[2];
        for (int i = 0; i < pixelComp.length; i++) {
            pixelComp[i] = (color) / 3;
        }
    }
    
    public int calculoAlfa(int[] pixelComp){
        int alfa = 1;
        
        return alfa;
    }
}
