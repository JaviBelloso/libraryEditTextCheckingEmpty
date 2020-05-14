package com.javibelloso.edittextchecking;

import android.annotation.SuppressLint;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * @author Javi Belloso
 * A simple {@link Fragment} subclass.
 */
public abstract class CheckingEditTestEmptyandFocusFragment extends Fragment {

    private ViewGroup viewById;
    private ArrayList<EditText> array;
    private View editTextEmpty;


    /**
     * Gets edit text empty.
     *
     * @param editTextsException the edit texts exception
     * @param messageToast       the message toast
     * @return the edit text empty
     */
    public View getEditTextEmpty(ArrayList<EditText> editTextsException, String messageToast) {
        array = new ArrayList<>();
        return StartCkecking(editTextsException,messageToast);
    }

    /**
     * Sets edit text empty.
     *
     * @param editTextEmpty the edit text empty
     */
    public void setEditTextEmpty(View editTextEmpty) {
        this.editTextEmpty = editTextEmpty;
    }

    /**
     * Instantiates a new Checking edit test emptyand focus fragment.
     */
    public CheckingEditTestEmptyandFocusFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(android.R.id.content, container, false);
        viewById = (ViewGroup) inflate.findViewById(android.R.id.content);
        return inflate;
    }

    private View StartCkecking(ArrayList<EditText> editTextsException, String messageToast) {
        if(messageToast.equals(""))
            messageToast="Fill this field!";
        editTextEmpty= findAllEditTexts(viewById,editTextsException);
        if (editTextEmpty != null) {
            editTextEmpty.requestFocus();
            final Drawable backgroundOriginal = editTextEmpty.getBackground();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                editTextEmpty.setBackground(getActivity().getDrawable(R.color.light_red));
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            editTextEmpty.setBackground(backgroundOriginal);
                        }
                    });

                }
            }, 800);
            Toast.makeText(getActivity(), messageToast, Toast.LENGTH_SHORT).show();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editTextEmpty, InputMethodManager.SHOW_IMPLICIT);
        }
        return editTextEmpty;
    }

    private View findAllEditTexts(ViewGroup viewGroup, ArrayList<EditText> editTextsException) {

        View viewEmpty = null;
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup)
                findAllEditTexts((ViewGroup) view, editTextsException);
            else if (view instanceof EditText) {
                EditText editText = (EditText) view;
                if(editText.getText().toString().equals("") && !editTextsException.contains(editText)) {
                    array.add(editText);
                }

            }

        }
        if(array.size()>0)
            return array.get(0);
        else
            return null;

    }
}
