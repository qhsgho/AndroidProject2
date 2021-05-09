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

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeekCalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeekCalendarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;
    private int mParam3;

    int year;
    int month;
    int sundate;

    public WeekCalendarFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static WeekCalendarFragment newInstance(int year, int month, int sundate) {
        WeekCalendarFragment fragment = new WeekCalendarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        args.putInt(ARG_PARAM3, sundate);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
            mParam3 = getArguments().getInt(ARG_PARAM3);
            year = mParam1;
            month = mParam2;
            sundate = mParam3;
        }
    }

    // 적절한 생명주기에서 타이틀 변경
    @Override
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
        View calView = inflater.inflate(R.layout.fragment_week_calendar, container, false);

        // 요일 그리드 뷰
        GridView gridview = (GridView) calView.findViewById(R.id.calendar_gridview_week);
        WeekGridListAdapter adapt = new WeekGridListAdapter();

        // 시간부분 그리드 뷰
        ExpandableHeightGridView gridview_timeline = (ExpandableHeightGridView) calView.findViewById(R.id.calendar_gridview_week_timeline);
        gridview_timeline.setExpanded(true);
        WeekTimeGridListAdapter adapt_timeline = new WeekTimeGridListAdapter();

        // 격자부분 그리드 뷰
        ExpandableHeightGridView gridview_time = (ExpandableHeightGridView) calView.findViewById(R.id.calendar_gridview_week_time);
        gridview_time.setExpanded(true);
        WeekTimeGridListAdapter adapt_time = new WeekTimeGridListAdapter();


        int weeksundate = sundate;
        int restbox = 1;
        int lastday = finddaynum(year, month);

        for(int i = 1; i < 8; i++) {

            if(weeksundate > lastday) {
                adapt.addItem(new DateItem(""+restbox));
                restbox++;
            }
            else {
                adapt.addItem(new DateItem(""+weeksundate));
            }
            weeksundate++;
        }

        for(int i = 0; i < 168; i++) {
            adapt_time.addItem(new DateItem(" "));
        }


        for(int i = 0; i < 24; i++) {
            adapt_timeline.addItem(new DateItem(""+i));
        }

        gridview.setAdapter(adapt);

        gridview_timeline.setAdapter(adapt_timeline);
        adapt_timeline.notifyDataSetChanged();

        gridview_time.setAdapter(adapt_time);
        adapt_time.notifyDataSetChanged();


        // 클릭 이벤트 처리 달력
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            TextView previousView = null;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView)view.findViewById(R.id.item_text_week);

                if(previousView != null) {
                    previousView.setBackgroundColor(Color.WHITE);
                }
                textView.setBackgroundColor(Color.CYAN);
                previousView = textView;
            }
        });


        // 클릭 이벤트 처리 (timeline)
        gridview_time.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            TextView previousView = null;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView)view.findViewById(R.id.item_text_week_time);

                Toast.makeText(view.getContext(),"position : "+position,Toast.LENGTH_SHORT).show();

                if(previousView != null) {
                    previousView.setBackgroundColor(Color.WHITE);
                }
                textView.setBackgroundColor(Color.CYAN);
                previousView = textView;
            }
        });

        return calView;
    }

    // 달의 총 요일을 찾는 함수
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