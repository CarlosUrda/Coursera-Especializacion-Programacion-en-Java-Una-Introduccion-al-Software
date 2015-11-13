import edu.duke.*;
import java.io.File;

/**
 * Conversión de imágenes seleccionadas a negativo.
 * 
 * @author Carlos A. Gómez Urda 
 * @version 1.0
 */
public class BatchInversion
{
    /**
     * Convierte una imagen a negativo.
     * 
     * @param img Imagen a convertir a negativo.
     * @return Imagen transformada a negativo. 
     */
    public ImageResource makeInversion( ImageResource imgEntrada)
    {
        ImageResource imgSalida = new ImageResource( imgEntrada.getWidth(), imgEntrada.getHeight());
        
        for (Pixel pxEntrada : imgEntrada.pixels())
        {
            Pixel pxSalida = imgSalida.getPixel( pxEntrada.getX(), pxEntrada.getY());
            
            pxSalida.setRed( 255-pxEntrada.getRed());
            pxSalida.setGreen( 255-pxEntrada.getGreen());
            pxSalida.setBlue( 255-pxEntrada.getBlue());
        }
        
        return imgSalida;
    }
    
    
    /**
     * Selecciona una serie de imágenes para convertirlas a negativo.
     * 
     * @param img Imagen a convertir a negativo.
     * @return Imagen transformada a negativo. 
     */    
    public void selectAndConvert()
    {
        DirectoryResource dResource = new DirectoryResource();
        ImageResource imgOriginal, imgInverted;
        
        for (File file : dResource.selectedFiles())
        {
            imgOriginal = new ImageResource( file);
            imgInverted = makeInversion( imgOriginal);
            imgInverted.draw();
            imgInverted.setFileName( file.getParent()+"/"+"inverted-"+imgOriginal.getFileName());
            imgInverted.save();
        }
    }
}
