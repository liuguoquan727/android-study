package com.liuguoquan.module.tool.imageprocess.color;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

public class ImageHelper {

  /**
   * 颜色效果
   */
  public static Bitmap handleImageEffect(Bitmap bm, float hue, float saturation, float lum) {
    Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bmp);
    Paint paint = new Paint();

    //色调
    ColorMatrix hueMatrix = new ColorMatrix();
    hueMatrix.setRotate(0, hue); //Red
    hueMatrix.setRotate(1, hue); //Green
    hueMatrix.setRotate(2, hue); //Blue

    //饱和度
    ColorMatrix saturationMatrix = new ColorMatrix();
    saturationMatrix.setSaturation(saturation);

    //亮度
    ColorMatrix lumMatrix = new ColorMatrix();
    lumMatrix.setScale(lum, lum, lum, 1);

    ColorMatrix imageMatrix = new ColorMatrix();
    imageMatrix.postConcat(hueMatrix);
    imageMatrix.postConcat(saturationMatrix);
    imageMatrix.postConcat(lumMatrix);

    paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
    canvas.drawBitmap(bm, 0, 0, paint);
    return bmp;
  }

  /**
   * 底片效果
   */
  public static Bitmap handleImageNegative(Bitmap bm) {
    int width = bm.getWidth();
    int height = bm.getHeight();
    int color;
    int r, g, b, a;

    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

    int[] oldPx = new int[width * height];
    int[] newPx = new int[width * height];
    bm.getPixels(oldPx, 0, width, 0, 0, width, height);

    for (int i = 0; i < width * height; i++) {
      color = oldPx[i];
      r = Color.red(color);
      g = Color.green(color);
      b = Color.blue(color);
      a = Color.alpha(color);

      r = 255 - r;
      g = 255 - g;
      b = 255 - b;

      if (r > 255) {
        r = 255;
      } else if (r < 0) {
        r = 0;
      }
      if (g > 255) {
        g = 255;
      } else if (g < 0) {
        g = 0;
      }
      if (b > 255) {
        b = 255;
      } else if (b < 0) {
        b = 0;
      }
      newPx[i] = Color.argb(a, r, g, b);
    }
    bmp.setPixels(newPx, 0, width, 0, 0, width, height);
    return bmp;
  }

  /**
   * 老照片效果
   */
  public static Bitmap handleImagePixelsOldPhoto(Bitmap bm) {
    Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
    int width = bm.getWidth();
    int height = bm.getHeight();
    int color = 0;
    int r, g, b, a, r1, g1, b1;

    int[] oldPx = new int[width * height];
    int[] newPx = new int[width * height];

    bm.getPixels(oldPx, 0, bm.getWidth(), 0, 0, width, height);
    for (int i = 0; i < width * height; i++) {
      color = oldPx[i];
      a = Color.alpha(color);
      r = Color.red(color);
      g = Color.green(color);
      b = Color.blue(color);

      r1 = (int) (0.393 * r + 0.769 * g + 0.189 * b);
      g1 = (int) (0.349 * r + 0.686 * g + 0.168 * b);
      b1 = (int) (0.272 * r + 0.534 * g + 0.131 * b);

      if (r1 > 255) {
        r1 = 255;
      }
      if (g1 > 255) {
        g1 = 255;
      }
      if (b1 > 255) {
        b1 = 255;
      }

      newPx[i] = Color.argb(a, r1, g1, b1);
    }
    bmp.setPixels(newPx, 0, width, 0, 0, width, height);
    return bmp;
  }

  /**
   * 浮雕效果
   */
  public static Bitmap handleImagePixelsRelief(Bitmap bm) {
    Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
    int width = bm.getWidth();
    int height = bm.getHeight();
    int color = 0, colorBefore = 0;
    int a, r, g, b;
    int r1, g1, b1;

    int[] oldPx = new int[width * height];
    int[] newPx = new int[width * height];

    bm.getPixels(oldPx, 0, bm.getWidth(), 0, 0, width, height);
    for (int i = 1; i < width * height; i++) {
      colorBefore = oldPx[i - 1];
      a = Color.alpha(colorBefore);
      r = Color.red(colorBefore);
      g = Color.green(colorBefore);
      b = Color.blue(colorBefore);

      color = oldPx[i];
      r1 = Color.red(color);
      g1 = Color.green(color);
      b1 = Color.blue(color);

      r = (r - r1 + 127);
      g = (g - g1 + 127);
      b = (b - b1 + 127);
      if (r > 255) {
        r = 255;
      }
      if (g > 255) {
        g = 255;
      }
      if (b > 255) {
        b = 255;
      }
      newPx[i] = Color.argb(a, r, g, b);
    }
    bmp.setPixels(newPx, 0, width, 0, 0, width, height);
    return bmp;
  }

  //灰度效果
  public static Bitmap handleImageGrayEffect(Bitmap bitmap) {
    Bitmap bmp =
        Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    float[] colorMatrixValue = new float[] {
        0.33f, 0.59f, 0.11f, 0, 0, 0.33f, 0.59f, 0.11f, 0, 0, 0.33f, 0.59f, 0.11f, 0, 0, 0, 0, 0, 1,
        0
    };
    ColorMatrix colorMatrix = new ColorMatrix();
    colorMatrix.set(colorMatrixValue);

    Canvas canvas = new Canvas(bmp);
    Paint paint = new Paint();
    paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    canvas.drawBitmap(bitmap, 0, 0, paint);
    return bmp;
  }

  //图像反转
  public static Bitmap handleImageReverseEffect(Bitmap bitmap) {
    Bitmap bmp =
        Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    float[] colorMatrixValue = new float[] {
        -1, 0, 0, 1, 1, 0, -1, 0, 1, 1, 0, 0, -1, 1, 1, 0, 0, 0, 1, 0
    };
    ColorMatrix colorMatrix = new ColorMatrix();
    colorMatrix.set(colorMatrixValue);

    Canvas canvas = new Canvas(bmp);
    Paint paint = new Paint();
    paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    canvas.drawBitmap(bitmap, 0, 0, paint);
    return bmp;
  }

  //去色效果
  public static Bitmap handleImageDesaturateEffect(Bitmap bitmap) {
    Bitmap bmp =
        Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    float[] colorMatrixValue = new float[] {
        1.5f, 1.5f, 1.5f, 0, -1, 1.5f, 1.5f, 1.5f, 0, -1, 1.5f, 1.5f, 1.5f, 0, -1, 0, 0, 0, 1, 0
    };
    ColorMatrix colorMatrix = new ColorMatrix();
    colorMatrix.set(colorMatrixValue);

    Canvas canvas = new Canvas(bmp);
    Paint paint = new Paint();
    paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    canvas.drawBitmap(bitmap, 0, 0, paint);
    return bmp;
  }

  //高饱和度效果
  public static Bitmap handleImageHighSaturationEffect(Bitmap bitmap) {
    Bitmap bmp =
        Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    float[] colorMatrixValue = new float[] {
        1.438f, -0.122f, -0.016f, 0, -0.03f, -0.062f, 1.378f, -0.016f, 0, 0.05f, -0.062f, -0.122f,
        1.483f, 0, -0.02f, 0, 0, 0, 1, 0
    };
    ColorMatrix colorMatrix = new ColorMatrix();
    colorMatrix.set(colorMatrixValue);

    Canvas canvas = new Canvas(bmp);
    Paint paint = new Paint();
    paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    canvas.drawBitmap(bitmap, 0, 0, paint);
    return bmp;
  }
}
