// write your code here
var img = new SimpleImage( 600, 200);
var height = img.getHeight();
var width = img.getWidth();
var rel = height/width;

for (var px of img.values())
{
    x = px.getX();
    y = px.getY();
    
    px.setBlue( 0);
    px.setGreen( 0);
    
    limY = rel*x;

    if (x < 20 && limY < y && y < (height-limY))
        px.setRed( 0);
    else
        px.setRed( 255);
}

print( img);