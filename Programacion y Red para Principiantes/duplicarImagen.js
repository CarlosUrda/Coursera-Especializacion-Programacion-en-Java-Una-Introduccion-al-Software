// write your code here

function duplicarImagen( img)
{
    var alto = img.getHeight();
    var ancho = img.getWidth();
    var imgResult = new SimpleImage( 2*ancho, 2*alto);

    for (var px of img.values())
    {
        for (var i = 0; i < 4; i++)
            imgResult.setPixel( px.getX() + (i%2)*ancho, 
                                px.getY() + Math.floor(i/2)*alto, px);
    }

    return imgResult;
}

var img = new SimpleImage( "drewRobertOrig.png");
print( img);

imgDuplicada = duplicarImagen( img);
print( imgDuplicada);
