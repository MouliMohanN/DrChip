package com.drchip.android.fragments;


import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.drchip.android.R;
import com.drchip.android.mail.SendMail;
import com.drchip.android.models.HomePageBundle;
import com.drchip.android.models.mobileinfo.App;
import com.drchip.android.models.mobileinfo.Os;
import com.drchip.android.models.mobileinfo.UserAgent;
import com.drchip.android.views.custom.FormEditText;
import com.google.gson.JsonObject;

import java.util.Calendar;

/**
 * Created by mohann on 23-04-2016.
 */
public class SaveUserPhoneNumber extends BaseFragment implements View.OnClickListener {

    View rootView;

    Toolbar toolbar;
    FormEditText phoneNumberEditText;
    RelativeLayout osRootLayout;
    EditText osTypeEditText;
    EditText osVersionEditText;
    TextView dateTextView;
    TextView timeTextView;
    Button submitButton;
    TextView appUdateText;
    //InputMethodManager imm;

    HomePageBundle homePageBundle;
    String phNumber;
    String osType;
    String osVersion;
    String date;
    String time;

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
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        getActionBar().show();
        setHasOptionsMenu(true);
        setupActionBar();
        phoneNumberEditText = (FormEditText) rootView.findViewById(R.id.phone_number_edit_text);
        osRootLayout = (RelativeLayout) rootView.findViewById(R.id.os_root_layout);
        osTypeEditText = (EditText) rootView.findViewById(R.id.os_type_edittext);
        osVersionEditText = (EditText) rootView.findViewById(R.id.os_version_edittext);
        dateTextView = (TextView) rootView.findViewById(R.id.date_textview);
        timeTextView = (TextView) rootView.findViewById(R.id.time_textview);
        dateTextView.setOnClickListener(this);
        timeTextView.setOnClickListener(this);
        submitButton = (Button) rootView.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);
        appUdateText = (TextView) rootView.findViewById(R.id.app_update_text);
        appUdateText.setSelected(true);

        //imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

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
    public void setupActionBar() {
        super.setupActionBar();
        setTitle(homePageBundle.getName());
    }

    @Override
    public void onClick(View v) {
        //if(v.equals(phoneNumberEditText)){
            //imm.toggleSoftInput(0,0);
            //phoneNumberEditText.requestFocus();
        //}

        if(v.equals(dateTextView) || v.equals(timeTextView)){
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

        if(v.equals(submitButton)){
            if(phoneNumberEditText.testValidity()){
                String phNumber = phoneNumberEditText.getText().toString();
                if(phNumber.isEmpty()){
                    Toast.makeText(baseActivity, "Please provide Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                this.phNumber = phoneNumberEditText.getText().toString();
                this.osType = osTypeEditText.getText().toString();
                this.osVersion = osVersionEditText.getText().toString();
                this.date = dateTextView.getText().toString();
                this.time = timeTextView.getText().toString();
                new MyTask().execute();
                //DrChipContentManager.getInstance().sayHelloWorldGet(DrChipConstants.SERVER_URL + "/hello/baby");
                //DrChipContentManager.getInstance().sayHelloWorld(DrChipConstants.SERVER_URL + "/hello/baby" , getJsonRequest());
            }
        }
        super.onClick(v);
    }

    private JsonObject getJsonRequest(){
        String osType = osTypeEditText.getText().toString();
        String osVersion = osVersionEditText.getText().toString();
        String date = dateTextView.getText().toString();
        String time = timeTextView.getText().toString();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("phNumber",phoneNumberEditText.getText().toString());

        if(!osType.isEmpty()){
            jsonObject.addProperty("osType", osType);
        }
        if(!osVersion.isEmpty()){
            jsonObject.addProperty("osVersion", osVersion);
        }
        if(!date.isEmpty()){
            jsonObject.addProperty("date" , date);
        }
        if(!time.isEmpty()){
            jsonObject.addProperty("time", time);
        }

        return jsonObject;
    }

    public class MyTask extends AsyncTask<String, Integer,String>{
        boolean success = false;
        @Override
        protected String doInBackground(String... params) {

            try {

                String[] strArr = { "moulimohann@gmail.com", "fifaarun@gmail.com" };

                String subject;
                String body;

                subject = phNumber;
                body = "Ph Number - " + phNumber + "\n";
                if(!osType.isEmpty()){
                    body += "Os Type - " + osType + "\n";
                }
                if(!osVersion.isEmpty()){
                    body += "Os Version - " + osVersion + "\n";
                }
                if(!date.isEmpty()){
                    body += "Date - " + date + "\n";
                }
                if(!time.isEmpty()){
                    body += "Time - " + time + "\n";
                }

                try{
                    UserAgent userAgent = getUserData();
                    body += "\n\n\n  " + " Mobile Details "+ " \n\n";
                    body += " AppVersion: " + userAgent.getApp().getVersion() + "\n";
                    body += " " + userAgent.getOs().getName() + ": " + userAgent.getOs().getVersion() + " \n";
                    body += " Device Name: " + getDeviceName();
                } catch (Exception e){
                    e.printStackTrace();
                }

                success = SendMail.send(strArr, subject, "Hi Arun, \n\n\n  Here are the details of the customer \n\n" + body);
            } catch (Exception e) {

                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(success){
                Toast.makeText(baseActivity, "sent mail", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(s);
        }

        private UserAgent getUserData() {
            String versionCode = "";
            try {
                versionCode = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
            } catch (android.content.pm.PackageManager.NameNotFoundException e) {
                Log.e("ERROR IN TERMS_OF_USE: ", e.getMessage());
            }

            Os os = new Os();
            os.setName("Android Os Version");
            os.setVersion(Build.VERSION.RELEASE);

            App app = new App();
            app.setVersion(versionCode);

            UserAgent userAgent = new UserAgent();
            userAgent.setApp(app);
            userAgent.setOs(os);

            return userAgent;
        }

        public String getDeviceName() {
            String manufacturer = Build.MANUFACTURER;
            String model = Build.MODEL;
            if (model.startsWith(manufacturer)) {
                return capitalize(model);
            } else {
                return capitalize(manufacturer) + " " + model;
            }
        }


        private String capitalize(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }
            char first = s.charAt(0);
            if (Character.isUpperCase(first)) {
                return s;
            } else {
                return Character.toUpperCase(first) + s.substring(1);
            }
        }
    }
}
