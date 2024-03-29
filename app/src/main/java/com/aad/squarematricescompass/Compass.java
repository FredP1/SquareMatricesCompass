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
import android.util.Pair;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
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
            R.drawable.car_west_northeast, R.drawable.car_west_northwest, R.drawable.car_west_southwest,
            R.drawable.careastsouthwest, R.drawable.careastwest, R.drawable.carnortheast, R.drawable.carnorthsouth, R.drawable.carsouthwest, R.drawable.carwestsoutheast};



    int[] boardArray = {R.id.option1,R.id.option2,R.id.option3,R.id.option4,R.id.option5,R.id.option6,R.id.option7,R.id.option8,R.id.option9,R.id.option10,R.id.option11,R.id.option12,R.id.option13,R.id.option14,R.id.option15,R.id.option16};

    ArrayList<Integer> carImages = new ArrayList<>();
    ArrayList<Integer> binImages = new ArrayList<>();
    ArrayList<boardCoordinates> coordinatesArray = new ArrayList<>();
    ArrayList<roundaboutTags> rtArray = new ArrayList<>();
    boolean boardGenerated = false;
    //timer.start();
    //timesUp = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Collections.addAll(carImages,carImages1);
        setContentView(R.layout.activity_compass);

        addDirectionsToDrawables();
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
        findViewById(R.id.bin).setOnDragListener(new dragListener());findViewById(R.id.bin).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.placeholder1).setOnDragListener(new dragListener());findViewById(R.id.placeholder1).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.placeholder2).setOnDragListener(new dragListener());findViewById(R.id.placeholder2).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.placeholder3).setOnDragListener(new dragListener());findViewById(R.id.placeholder3).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.placeholder4).setOnDragListener(new dragListener());findViewById(R.id.placeholder4).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.finishButton).setOnTouchListener(new dragTouchListener());

    }

    private void generateBoardArrayCoordinates()
    {
        int[] imgCoordinates = new int[2];
        for (int i =0; i < boardArray.length; i++)
        {
            boardCoordinates coordinates = new boardCoordinates();
            findViewById(boardArray[i]).getLocationInWindow(imgCoordinates);
            coordinates.setViewID(boardArray[i]);
            coordinates.setX(imgCoordinates[0]);
            coordinates.setY(imgCoordinates[1]);
            //System.out.println(coordinates.getFirstDirection() + " " +coordinates.getSecondDirection());
            //System.out.println(imgCoordinates[1]);
            coordinatesArray.add(coordinates);
        }
        boardGenerated = true;
    }

    private void addDirectionsToDrawables()
    {
        addDrawableToArray(carImages1[0], "E", "NE");
        addDrawableToArray(carImages1[1], "E", "NW");
        addDrawableToArray(carImages1[2], "E", "SE");
        addDrawableToArray(carImages1[3], "N", "NE");
        addDrawableToArray(carImages1[4], "N", "NW");
        addDrawableToArray(carImages1[5], "N", "SE");
        addDrawableToArray(carImages1[6], "N", "SW");
        addDrawableToArray(carImages1[7], "N", "W");
        addDrawableToArray(carImages1[8], "NE", "NW");
        addDrawableToArray(carImages1[9], "NE", "SE");
        addDrawableToArray(carImages1[10], "NE", "SW");
        addDrawableToArray(carImages1[11], "NW", "SE");
        addDrawableToArray(carImages1[12], "NW", "SW");
        addDrawableToArray(carImages1[13], "S", "E");
        addDrawableToArray(carImages1[14], "S", "NE");
        addDrawableToArray(carImages1[15], "S", "NW");
        addDrawableToArray(carImages1[16], "S", "SE");
        addDrawableToArray(carImages1[17], "S", "SW");
        addDrawableToArray(carImages1[18], "SE", "SW");
        addDrawableToArray(carImages1[19], "W", "NE");
        addDrawableToArray(carImages1[20], "W", "NW");
        addDrawableToArray(carImages1[21], "W", "SW");
        addDrawableToArray(carImages1[22], "E", "SW");
        addDrawableToArray(carImages1[23], "E", "W");
        addDrawableToArray(carImages1[24], "N", "E");
        addDrawableToArray(carImages1[25], "N", "S");
        addDrawableToArray(carImages1[26], "S", "W");
        addDrawableToArray(carImages1[27], "W", "SE");
    }

    private void addDrawableToArray(int drawableInt, String firstDirection, String secondDirection)
    {
        roundaboutTags rt = new roundaboutTags();
        rt.setTagID(drawableInt);
        rt.setFirstDirection(firstDirection);
        rt.setSecondDirection(secondDirection);
        rtArray.add(rt);
    }

    private int scoreCars()
    {
        int finalScore = 0;
        if (!boardGenerated)
        {
            generateBoardArrayCoordinates();
        }
        for (int i = 0; i < coordinatesArray.size(); i++)
        {
            for (int j = 0; j < rtArray.size(); j++)
            {
                if (findViewById(coordinatesArray.get(i).getViewID()).getTag() != null)
                {
                    int coordinatesTag = Integer.valueOf(findViewById(coordinatesArray.get(i).getViewID()).getTag().toString());
                    if (coordinatesTag == rtArray.get(j).getTagID())
                    {
                        if ((rtArray.get(j).getFirstDirection() == coordinatesArray.get(i).getFirstDirection()) || (rtArray.get(j).getFirstDirection() == coordinatesArray.get(i).getSecondDirection()))
                        {
                            finalScore++;
                        }
                        if ((rtArray.get(j).getSecondDirection() == coordinatesArray.get(i).getFirstDirection()) || (rtArray.get(j).getSecondDirection() == coordinatesArray.get(i).getSecondDirection()))
                        {
                            finalScore++;
                        }
                    }
                }
            }
        }
        return finalScore;
    }

    private final class dragTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            /*if (timer > 300 second){
            timesUp = true;
            place finalScore into file.

            }

             */
            if ((view != findViewById(R.id.finishButton)) && (view != findViewById(R.id.bin))) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    ClipData data = ClipData.newPlainText("", "");
                    String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                    view.startDragAndDrop(data, shadowBuilder, view, 0);
/*                System.out.println("Touched view ID:");
                System.out.println(view.getTag());
                System.out.println("Option 1 ID:");
                System.out.println(R.id.option1);
                System.out.println(view.getLeft());
                System.out.println(findViewById(R.id.option1).getLeft());
                if (view.getParent() == findViewById(R.id.option1)){
                    System.out.println("You have clicked on option1");
                }*/
                    return true;
                }
            }
            if (view == findViewById(R.id.finishButton))
            {
                /*
                if (timesUp)
                {
                    display confirmation popup?
                    pass time and finalScore into scoresheet.
                }
                 */
                int finishScore = 0;
                finishScore = scoreCars();
                System.out.println("The current score is: "+ finishScore); //Replace with score sent to scoresheet.
            }
            if (view == findViewById(R.id.bin))
            {
                for (int i = 0; i < binImages.size(); i++)
                {
                    carImages.add(binImages.get(i));
                }
                binImages.clear();
                populateCardSlot();
                System.out.println("You can clicked the bin, the cards in the bin are meant to be added back to the pile");
            }
            return false;
        }
    }

    private void populateCardSlot()
    {
        if (carImages.size() != 0) {
            int random = (int) (Math.random() * carImages.size() + 0);
            ImageView cardSlotImage = findViewById(R.id.cardSlot);
            cardSlotImage.setImageDrawable(getDrawable(carImages.get(random)));
            cardSlotImage.setTag(carImages.get(random));
        }
    }
    private final class dragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int action = dragEvent.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if (dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        return true;
                    } else {
                    }
                    return false;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    view.invalidate();
                    ImageView draggedFrom = (ImageView) dragEvent.getLocalState();
                    if ((((ImageView) view).getDrawable()!=null) && ((view.getId() != R.id.bin)))
                    {
                        break;
                    }
                    if (view.getId() == R.id.bin) {
                        carImages.remove(draggedFrom.getTag());
                        binImages.add(Integer.valueOf(draggedFrom.getTag().toString()));
                    }
                    else {
                        ImageView container = (ImageView) view;
                        container.setImageDrawable(((ImageView) draggedFrom).getDrawable());
                        container.setTag(draggedFrom.getTag());
                    }
                    draggedFrom.setImageDrawable(null);
                    draggedFrom.setVisibility(View.VISIBLE);
                    if (draggedFrom.getId() == R.id.cardSlot) {
                        carImages.remove(draggedFrom.getTag());
                        populateCardSlot();
                    }
                    else{
                        draggedFrom.setTag(null);
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
