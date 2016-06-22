package com.software.studio.delicacies.fragments;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.software.studio.delicacies.R;
import com.software.studio.delicacies.RecycleViewAdapter;

import java.util.ArrayList;

public class Settings extends Fragment {
    RecyclerView recyclerView;
    RecycleViewAdapter mAdapter;
    View menuView;
    View FragView;
    SharedPreferences settings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_settings, container, false);
        FragView = inflater.inflate(R.layout.activity_framement_tags, container, false);
        RecyclerView.LayoutManager mLayoutManager;
        ArrayList<String> data = new ArrayList<String>();
        data.add("Notifications");
        data.add("Change Background Color");
        data.add("Password");
        data.add("Reset");
        data.add("About");
        SharedPreferences set;
        recyclerView = (RecyclerView) rootview.findViewById(R.id.setting_recycler_view);
        mLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecycleViewAdapter(data);
        recyclerView.setAdapter(mAdapter);
        registerForContextMenu(recyclerView);
        mAdapter.setOnItemClickListener(new RecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                menuView = view;
                if(data == mAdapter.getDataset().get(0) || data == mAdapter.getDataset().get(1) || data == mAdapter.getDataset().get(2)) {
                    recyclerView.showContextMenuForChild(view);
                }
                else if(data == mAdapter.getDataset().get(3)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Are you sure to reset the datas ?");
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //TODO
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //TODO
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if(data == mAdapter.getDataset().get(4)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Class: Software Studio\nAuthor: Liang-Wei, Yu-Jen");
                    builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

        return rootview;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        if(menuView.getTag() == mAdapter.getDataset().get(0)) {
            menu.setHeaderTitle("Notifications");
            menu.add(0, v.getId(), 0, "On");
            menu.add(0, v.getId(), 0, "Off");
        }
        else if(menuView.getTag() == mAdapter.getDataset().get(1)) {
            menu.setHeaderTitle("Change Background Color");
            menu.add(1, v.getId(), 0, "Red");
            menu.add(1, v.getId(), 0, "Blue");
            menu.add(1, v.getId(), 0, "Green");
            menu.add(1, v.getId(), 0, "Orange");
            menu.add(1, v.getId(), 0, "Purple");
        }
        else if(menuView.getTag() == mAdapter.getDataset().get(2)) {
            menu.setHeaderTitle("Password");
            menu.add(2, v.getId(), 0, "On");
            menu.add(2, v.getId(), 0, "Off");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        settings = getActivity().getSharedPreferences("Preference", 0);
        switch (item.getGroupId()){
            case 0:
                if(item.getTitle().equals("On")){
                    Toast.makeText(getContext(), R.string.msg_NotificationOn, Toast.LENGTH_SHORT).show();
                    settings.edit().putString("notifications", "on").apply();
                }
                else if(item.getTitle().equals("Off")){
                    Toast.makeText(getContext(), R.string.msg_NotificationOff, Toast.LENGTH_SHORT).show();
                    settings.edit().putString("notifications", "off").apply();
                }
                break;
            case 1:
                if(item.getTitle().equals("Red")){
                    Toast.makeText(getContext(), R.string.msg_ChangeColor, Toast.LENGTH_SHORT).show();
                    settings.edit().putString("bgcolor", "red").apply();
                }
                else if(item.getTitle().equals("Blue")){
                    Toast.makeText(getContext(), R.string.msg_ChangeColor, Toast.LENGTH_SHORT).show();
                    settings.edit().putString("bgcolor", "blue").apply();
                }
                else if(item.getTitle().equals("Green")){
                    Toast.makeText(getContext(), R.string.msg_ChangeColor, Toast.LENGTH_SHORT).show();
                    settings.edit().putString("bgcolor", "green").apply();
                }
                else if(item.getTitle().equals("Orange")){
                    Toast.makeText(getContext(), R.string.msg_ChangeColor, Toast.LENGTH_SHORT).show();
                    settings.edit().putString("bgcolor", "orange").apply();
                }
                else if(item.getTitle().equals("Purple")){
                    Toast.makeText(getContext(), R.string.msg_ChangeColor, Toast.LENGTH_SHORT).show();
                    settings.edit().putString("bgcolor", "purple").apply();
                }
                break;
            case 2:
                if(item.getTitle().equals("On")){
                    AlertDialog.Builder editDialog = new AlertDialog.Builder(getActivity());
                    editDialog.setTitle("--- Password ---");

                    final EditText editText = new EditText(getActivity());

                    // Input number
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);

                    // Length at most 10
                    editText.setEms(10);

                    // Hide password
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    editDialog.setView(editText);

                    editDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            if(editText.getText().length()==0){
                                Toast.makeText(getContext(), R.string.msg_EmptyPassword, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                settings.edit().putBoolean("needPassword", true).apply();
                                settings.edit().putString("password", editText.getText().toString()).apply();
                            }
                        }
                    });
                    editDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            settings.edit().putBoolean("needPassword", false).apply();
                        }
                    });
                    editDialog.show();
                }
                else if(item.getTitle().equals("Off")){
                    Toast.makeText(getContext(), "Password Off", Toast.LENGTH_SHORT).show();
                    settings.edit().putBoolean("needPassword", false).apply();
                }
                break;
        }
        return true;
    }
}
