// write your code here
function setBlack( px)
{
    px.setRed( 0);
    px.setGreen( 0);
    px.setBlue( 0);
}

function pixelOnEdge( px, img, width)
{
    var x = px.getX();
    var y = px.getY();
    
    if (x < width || y < width ||
        x > (img.getWidth()-width) || y > (img.getHeight()-width))
        return true;
    
    return false
    
}

var img = new SimpleImage( "smallhands.png");
print( img);
for (var px of img.values())
{
    if (pixelOnEdge( px, img, 13))
        setBlack( px);
}

print( img)