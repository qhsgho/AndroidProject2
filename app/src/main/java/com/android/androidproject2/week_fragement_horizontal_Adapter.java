package com.android.androidproject2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Calendar;

public class week_fragement_horizontal_Adapter extends FragmentStateAdapter {
    private static int NUM_ITEMS=200;
    public week_fragement_horizontal_Adapter(@NonNull Week_View_Fragment fragmentActivity) {
        super(fragmentActivity);

    }
    int year;
    int month;
    int day;
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH)+1;
        day = cal.get(Calendar.DATE);
        findSwifeday(position - 50);

        return week_fragment_horizontal1.newInstance(year, month,day);
    }
    public void findSwifeday(int swipe) {
        int result;
        result = month + swipe;

        if(result > 0) {
            if((result) > 12 && (result) % 12 != 0) {
                year = year + ((result) / 12);
                month = (result) % 12;
            }
            else if ((result) > 12 && (result) % 12 == 0) {
                year = year + ((result) / 12) - 1;
                month = 12;
            }
            else {
                month = result;
            }
        }

        else {
            if(result > -12) {
                year = year - 1;
                month = 12 + result;
            }
            else {
                year = year - 1 + (result / 12);
                month = 12 + (result % 12);
            }
        }
    }


    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }
}
