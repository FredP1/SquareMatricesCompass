package com.aad.squarematricescompass;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.Settings;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Compass extends AppCompatActivity {

    Integer carImages1[] = {R.drawable.car_east_northeast, R.drawable.car_east_northwest, R.drawable.car_east_southeast,
            R.drawable.car_north_northeast, R.drawable.car_north_northwest, R.drawable.car_north_southeast, R.drawable.car_north_southwest, R.drawable.car_north_west,
            R.drawable.car_northeast_northwest, R.drawable.car_northeast_southeast, R.drawable.car_northeast_southwest, R.drawable.car_northwest_southeast, R.drawable.car_northwest_southwest,
            R.drawable.car_south_east, R.drawable.car_south_northeast, R.drawable.car_south_northwest, R.drawable.car_south_southeast, R.drawable.car_south_southwest,
            R.drawable.car_southeast_southwest,
            R.drawable.car_west_northeast, R.drawable.car_west_northwest, R.drawable.car_west_southwest};

    ArrayList<Integer> carImages = new ArrayList<Integer>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Collections.addAll(carImages,carImages1);

        setContentView(R.layout.activity_compass);

        ImageView cardSlotImage = (ImageView) findViewById(R.id.cardSlot);
        cardSlotImage.setImageDrawable(getDrawable(carImages.get(5)));

        cardSlotImage.setTag(carImages.get(5));

        findViewById(R.id.cardSlot).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option1).setOnDragListener(new dragListener());findViewById(R.id.option1).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option2).setOnDragListener(new dragListener());findViewById(R.id.option2).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option3).setOnDragListener(new dragListener());findViewById(R.id.option3).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option4).setOnDragListener(new dragListener());findViewById(R.id.option4).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option5).setOnDragListener(new dragListener());findViewById(R.id.option5).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option6).setOnDragListener(new dragListener());findViewById(R.id.option6).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option7).setOnDragListener(new dragListener());findViewById(R.id.option7).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option8).setOnDragListener(new dragListener());findViewById(R.id.option8).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option9).setOnDragListener(new dragListener());findViewById(R.id.option9).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option10).setOnDragListener(new dragListener());findViewById(R.id.option10).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option11).setOnDragListener(new dragListener());findViewById(R.id.option11).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option12).setOnDragListener(new dragListener());findViewById(R.id.option12).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option13).setOnDragListener(new dragListener());findViewById(R.id.option13).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option14).setOnDragListener(new dragListener());findViewById(R.id.option14).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option15).setOnDragListener(new dragListener());findViewById(R.id.option15).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option16).setOnDragListener(new dragListener());findViewById(R.id.option16).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.bin).setOnDragListener(new dragListener());


    }

    private final class dragTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                ClipData data = ClipData.newPlainText("", "");
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                view.startDragAndDrop(data, shadowBuilder, view, 0);
                return true;
            }
            return false;
        }
    }

    private final class dragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int action = dragEvent.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if (dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        System.out.println("This can accept data");
                        return true;
                    } else {
                        System.out.println("This cannot accept data");
                    }
                    return false;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    view.invalidate();
                    View v = (View) dragEvent.getLocalState();
                    if ((((ImageView) view).getDrawable()!=null) && ((view.getId() != R.id.bin)))
                    {
                        break;
                    }
                    if (view.getId() == R.id.bin)
                    {
                        //carImages.remove(((ImageView) v).getDrawable());
                        System.out.println(((ImageView) v).getImageAlpha());
                        System.out.println(carImages.get(5));
                        System.out.println(carImages.size());
                    }
                    //ViewGroup viewParentLayout = (ViewGroup) v.getParent();
                    //viewParentLayout.removeView(v);
                    ImageView container = (ImageView) view;
                    container.setImageDrawable(((ImageView) v).getDrawable());
                    ((ImageView) v).setImageDrawable(null);
                    v.setVisibility(View.VISIBLE);
                    if (v.getId() == R.id.cardSlot) {
                        //carImages.remove(((ImageView) v).getDrawable());
                        int random = (int) (Math.random() * carImages.size() + 0);
                        ImageView cardSlotImage = findViewById(R.id.cardSlot);
                        cardSlotImage.setImageDrawable(getDrawable(carImages.get(random)));
                    }
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}
