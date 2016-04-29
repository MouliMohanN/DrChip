package com.drchip.android.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.drchip.android.R;
import com.drchip.android.models.HomePageBundle;

import java.util.Calendar;

/**
 * Created by mohann on 23-04-2016.
 */
public class SaveUserPhoneNumber extends BaseFragment implements View.OnClickListener {

    View rootView;

    EditText phoneNumberEditText;
    RelativeLayout osRootLayout;
    EditText osTypeEditText;
    EditText osVersionEditText;
    TextView dateTextView;
    TextView timeTextView;
    ImageView dateTimePicker;
    Button submitButton;
    InputMethodManager imm;

    HomePageBundle homePageBundle;


    SublimePickerFragment.Callback mFragmentCallback = new SublimePickerFragment.Callback() {
        @Override
        public void onCancelled() {
            //rlDateTimeRecurrenceInfo.setVisibility(View.GONE);
        }

        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate,
                                            int hourOfDay, int minute,
                                            SublimeRecurrencePicker.RecurrenceOption recurrenceOption,
                                            String recurrenceRule) {

            /*mSelectedDate = selectedDate;
            mHour = hourOfDay;
            mMinute = minute;
            mRecurrenceOption = recurrenceOption != null ?
                    recurrenceOption.name() : "n/a";
            mRecurrenceRule = recurrenceRule != null ?
                    recurrenceRule : "n/a";*/

            //dateTextView.setText(selectedDate.get);
            String day = String.valueOf(selectedDate.getStartDate().get(Calendar.DAY_OF_MONTH));
            String month = String.valueOf(selectedDate.getStartDate().get(Calendar.MONTH) + 1);
            String year = String.valueOf(selectedDate.getStartDate().get(Calendar.YEAR));
            if(Integer.valueOf(day) < 10){
                day = 0 + "" + day;
            }
            if(Integer.valueOf(month) < 10){
                month = 0 + "" + month;
            }

            String hour = String.valueOf(hourOfDay);
            String min = String.valueOf(minute);
            if(hourOfDay < 10){
                hour = 0 + "" + hourOfDay;
            }
            if(minute < 10){
                min = 0 + "" + minute;
            }
            dateTextView.setText(applyBoldStyle(day)
                    .append("/")
                    .append(applyBoldStyle(month))
                    .append("/")
                    .append(applyBoldStyle(year)));

            timeTextView.setText(applyBoldStyle(hour)
                    .append(":")
                    .append(applyBoldStyle(min)));
            //updateInfoView();


        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        homePageBundle =  bundle.getParcelable("homePageBundle");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.save_user_phone_number, container, false);
        phoneNumberEditText = (EditText) rootView.findViewById(R.id.phone_number_edit_text);
        osRootLayout = (RelativeLayout) rootView.findViewById(R.id.os_root_layout);
        osTypeEditText = (EditText) rootView.findViewById(R.id.os_type_edittext);
        osVersionEditText = (EditText) rootView.findViewById(R.id.os_version_edittext);
        dateTextView = (TextView) rootView.findViewById(R.id.date_textview);
        timeTextView = (TextView) rootView.findViewById(R.id.time_textview);
        dateTimePicker = (ImageView) rootView.findViewById(R.id.date_picker);
        submitButton = (Button) rootView.findViewById(R.id.submit_button);

        phoneNumberEditText.setOnClickListener(this);
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        dateTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DialogFragment to host SublimePicker
                SublimePickerFragment pickerFrag = new SublimePickerFragment();
                pickerFrag.setCallback(mFragmentCallback);

                // Options
                Pair<Boolean, SublimeOptions> optionsPair = getOptions();

                if (!optionsPair.first) { // If options are not valid
                    Toast.makeText(baseActivity, "No pickers activated",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Valid options
                Bundle bundle = new Bundle();
                bundle.putParcelable("SUBLIME_OPTIONS", optionsPair.second);
                pickerFrag.setArguments(bundle);

                pickerFrag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
                pickerFrag.show(baseActivity.getSupportFragmentManager(), "SUBLIME_PICKER");
            }
        });

        if(homePageBundle.isShowOs()){
            osRootLayout.setVisibility(View.VISIBLE);
        } else {
            osRootLayout.setVisibility(View.GONE);
        }
        submitButton.setText(homePageBundle.getType());
        return rootView;
    }


    // Validates & returns SublimePicker options
    Pair<Boolean, SublimeOptions> getOptions() {
        SublimeOptions options = new SublimeOptions();
        int displayOptions = SublimeOptions.ACTIVATE_DATE_PICKER | SublimeOptions.ACTIVATE_TIME_PICKER;

        options.setPickerToShow(SublimeOptions.Picker.DATE_PICKER);
        options.setDisplayOptions(displayOptions);

        return new Pair<>(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE, options);
    }

    private SpannableStringBuilder applyBoldStyle(String text) {
        SpannableStringBuilder ss = new SpannableStringBuilder(text);
        ss.setSpan(new StyleSpan(Typeface.BOLD), 0, text.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(phoneNumberEditText)){
            imm.toggleSoftInput(0,0);
            phoneNumberEditText.requestFocus();
        }
        super.onClick(v);
    }
}
