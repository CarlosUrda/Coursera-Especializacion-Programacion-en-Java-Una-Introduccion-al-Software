// write your code here
function swapRedGreen( px)
{
    var green = px.getGreen();
    px.setGreen( px.getRed());
    px.setRed( green);
}

var img = new SimpleImage( "smallhands.png");
print( img);
for (var px of img.values())
{
    swapRedGreen( px);
}

print( img)