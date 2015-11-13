//start with a blank image
var input = new SimpleImage("rodger.png");
var width = input.getWidth();
var height = input.getHeight();
var output = new SimpleImage( width, height);
var waveAmp = 50;   // Wave's amplitude
var waveLen = 0.1;  // Wave's length

//Make something here!
for (var px of input.values())
{
  var x = px.getX();
  var y = px.getY();

  var altX = (x + Math.sin( y*waveLen)*waveAmp + width) % width;
  var altY = y;
  var altPx = input.getPixel( altX, altY);

  output.setPixel( x, y, altPx);
}

//print whatever you made
print(output);