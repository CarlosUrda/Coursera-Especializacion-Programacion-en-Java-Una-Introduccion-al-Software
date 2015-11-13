// write your code here
function dist(pixel, x,y) 
{
    var dx = pixel.getX() - x;
    var dy = pixel.getY() - y;
    return Math.sqrt(dx * dx + dy *dy);
}

function normalize( value, max) 
{
    return Math.abs( value/max);
}
 
//start with a blank image
var output = new SimpleImage(256,256);
 
var width = output.getWidth();
var height = output.getHeight();
var origX = Math.random() * width/2 + width/2;
var origY = Math.random() * height;
var maxDist = Math.sqrt( width*width/4 + height*height)

 //Make something here!
for (var px of output.values())
{
    var x = px.getX();
    var y = px.getY();
    if (x < width/2)
    {
        var green = Math.abs( Math.sin( px.getX()*0.05)) * 255;
        var red = Math.abs( Math.sin( px.getY()*0.05)) * 255;
        px.setGreen( (green < 90) ? 0 : green - 90);
        px.setRed( (red < 90) ? 0 : red - 90);
        if (px.getGreen() < 60 && px.getRed() < 60)
            px.setBlue( 255);
    }
    else
    {
        distance = dist( px, origX, origY);
        distance = normalize( distance, maxDist);

        var distX = Math.abs( x - origX);
        var distY = Math.abs( y - origY);
        angle = normalize( Math.atan( distY / distX), Math.PI/2);
        green = 255 * (angle%(1/4)) * 4;
        red = 255 * ((1-angle)%(1/4)) * 4;
        blue = 255 * Math.random();
        

        px.setGreen( (1-2*distance) * green);
        px.setRed( (1-2*distance) * red);
        px.setBlue( (1-2*distance) * blue);
    }
    
    
}
 
 //print whatever you made
 print(output);