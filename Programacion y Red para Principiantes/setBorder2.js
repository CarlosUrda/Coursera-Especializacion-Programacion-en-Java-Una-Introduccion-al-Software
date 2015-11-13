// write your code here
function setBlack( px)
{
    px.setRed( 0);
    px.setGreen( 0);
    px.setBlue( 0);
}

function pixelOnEdge( px, img, width, height)
{
    var x = px.getX();
    var y = px.getY();
    
    if (x < width || y < height ||
        x > (img.getWidth()-width) || y > (img.getHeight()-height))
        return true;
    
    return false;
}

var img = new SimpleImage( "smallhands.png");
print( img);
for (var px of img.values())
{
    if (pixelOnEdge( px, img, 5, 50))
        setBlack( px);
}

print( img)