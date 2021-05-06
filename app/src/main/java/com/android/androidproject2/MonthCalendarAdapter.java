package com.android.androidproject2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Calendar;

public class MonthCalendarAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS=20;
    public MonthCalendarAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    int year;
    int month;

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH)+1;

        findSwifeday(position - 10);

        return MonthCalendarFragment.newInstance(year, month);
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }

    // position에 따른 년도와 월 계산
    public void findSwifeday(int swipe) {

        if((month + swipe) >= 13 && (month + swipe) % 12 != 0) {
            year = year + ((month + swipe) / 12);
            month = (month+swipe) % 12;
        }
        else if ((month + swipe) >= 13 && (month + swipe) % 12 == 0) {
            year = year + ((month + swipe) / 12) - 1;
            month = 12;
        }
        else {
            month = month + swipe;
        }
    }


}
