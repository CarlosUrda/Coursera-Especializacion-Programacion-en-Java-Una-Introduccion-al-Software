// write your code here
var img = new SimpleImage( 200, 200);
var height = img.getHeight();
var width = img.getWidth();

for (var px of img.values())
{
    x = px.getX();
    y = px.getY();
    
    px.setRed( 0);
    px.setGreen( 0);
    px.setBlue( 0);
    
    if (x < width/2)
        px.setRed( 255);
    else if (y < height/2)
        px.setGreen( 255);
        
    if (y > height/2)
        px.setBlue( 255);
}

print( img);