package com.example.androidcamera;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.os.Bundle;
import android.view.View;

public class FaceDetection extends Activity {

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(new myView(this));
 }

 private class myView extends View {

  private int imageWidth, imageHeight;
  private int numberOfFace = 5;
  private FaceDetector myFaceDetect;
  private FaceDetector.Face[] myFace;
  float myEyesDistance;
  int numberOfFaceDetected;

  Bitmap myBitmap;

  public myView(Context context) {
   super(context);

   BitmapFactory.Options BitmapFactoryOptionsbfo = new BitmapFactory.Options();
   BitmapFactoryOptionsbfo.inPreferredConfig = Bitmap.Config.RGB_565;
   myBitmap = BitmapFactory.decodeResource(getResources(),
     R.drawable.jennifer_lopez, BitmapFactoryOptionsbfo);
   imageWidth = myBitmap.getWidth();
   imageHeight = myBitmap.getHeight();
   myFace = new FaceDetector.Face[numberOfFace];
   myFaceDetect = new FaceDetector(imageWidth, imageHeight,
     numberOfFace);
   numberOfFaceDetected = myFaceDetect.findFaces(myBitmap, myFace);

  }

  @Override
  protected void onDraw(Canvas canvas) {

   canvas.drawBitmap(myBitmap, 0, 0, null);

   Paint myPaint = new Paint();
   myPaint.setColor(Color.GREEN);
   myPaint.setStyle(Paint.Style.STROKE);
   myPaint.setStrokeWidth(3);

   for (int i = 0; i < numberOfFaceDetected; i++) {
    Face face = myFace[i];
    PointF myMidPoint = new PointF();
    face.getMidPoint(myMidPoint);    
    myEyesDistance = face.eyesDistance();
    int x = (int) (myMidPoint.x - myEyesDistance);
    int y = (int) (myMidPoint.y - myEyesDistance);
    int width = (int) (myMidPoint.x + myEyesDistance);
    int height = (int) (myMidPoint.y + myEyesDistance);
    
    myPaint.setColor(Color.RED);
    myPaint.setAlpha(100);
    canvas.drawCircle(myMidPoint.x, myMidPoint.y, face.eyesDistance()*2,
                    myPaint);
    
    /*canvas.drawRect((int) (myMidPoint.x - myEyesDistance),
      (int) (myMidPoint.y - myEyesDistance),
      (int) (myMidPoint.x + myEyesDistance),
      (int) (myMidPoint.y + myEyesDistance), myPaint);*/
    
    //Bitmap newBitmap = Bitmap.createBitmap(myBitmap, x, y, width, height, null, false);
   }
  }
 }

}
