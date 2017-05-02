package com.pccw.beaconbank.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pccw.beaconbank.R;


public class AccountFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public AccountFragment() {
    }

    public static AccountFragment newInstance(int sectionNumber) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        Snackbar.make(getActivity().findViewById(R.id.main_content), "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        return rootView;
    }
}
