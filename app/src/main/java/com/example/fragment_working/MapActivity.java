package com.example.fragment_working;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    private Matrix matrix = new Matrix();
    private float mScale = 1f;     //масштаб изображения
    private float mLastTouchY;
    private float mLastTouchX;

    public float[] matrixValues = new float[9];

    private ScaleGestureDetector mScaleGestureDetector;    //слушатель жестов для увеличения, перемещения по карте/картинке
    private GestureDetector gestureDetector;
    ImageView imageView;
    float currentTranslateX;
    float currentTranslateY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        imageView = findViewById(R.id.image_view);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map);   //создаем картинку из ресурсов

        Display display = getWindowManager().getDefaultDisplay();   //получаем параметры экрана девайса
        Point size = new Point();                                   //класс для получения пикселей
        display.getSize(size);                                      //получаем размеры девайса
        int screenWidth = size.x;   //высота
        int screenHeight = size.y;  //ширина

        float scaleFactorX = (float) screenWidth / bitmap.getWidth();
        float scaleFactorY = (float) screenHeight / bitmap.getHeight();

        matrix.postScale(scaleFactorX, scaleFactorY);     //создаем матрицу с пропорцией

        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),  //создаем картинку под размеры экрана девайса
                bitmap.getHeight(), matrix, true);

        imageView.setImageBitmap(scaledBitmap);   //подгружаем измененную картинку



        gestureDetector = new GestureDetector(this, new GestureListener());
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.
                SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {

                float scale = 1 - detector.getScaleFactor();
                float prevScale = mScale;
                mScale += scale;

                if (mScale > 1f)  //если картинка больше экрана, то уменьшить под размеры экрана
                    mScale = 1f;


                ScaleAnimation scaleAnimation = new ScaleAnimation(1f / prevScale,
                        1f / mScale, 1f / prevScale, 1f / mScale,
                        detector.getFocusX(), detector.getFocusY());
                scaleAnimation.setDuration(0);
                scaleAnimation.setFillAfter(true);
                imageView.startAnimation(scaleAnimation);   //назначаем анимацию изображению

                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {   //передаем касания в слушатель (без этого экран не будет реагировать на касания)
        return gestureDetector.onTouchEvent(event); //|| super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);

        mScaleGestureDetector.onTouchEvent(ev);
        gestureDetector.onTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return true;
        }
                //МЕТОД ДЛЯ СКРОЛЛИНГА:
        @Override
        public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
            float diffX = (e2.getX() - e1.getX())/2;
            float diffY = (e2.getY() - e1.getY())/2;
            //   currentTranslateX = e1.getX();
            // currentTranslateY = e1.getY();

            Log.d("Координаты e2: ", " " + e2.getX() + " " + e2.getY());
            Log.d("Координаты e1: ", " " + e1.getX() + " " + e1.getY());
            float newTranslateX =  currentTranslateX + diffX;
            float newTranslateY =  currentTranslateY + diffY;
            Log.d("Координаты y: ", " " + currentTranslateY + " " + diffY);
            Log.d("Координаты x: ", " " + currentTranslateX + " " + diffX);

            // if(Math.abs(diffX)> 150 || Math.abs(diffY)>150) {
            ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(MapActivity.this, "translateX", currentTranslateX, newTranslateX);
            ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(MapActivity.this, "translateY", currentTranslateY, newTranslateY);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(translateXAnimator, translateYAnimator);
            animatorSet.setDuration(300);
            animatorSet.setInterpolator(new DecelerateInterpolator());

            animatorSet.start();
            //   }
            return true;
        }


    }


    public void setTranslateX(float translateX) {
        currentTranslateX = translateX;
        matrix.postTranslate(translateX, currentTranslateY);
        imageView.setImageMatrix(matrix);
    }

    public void setTranslateY(float translateY) {
        currentTranslateY = translateY;
        matrix.setTranslate(currentTranslateX, translateY);
        imageView.setImageMatrix(matrix);
    }
}