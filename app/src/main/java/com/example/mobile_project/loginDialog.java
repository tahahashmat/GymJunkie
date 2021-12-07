package com.example.mobile_project;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

//Used to popout the dialog for update information
public class loginDialog extends AppCompatDialogFragment {
    EditText newUsername, newPassword;
    DialogListener listener;
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity(). getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        newUsername = view.findViewById(R.id.username);
        newPassword = view.findViewById(R.id.password);

        builder.setView(view)
                .setTitle("Account Login")
                //cancel button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    }
                })
                //update button
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String userName = newUsername.getText().toString();
                        String passWord = newPassword.getText().toString();
                        if (userName.equals("")){
                            Toast.makeText(getContext(), "Try Again...Please Enter Username", Toast.LENGTH_SHORT).show();
                        }
                        else if (passWord.equals("")){
                            Toast.makeText(getContext(), "Try Again...Please Enter Password", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            listener.login(userName, passWord);//calls the text method to update the database
                        }
                    }
                });
        return builder.create();
    }

    public void onAttach (Context context){
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    public interface DialogListener{
        void login(String username, String password);
    }
}
