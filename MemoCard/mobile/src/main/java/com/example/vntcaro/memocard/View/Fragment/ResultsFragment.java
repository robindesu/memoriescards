package com.example.vntcaro.memocard.View.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vntcaro.memocard.R;
import com.example.vntcaro.memocard.View.StudyCardsView;
import com.example.vntcaro.memocard.View.ViewDeckActivity;

/**
 * Created by vntcaro on 22/12/2015.
 * This class show a fragment dialog with the results of a study session
 */
public class ResultsFragment extends DialogFragment {
    private ProgressBar mProgress;
    private Button mErrButton;
    private Button mAllButton;
    private TextView mRightNumb;
    private TextView mRongNumb;
    private View mDialogView;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity());
        if(StudyCardsView.counters[0]<=0){
            initComps();
        }
        builder.setView(mDialogView);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ViewDeckActivity.class);
                intent.putExtra("DECK_ID", StudyCardsView.mDeck_id);
                startActivity(intent);
            }
        });
        return builder.create();
    }

    /**
    *This function inicializate the member variable and set view
    * */
    private void initComps(){
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mDialogView =  inflater.inflate(R.layout.results_fragment,null);
        mErrButton = (Button) mDialogView.findViewById(R.id.err_rev_btn);
        mAllButton = (Button) mDialogView.findViewById(R.id.all_rev_btn);
        mRightNumb = (TextView) mDialogView.findViewById(R.id.right_number);
        mRongNumb = (TextView) mDialogView.findViewById(R.id.rong_number);
        mRightNumb.setText(String.valueOf(StudyCardsView.counters[1]));
        mRongNumb.setText(String.valueOf(StudyCardsView.counters[2]));
        mProgress= (ProgressBar) mDialogView.findViewById(R.id.progressBar);

        mProgress.setMax(StudyCardsView.mNumCards);
        mProgress.setProgress(StudyCardsView.counters[1]);
        mProgress.setScaleX(3f);

        mAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), StudyCardsView.class);
                intent.putExtra("ISALLREVIEW", 1);
                startActivity(intent);
            }
        });
        if(StudyCardsView.counters[2]>0) { //Test if has any rong answear, desable err_rev_btn button if not
            mErrButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), StudyCardsView.class);
                    intent.putExtra("ISRONGREVIEW", 1);
                    startActivity(intent);
                }
            });
        }
        else{
            mErrButton.setEnabled(false);
        }

    }
}
