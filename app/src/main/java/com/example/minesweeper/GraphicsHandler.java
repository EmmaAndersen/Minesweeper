package com.example.minesweeper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.DrawableRes;

public class GraphicsHandler extends View {

    // variable of type String
    public Board board;

    private Bitmap BOMBmap;
    private Bitmap EIGHTmap;
    private Bitmap SEVENmap;
    private Bitmap SIXmap;
    private Bitmap FIVEmap;
    private Bitmap FOURmap;
    private Bitmap THREEmap;
    private Bitmap TWOmap;
    private Bitmap ONEmap;
    private Bitmap EMPTYmap;
    private Bitmap HIDDENmap;
    private Bitmap FLAGmap;
    private Paint paint;
    int x = 10;
    int y = 10;

    private Node node;

    private ScaleGestureDetector scaleGestureDetector;
    private float scaleFactor = 1.0f;

    public GraphicsHandler(Context context, Node node){
        super(context);
        Initialize(context);
    }

    public GraphicsHandler(Context context, AttributeSet attributeSet)
    {
        super(context,attributeSet);
        Initialize(context);
    }

    private void Initialize(Context context){
        BOMBmap = BitmapFactory.decodeResource(getResources(),R.mipmap.bomb1);
        EIGHTmap = BitmapFactory.decodeResource(getResources(),R.mipmap.eight1);
        SEVENmap = BitmapFactory.decodeResource(getResources(),R.mipmap.seven1);
        SIXmap = BitmapFactory.decodeResource(getResources(),R.mipmap.six1);
        FIVEmap = BitmapFactory.decodeResource(getResources(),R.mipmap.five1);
        FOURmap = BitmapFactory.decodeResource(getResources(),R.mipmap.four1);
        THREEmap = BitmapFactory.decodeResource(getResources(),R.mipmap.three1);
        TWOmap = BitmapFactory.decodeResource(getResources(),R.mipmap.two1);
        ONEmap = BitmapFactory.decodeResource(getResources(),R.mipmap.one1);
        EMPTYmap = BitmapFactory.decodeResource(getResources(),R.mipmap.empty1);
        HIDDENmap = BitmapFactory.decodeResource(getResources(),R.mipmap.hidden1);
        FLAGmap = BitmapFactory.decodeResource(getResources(),R.mipmap.flag1);
        paint = new Paint();
        paint.setARGB(0,0,0,0);

        this.node = node;
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();

            //preventing the object to get too large or too small.
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));

            invalidate();
            return true;
        }
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void updateNodeTexture(Node node, Canvas canvas)
    {
        //set graphic
        if(node.isFlagged)
        {
            canvas.drawRect(x*node.posX,y*node.posY,x*node.posX-10,y*node.posY-10,paint);  // + eventuella offsets for visuals?
            canvas.drawBitmap(FLAGmap, x*node.posX, y*node.posY, paint);
            //add graphic swap
            return;
        }
        if(node.isHidden)
        {
            canvas.drawRect(x*node.posX,y*node.posY,x*node.posX-10,y*node.posY-10,paint);  // + eventuella offsets for visuals?
            canvas.drawBitmap(HIDDENmap, x*node.posX, y*node.posY, paint);
            //add graphic swap
            return;
        }
        canvas.drawRect(x*node.posX,y*node.posY,x*node.posX-10,y*node.posY-10,paint);  // + eventuella offsets for visuals?
        //canvas.drawBitmap(EMPTYmap, x, y, paint);


        //Drawable.createFromPath("res/drawable/"+node.nodeContains.name()
        //change graphic to node.contains

        //node.nodeContains.name()+"map"

    }

    public void NodeTexturesUpdate(Canvas canvas, Node node)
    {
        switch(node.nodeContains) {
            case EMPTY:
                // code block
                canvas.drawBitmap(EMPTYmap, x*node.posX, y*node.posY, paint);
                break;
            case ONE:
                // code block
                canvas.drawBitmap(ONEmap, x*node.posX, y*node.posY, paint);
                break;
            case TWO:
                // code block
                canvas.drawBitmap(TWOmap, x*node.posX, y*node.posY, paint);
                break;
            case THREE:
                // code block
                canvas.drawBitmap(THREEmap, x*node.posX, y*node.posY, paint);
                break;
            case FOUR:
                // code block
                canvas.drawBitmap(FOURmap, x*node.posX, y*node.posY, paint);
                break;
            case FIVE:
                // code block
                canvas.drawBitmap(FIVEmap, x*node.posX, y*node.posY, paint);
                break;
            case SIX:
                // code block
                canvas.drawBitmap(SIXmap, x*node.posX, y*node.posY, paint);
                break;
            case SEVEN:
                // code block
                canvas.drawBitmap(SEVENmap, x*node.posX, y*node.posY, paint);
                break;
            case EIGHT:
                // code block
                canvas.drawBitmap(EIGHTmap, x*node.posX, y*node.posY, paint);
                break;
            case BOMB:
                // code block
                canvas.drawBitmap(BOMBmap, x*node.posX, y*node.posY, paint);
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        updateNodeTexture(node,canvas);
        NodeTexturesUpdate(canvas,node);

        Log.d("graphics", "hej");

        canvas.drawColor(Color.BLUE);
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        p.setStrokeWidth(20);
        canvas.drawLine(20, 0, 20, canvas.getHeight(), p);
    }
}
