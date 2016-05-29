package com.drchip.android.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.drchip.android.R;
import com.drchip.android.activities.BaseActivity;
import com.drchip.android.models.DateInfo;
import com.drchip.android.views.custom.materialcalederview.CalendarDay;
import com.drchip.android.views.custom.materialcalederview.MaterialCalendarView;
import com.drchip.android.views.custom.materialcalederview.OnDateSelectedListener;
import com.drchip.android.views.custom.materialcalederview.OnMonthChangedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mohann on 29-05-2016.
 */
public class BasicCalendar extends BaseFragment implements OnDateSelectedListener, OnMonthChangedListener, View.OnClickListener {

    View rootView;
    Toolbar toolbar;
    MaterialCalendarView widget;
    TextView textView;
    BaseActivity baseActivity;
    DateInfo dateInfo;
    Button ok;
    Button cancel;
    String SELECTION_TEXT = "No Selection";

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_basic, container, false);
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        getActionBar().show();
        setHasOptionsMenu(true);
        setupActionBar();

        baseActivity = (BaseActivity) getActivity();
        Bundle bundle = getArguments();
        dateInfo = bundle.getParcelable("dateInfo");

        widget = (MaterialCalendarView) rootView.findViewById(R.id.calendarView);
        textView = (TextView) rootView.findViewById(R.id.textView);
        ok = (Button) rootView.findViewById(R.id.ok);
        cancel = (Button) rootView.findViewById(R.id.cancel);
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);

        widget.setMinimumDate(Calendar.getInstance().getTime());
        widget.setOnDateChangedListener(this);
        widget.setOnMonthChangedListener(this);

        //Setup initial text
        textView.setText(getSelectedDatesString());

        return rootView;
    }

    @Override
    public void setupActionBar() {
        super.setupActionBar();
        setTitle("Select Date");
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date, boolean selected) {
        textView.setText(getSelectedDatesString());
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        //noinspection ConstantConditions
        //getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
        setTitle(FORMATTER.format(date.getDate()));
    }

    private String getSelectedDatesString() {
        CalendarDay date = widget.getSelectedDate();
        if (date == null) {
            return SELECTION_TEXT;
        }
        return FORMATTER.format(date.getDate());
    }

    @Override
    public void onClick(View v) {
        if(v.equals(ok)){
            if(!SELECTION_TEXT.equalsIgnoreCase(textView.getText().toString())){
                dateInfo.setDate(textView.getText().toString());
                baseActivity.onBackPressed();
            } else {
                Snackbar snackbar = Snackbar
                        .make(ok, "Please Select date", Snackbar.LENGTH_LONG);
                snackbar.show();
            }

        } else if(v.equals(cancel)){
            baseActivity.onBackPressed();
        }
    }
}
