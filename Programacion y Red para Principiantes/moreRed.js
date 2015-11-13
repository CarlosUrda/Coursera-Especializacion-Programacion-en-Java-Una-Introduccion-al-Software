// write your code here
function moreRed( px, redInc)
{
    var newRed = px.getRed() + redInc;
    if (newRed > 255)
        newRed = 255;
    px.setRed( newRed);
}

var img = new SimpleImage( "smallhands.png");
print( img);
for (var px of img.values())
{
    moreRed( px, 150);
}

print( img)