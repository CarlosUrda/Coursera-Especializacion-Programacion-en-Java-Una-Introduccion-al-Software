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

function extraerImagen( img)
{
    var imgResult = new SimpleImage( img.getWidth(), img.getHeight());

    for (var px of img.values())
    {
        pxResult = imgResult.getPixel( px.getX(), px.getY());
        pxResult.setRed( (px.getRed() % 16) * 16);
        pxResult.setGreen( (px.getGreen() % 16) * 16);
        pxResult.setBlue( (px.getBlue() % 16) * 16);
    }
    
    return imgResult;
    
}

var imgVisible = new SimpleImage( "drewRobertOrig.png");
var imgOculta = new SimpleImage( "dinos.png");

imgOculta = recortarImagen( imgOculta, imgVisible.getWidth(), imgVisible.getHeight());

print( imgVisible);
print( imgOculta);

var imgEncriptada = ocultarImagen( imgVisible, imgOculta);

print( imgEncriptada);

var imgExtraida = extraerImagen( imgEncriptada);

print( imgExtraida);