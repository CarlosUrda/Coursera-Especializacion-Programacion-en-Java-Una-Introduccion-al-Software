// write your code here

function recortarImagen( img, ancho, alto)
{
    var imgNueva = new SimpleImage( ancho, alto);
    var x, y;
    
    for (var px of img.values())
    {
        x = px.getX();
        y = px.getY();
        if (x < ancho && y < alto)
            imgNueva.setPixel( x, y, px);
    }
    
    return imgNueva;
}


function ocultarImagen( imgVisible, imgOculta)
{
    var imgResult = new SimpleImage( imgVisible.getWidth(), imgVisible.getHeight());
    var x, y;
    
    for (var pxVisible of imgVisible.values())
    {
        x = pxVisible.getX();
        y = pxVisible.getY();
        
        rojo = Math.floor( pxVisible.getRed() / 16) * 16;
        verde = Math.floor( pxVisible.getGreen() / 16) * 16;
        azul = Math.floor( pxVisible.getBlue() / 16) * 16;
        
        pxOculto = imgOculta.getPixel( x, y);
        rojo += Math.floor( pxOculto.getRed() / 16);
        verde += Math.floor( pxOculto.getGreen() / 16);
        azul += Math.floor( pxOculto.getBlue() / 16);
        
        pxResult = imgResult.getPixel( x, y);
        pxResult.setRed( rojo);
        pxResult.setGreen( verde);
        pxResult.setBlue( azul);
    }
    
    return imgResult;
}


var imgVisible = new SimpleImage( "drewRobertOrig.png");
var imgOculta = new SimpleImage( "dinos.png");

print("Las dimensiones de la imagen visible son: " + imgVisible.getWidth() + " " + imgVisible.getHeight());
print("Las dimensiones de la imagen oculta son: " + imgOculta.getWidth() + " " + imgOculta.getHeight());
print( imgVisible);
print( imgOculta);

imgOculta = recortarImagen( imgOculta, imgVisible.getWidth(), imgVisible.getHeight());
print("Las dimensiones de la nueva imagen visible son: " + imgVisible.getWidth() + " " + imgVisible.getHeight());
print("Las dimensiones de la nueva imagen oculta son: " + imgOculta.getWidth() + " " + imgOculta.getHeight());
print( imgVisible);
print( imgOculta);

var imgEncriptada = ocultarImagen( imgVisible, imgOculta);
print("Las dimensiones de la imagen encriptada son: " + imgEncriptada.getWidth() + " " + imgEncriptada.getHeight());
print( imgEncriptada);