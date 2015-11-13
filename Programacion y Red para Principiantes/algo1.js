var output = new SimpleImage(256,256);
 
 //Make something here!
for (var px of output.values())
{
    var green = Math.abs( Math.sin( px.getX()*0.05)) * 255;
    var red = Math.abs( Math.sin( px.getY()*0.05)) * 255;
    px.setGreen( (green < 80) ? 0 : green - 80);
    px.setRed( (red < 80) ? 0 : red - 80);
    if (px.getGreen() < 60 && px.getRed() < 60)
        px.setBlue( 255);
}
 
 //print whatever you made
 print(output);