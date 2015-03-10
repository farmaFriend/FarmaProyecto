package com.example.ixtilion.proyecto;

import android.graphics.*;
import android.view.View;
import android.content.Context;
import android.util.*;

/**
 * Created by Alumno on 10/03/2015.
 */
public class pastillero_ini extends View {
    private Paint paint;
    public pastillero_ini(Context context){
        super(context);
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
    }
    public pastillero_ini(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
    }
    public void onDraw(Canvas canvas){
        canvas.drawCircle(200,200,200,paint);
    }
}
