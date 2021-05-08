package com.android.androidproject2;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Week_Fragement_Day#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Week_Fragement_Day extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;


    int year;
    int day;
    int month;

    public Week_Fragement_Day() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param year Parameter 1.
     * @param month Parameter 2.
     * @param day Parameter 3.
     * @return A new instance of fragment Week_Fragement_Day.
     */
    // TODO: Rename and change types and number of parameters
    public static Week_Fragement_Day newInstance(int year, int month,int day) {
        Week_Fragement_Day fragment = new Week_Fragement_Day();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        args.putInt(ARG_PARAM3, day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
        }
    }

    public void onResume() {
        super.onResume();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((MainActivity) activity).setActionBarTitle(year+"년 "+month+"월");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View calView = inflater.inflate(R.layout.fragment_week__fragement__day, container, false);
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, day);

        GridView gridview = (GridView) calView.findViewById(R.id.calendar_gridview);
        GridListAdapter adapt = new GridListAdapter();

        int count=0;

        if(count !=7)
        {

             if(day==1&&count==0)
             {
                 for(int i = 1; i < cal.get(Calendar.DAY_OF_WEEK); i++) {
                adapt.addItem(new DateItem(" "));
                count++;
                 }
             }
            else {
                 for (int i = 0; i < finddaynum(year, month); i++) {
                     adapt.addItem(new DateItem(Integer.toString(i + 1)));
                     count++;
                 }
             }

        }





        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            TextView previousView = null;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView)view.findViewById(R.id.item_text);

                if((String) textView.getText() != " ") {
                    Toast.makeText(view.getContext(),year+"."+month+"."+textView.getText(),Toast.LENGTH_SHORT).show();
                    if(previousView != null) {
                        // revert the previous view when a new item is clicked
                        previousView.setBackgroundColor(Color.WHITE);
                    }
                    textView.setBackgroundColor(Color.CYAN);
                    previousView = textView;
                }

            }
        });


        return calView;
    }
    public int finddaynum(int year, int month) {
        int day_num = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day_num = 31;
                break;
            case 2:
                if((year%4==0 && year%100 != 0) || year%400 == 0)
                    day_num = 29;
                else
                    day_num = 28;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day_num = 30;
                break;
        }
        return day_num;
    }


}