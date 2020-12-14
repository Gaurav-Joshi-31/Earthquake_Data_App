package com.example.earthquake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.GradientDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private String offset;
    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> detail) {
        super(context,0,detail);
    }
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View singleContainerView = convertView;
        if (singleContainerView == null) {
            singleContainerView = LayoutInflater.from(getContext()).inflate(
                    R.layout.single_container, parent, false);
        }
        Earthquake current=getItem(position);


        TextView magnitude=(TextView)singleContainerView.findViewById(R.id.magnitude);
        String formattedMag=formatMagnitude(current.getMagnitude());
        magnitude.setText(formattedMag);

        String city_full_name=current.getCity();
        String [] str=city_full_name.split("of");

        TextView city_offset=(TextView)singleContainerView.findViewById(R.id.city_offset);
        TextView city_name=(TextView)singleContainerView.findViewById(R.id.city_name);

        if(str.length==2){
            offset=str[0]+"of";
            city_offset.setText(offset);
            city_name.setText(str[1].trim());
        }
        else{
            offset="Near the";
            city_offset.setText(offset);
            city_name.setText(str[0]);
        }



            Date dateObject=new Date(current.getTimeInMilliseconds());
        TextView date=(TextView)singleContainerView.findViewById(R.id.date);
        String formattedDate=formatDate(dateObject);
          date.setText(formattedDate);



          TextView time=(TextView)singleContainerView.findViewById(R.id.timeView);
          String formattedTime=formatTime(dateObject);
          time.setText(formattedTime);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(current.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return singleContainerView;
    }







    //helper method for color determination
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
