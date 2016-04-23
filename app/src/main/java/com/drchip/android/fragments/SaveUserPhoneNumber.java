package com.drchip.android.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.drchip.android.R;
import com.drchip.android.models.HomePageBundle;

/**
 * Created by mohann on 23-04-2016.
 */
public class SaveUserPhoneNumber extends BaseFragment {

    View rootView;

    EditText phoneNumberEditText;
    RelativeLayout osRootLayout;
    EditText osTypeEditText;
    EditText osVersionEditText;
    TextView dateTextView;
    TextView timeTextView;
    Button submitButton;

    HomePageBundle homePageBundle;

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
        submitButton = (Button) rootView.findViewById(R.id.submit_button);

        if(homePageBundle.isShowOs()){
            osRootLayout.setVisibility(View.VISIBLE);
        } else {
            osRootLayout.setVisibility(View.GONE);
        }
        submitButton.setText(homePageBundle.getType());
        return rootView;
    }
}
