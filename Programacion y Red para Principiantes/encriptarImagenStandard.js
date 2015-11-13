// write your code here

function crop( img, width, height)
{
    var newImg = new SimpleImage( width, height);
    var x, y;
    
    for (var px of img.values())
    {
        x = px.getX();
        y = px.getY();
        if (x < width && y < height)
            newImg.setPixel( x, y, px);
    }
    
    return newImg;
}

function pixchange( pxValue, valueBits)
{
    return Math.floor( pxValue / valueBits) * valueBits;
}

function chop2Hide( img, bits)
{
    var newImg = new SimpleImage( img.getWidth(), img.getHeight());
    var valueBits = Math.pow( 2, bits);

    for (var px of img.values())
    {
        var x = px.getX();
        var y = px.getY();

        newPx = newImg.getPixel( x, y);
        newPx.setRed( pixchange( px.getRed(), valueBits));
        newPx.setGreen( pixchange( px.getGreen(),valueBits));
        newPx.setBlue( pixchange( px.getBlue(),valueBits));
    }

    return newImg;
}

function shift( img, bits)
{
    var newImg = new SimpleImage( img.getWidth(), img.getHeight());
    var valueBits = Math.pow( 2, 8-bits);

    for (var px of img.values())
    {
        var x = px.getX();
        var y = px.getY();

        newPx = newImg.getPixel( x, y);
        newPx.setRed( Math.floor( px.getRed() / valueBits));
        newPx.setGreen( Math.floor( px.getGreen() / valueBits));
        newPx.setBlue( Math.floor( px.getBlue() / valueBits));
    }

    return newImg;
}

function newpv( p, v)
{
    var sum = p + v;
    if (sum > 255)
    {
        print( "ERROR: Valor de pixel inválido " + p + " " + v);
        sum = 255;
    }
    
    return sum;
}

function combine( start, hide)
{
    var combined = new SimpleImage( start.getWidth(), start.getHeight());
    
    for (var startPx of start.values())
    {
        var x = startPx.getX();
        var y = startPx.getY();
        
        combPx = combined.getPixel( x, y);
        hidePx = hide.getPixel( x, y);
        combPx.setRed( newpv( startPx.getRed(), hidePx.getRed()));
        combPx.setGreen( newpv( startPx.getGreen(), hidePx.getGreen()));
        combPx.setBlue( newpv( startPx.getBlue(), hidePx.getBlue()));
    }

    return combined;
}


function extract( img, bits)
{
    var imgResult = new SimpleImage( img.getWidth(), img.getHeight());
    var valueBitsStart = Math.pow( 2, 8-bits);
    var valueBitsHide = Math.pow( 2, bits);

    for (var px of img.values())
    {
        pxResult = imgResult.getPixel( px.getX(), px.getY());
        pxResult.setRed( (px.getRed() % valueBitsHide) * valueBitsStart);
        pxResult.setGreen( (px.getGreen() % valueBitsHide) * valueBitsStart);
        pxResult.setBlue( (px.getBlue() % valueBitsHide) * valueBitsStart);
    }
    
    return imgResult;
    
}

var start = new SimpleImage( "palm-and-beach.png");
var hide = new SimpleImage( "chapel.png");
start = crop( start, 200, 300);
hide = crop( hide, 200, 300);
print( start);
print( hide);
var bits = 2;   // Bits to contain the hidden image.

start = chop2Hide( start, bits);
hide = shift( hide, bits);
var combined = combine( start, hide);
print( combined);

hide = extract( combined, bits);
print( hide);