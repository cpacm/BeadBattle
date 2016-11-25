package com.cpacm.game.Bead;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cpacm.game.R;

/**
 * Created by cpacm on 2014/10/10.
 */
public class BeadFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bead_fragment, container, false);
       // return super.onCreateView(inflater, container, savedInstanceState);
    }

}
