// write your code here
var imgFrente = new SimpleImage( "drewRobert.png");
var imgFondo = new SimpleImage( "dinos.png");

for (var px of imgFrente.values())
{
    if (px.getGreen() == 255 && px.getBlue() === 0 && px.getRed() === 0)
        px.setAllFrom( imgFondo.getPixel( px.getX(), px.getY()));
}

print( imgFrente);