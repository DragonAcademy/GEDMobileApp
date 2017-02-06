/*
 * Bucket.java
 *
 * Bucket character for student to move
 *
 * Bucket character
 *
 * https://www.simplifiedcoding.net/android-game-development-tutorial-1/
 *
 * Worked on by:
 * Myanna Harris
 * Kristina Spring
 * Jasmine Jans
 * Jimmy Sherman
 *
 * Last Edit: 2-6-17
 *
 */

package com.gedappgui.gedappgui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;

public class Bucket {

    //Bitmap to get character from image
    private Bitmap bitmap;

    //coordinates
    private int x;
    private int y;

    //motion speed of the character
    private int speed = 0;

    //screen boundaries
    private int minY;
    private int maxY;
    private int minX;
    private int maxX;

    // For moving
    private int startX;
    private int dx;

    private Rect detectCollision;

    private int width;
    private int height;

    //constructor
    public Bucket(Context context, int widthp, int heightp, int questionHeight) {
        x = 0;
        y = 0;
        speed = 0;
        width = widthp;
        height = heightp;

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.example_picture);
        bitmap = Bitmap.createScaledBitmap(bitmap, (int)(questionHeight * 1.5),
                (int)(questionHeight * 1.5), false);

        //calculating maxY
        maxY = height - bitmap.getHeight();
        if (Build.VERSION.SDK_INT < 19) {
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                maxY = maxY - resources.getDimensionPixelSize(resourceId);
            }
        }

        //top edge's y point is 0 so min y will always be zero
        minY = 0;

        //calculating maxY
        maxX = width - bitmap.getWidth();

        //top edge's y point is 0 so min y will always be zero
        minX = 0;

        x = maxX / 2;
        y = maxY;

        //initializing rect object
        detectCollision =  new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void startMoveBucket(int newX) {
        startX = newX;
    }

    public void stopMoveBucket(int newX) {
        dx = startX - newX;
    }

    //Method to update coordinate of character
    public void update(){
        //updating x coordinate
        if (x - dx > maxX) {
            x = maxX;
            dx = 0;
            startX = x;
        } else if (x - dx < minX) {
            x = minX;
            dx = 0;
            startX = x;
        } else {
            x = x - dx;
            dx = 0;
            startX = x;
        }

        //adding top, left, bottom and right to the rect object
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }

    //one more getter for getting the rect object
    public Rect getDetectCollision() {
        return detectCollision;
    }

    /*
    * These are getters
    * */
    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}
