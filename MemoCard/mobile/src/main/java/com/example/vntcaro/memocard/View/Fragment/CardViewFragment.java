package com.example.vntcaro.memocard.View.Fragment;

/**
 * Created by vntcaro on 30/10/2015.
 */


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vntcaro.memocard.R;

/**This class defines a Fragmment for a Card and it's used by StudyCardsView**/
public class CardViewFragment extends Fragment {
    private TextView mFrontText;
    private TextView mBackText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.card_fragment, null);
        Bundle argumets = getArguments();
        if(argumets!=null){
            mFrontText= (TextView) root.findViewById(R.id.front_card_study);
            mBackText= (TextView) root.findViewById(R.id.back_card_study);
            mFrontText.setText(argumets.getString("FRONT"));
            mBackText.setText(argumets.getString("BACK"));
        }
        return root;
    }

    public void hideField(){
        mFrontText.setVisibility(View.GONE);
        mBackText.setVisibility(View.GONE);
    }

}
