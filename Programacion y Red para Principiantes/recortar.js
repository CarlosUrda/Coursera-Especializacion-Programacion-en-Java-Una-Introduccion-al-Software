// write your code here

var imgVisible = new SimpleImage( "drewRobertOrig.png");
var imgOculta = new SimpleImage( "dinos.png");

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

print( imgVisible);
print( recortarImagen( imgVisible, 150, 150));
