import edu.duke.*;
import java.io.File;

/**
 * Conversión de imágenes seleccionadas a escala de grises.
 * 
 * @author Carlos A. Gómez Urda 
 * @version 1.0
 */
public class BatchGrayscale
{
    /**
     * Convierte una imagen a escala de grises.
     * 
     * @param img Imagen a convertir a escala de grises.
     * @return Imagen transformada a escala de grises. 
     */
    public ImageResource makeGrayscale( ImageResource imgEntrada)
    {
        ImageResource imgSalida = new ImageResource( imgEntrada.getWidth(), imgEntrada.getHeight());
        
        for (Pixel pxEntrada : imgEntrada.pixels())
        {
            Pixel pxSalida = imgSalida.getPixel( pxEntrada.getX(), pxEntrada.getY());
            int media = (pxEntrada.getRed() + pxEntrada.getGreen() + pxEntrada.getBlue()) / 3;
            
            pxSalida.setRed( media);
            pxSalida.setGreen( media);
            pxSalida.setBlue( media);
        }
        
        return imgSalida;
    }
    
    
    /**
     * Selecciona una serie de imágenes para convertirlas a escala de grises.
     * 
     * @param img Imagen a convertir a escala de grises.
     * @return Imagen transformada a escala de grises. 
     */    
    public void selectAndConvert()
    {
        DirectoryResource dResource = new DirectoryResource();
        ImageResource imgGrey, imgOriginal;
        
        for (File file : dResource.selectedFiles())
        {
            imgOriginal = new ImageResource( file);
            imgGrey = makeGrayscale( imgOriginal);
            imgGrey.draw();
            System.out.println( file);
            imgGrey.setFileName( file.getParent()+"/"+"grey-"+imgOriginal.getFileName());
            imgGrey.save();
        }
    }
}
