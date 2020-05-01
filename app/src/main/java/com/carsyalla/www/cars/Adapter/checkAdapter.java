package com.carsyalla.www.cars.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.carsyalla.www.cars.Model.check;
import com.carsyalla.www.R;
import com.carsyalla.www.cars.library.savedId;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class checkAdapter extends RecyclerView.Adapter<checkAdapter.checkHolder> {
    Context context;
    ArrayList<check> mylist;
    ArrayList<Boolean> checkList = new ArrayList<>();
    int num;
    static ArrayList<String> arrayList_id_ser = new ArrayList<String>();
    static ArrayList<String> arrayList_id_brand = new ArrayList<String>();

    static ArrayList<String> arrayList_title_ser = new ArrayList<String>();
    static ArrayList<String> arrayList_title_brand = new ArrayList<String>();

    static ArrayList<String> arrayList_num_ser = new ArrayList<String>();
    static ArrayList<String> arrayList_num_brand = new ArrayList<String>();


    public checkAdapter(Context context, ArrayList<check> mylist, int num) {
        this.context = context;
        this.mylist = mylist;
        this.num = num;
    }

    @NonNull
    @Override
    public checkHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.check_item, viewGroup, false);
        checkHolder checkHolder = new checkHolder(view);
        return checkHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final checkHolder viewHolder, final int i) {
        viewHolder.checkBox.setText(mylist.get(i).getTitle().toString());
        //checkbox of services_i
        if (num == 0) {
            checkList();
            savedId savedId = new savedId();
            //get size of checked item in array list saved in sharedPrefrences using position
            ArrayList<String> checkList = savedId.retrieveServiceNum(context);
            //loop to get every position of it
            for (int index = 0; index < checkList.size(); index++) {

                //get list items
                int num = Integer.parseInt(checkList.get(index).toString());
                //set num =1
                mylist.get(num).setNum("1");
                //if num=1 make checkbox true
                if (mylist.get(i).getNum().equals("1")) {
                    viewHolder.checkBox.setChecked(true);
                }
            }
        }
        //check box for brands
        else if (num == 1) {
            checkList();
            savedId savedId = new savedId();
            //get size of checked item in array list saved in sharedPrefrences using position
            ArrayList<String> checkList = savedId.retrieveBrandsNum(context);
            Log.e("arraylist", "" + checkList);

            //loop to get every position of it
            for (int index = 0; index < checkList.size(); index++) {
                //get list items
                int num = Integer.parseInt(checkList.get(index).toString());
                //set num =1
                mylist.get(num).setNum("1");
                //if num=1 make checkbox true
                if (mylist.get(i).getNum().equals("1")) {
                    viewHolder.checkBox.setChecked(true);
                }
            }
        }

        //if equal zero service
        if (num == 0) {
            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //add id of check item
                        arrayList_id_ser.add(mylist.get(i).getId().toString());
                        //add title of check item
                        arrayList_title_ser.add(mylist.get(i).getTitle().toString());
                        //add poirtion of check item
                        arrayList_num_ser.add("" + i);
                        mylist.get(viewHolder.getAdapterPosition()).setCheck(isChecked);
                        Toast.makeText(context, "" + mylist.get(i).getTitle().toString(), Toast.LENGTH_SHORT).show();

                        checkList();
                    } else {
                        arrayList_id_ser.remove(mylist.get(i).getId().toString());
                        arrayList_title_ser.remove(mylist.get(i).getTitle().toString());
                        arrayList_num_ser.remove("" + i);
                        checkList();
                    }
                }
            });
        }
        // if equal one brands
        else if (num == 1) {
            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //add id of check item
                        arrayList_id_brand.add(mylist.get(i).getId().toString());
                        //add title of check item
                        arrayList_title_brand.add(mylist.get(i).getTitle().toString());
                        //add poirtion of check item
                        arrayList_num_brand.add("" + i);
                        Toast.makeText(context, "" + mylist.get(i).getTitle().toString(), Toast.LENGTH_SHORT).show();
                        mylist.get(viewHolder.getAdapterPosition()).setCheck(isChecked);
                        checkList();
                    } else {
                        arrayList_id_brand.remove(mylist.get(i).getId().toString());
                        arrayList_title_brand.remove(mylist.get(i).getTitle().toString());
                        arrayList_num_brand.remove("" + i);
                        checkList();
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class checkHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public checkHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }

    //return arraylist services_i
    public ArrayList checkList() {
        //save data in services_i
        if (num == 0) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("services_i", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Set<String> set = new HashSet<String>();
            Set<String> set_name = new HashSet<String>();
            Set<String> set_num = new HashSet<String>();

            set.addAll(arrayList_id_ser);
            set_name.addAll(arrayList_title_ser);
            set_num.addAll(arrayList_num_ser);

            editor.putStringSet("key", set);
            editor.putStringSet("title", set_name);
            editor.putStringSet("num", set_num);
            editor.commit();
        }
        //save data in brands
        else if (num == 1) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("brands", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Set<String> set = new HashSet<String>();
            Set<String> set_name = new HashSet<String>();
            Set<String> set_num = new HashSet<String>();
            set.addAll(arrayList_id_brand);
            set_name.addAll(arrayList_title_brand);
            set_num.addAll(arrayList_num_brand);

            editor.putStringSet("key", set);
            editor.putStringSet("title", set_name);
            editor.putStringSet("num", set_num);
            editor.commit();
        }
        return arrayList_id_ser;
    }

}
