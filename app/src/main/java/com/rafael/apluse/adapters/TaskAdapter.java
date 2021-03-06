package com.rafael.apluse.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.rafael.apluse.R;
import com.rafael.apluse.classes.StudentClass;
import com.rafael.apluse.classes.SubTask;
import com.rafael.apluse.classes.Task;
import com.rafael.apluse.classes.TinyDB;
import com.rafael.apluse.fragments.ClassPageFragment;
import com.rafael.apluse.fragments.TaskViewFragment;

import java.util.ArrayList;
import java.util.Date;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    //private ArrayList<About> about ;
    private ArrayList<Task> tasks;
    private ArrayList<StudentClass> classes;
    private final Context mContext;


    public static class ViewHolder extends RecyclerView.ViewHolder {
       // ImageView image;
        TextView taskName;
        TextView taskDescription;
        TextView dueDate;
        TextView className;
       // CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
           // image = itemView.findViewById(R.id.ivHomeRVImage);
            taskName = itemView.findViewById(R.id.task_card_TName);
            taskDescription = itemView.findViewById(R.id.task_card_TDesc);
            className = itemView.findViewById(R.id.task_card_CName);
            dueDate = itemView.findViewById(R.id.task_card_DDate);
            //cardView = itemView.findViewById(R.id.classRVCardView);
        }

    }

    public TaskAdapter(Context context, ArrayList<Task>tasks, ArrayList<StudentClass> classes) {
        mContext = context;
        this.tasks = tasks;
        this.classes = classes;

       // Log.d("GGGGGGGGGGGGG",about.toString());

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  int position) {
        TinyDB tinyDB = new TinyDB(mContext);

        Task currentTask = tasks.get(position);
//        StudentClass currentClass  = classes.get(position);
        String className = currentTask.getClassName();
        String taskName = currentTask.getTaskName();
        String taskDesc = currentTask.getTaskDesc();
        String taskDDay = currentTask.getTaskDueDate();
        Date taskDDate = currentTask.getDueDate();
        ArrayList<SubTask> subTasks = currentTask.getSubTasks();
        className = className.substring(1, className.length() - 1);
        StudentClass currentClass;


        tinyDB.putString("currentTaskCName",className);
        tinyDB.putString("currentTaskTName",taskName );
        tinyDB.putString("currentTaskTDesc",taskDesc );
        tinyDB.putString("currentTaskTDDay",taskDDay );
        tinyDB.putObject("currentTaskTDDate",taskDDate);
        saveSubTasks(subTasks);




        for(StudentClass c: classes)
        {
            if(c.getClassName().equals(className))
            {
                currentClass = c;
                String classColor = currentClass.getColor();

                holder.className.setTextColor(Color.parseColor(classColor));
            }
        }


        //String color = classColor.substring(1);

        tinyDB.putInt("currentTaskPosition",position);
      //  Log.d("GGGGGGGGGHHH",about.toString());
        holder.taskName.setText(currentTask.getTaskName());
        holder.taskDescription.setText(currentTask.getTaskDesc());
        holder.dueDate.setText(currentTask.getTaskDueDate());
        holder.className.setText(className);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("GGGGGXXXXXX",position+"");
                ArrayList<String> dateList = new ArrayList<>();
                //tinyDB.putInt("POSITION_KEY",position);
                String className = tasks.get(position).getClassName();

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new TaskViewFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, myFragment).addToBackStack(null).commit();




//                for(StudentClass c: classes)
//                {
//                    if(c.getClassName().equals(className))
//                    {
//                        tinyDB.putObject("RVOnClickClassName",c.getClassName());
//                        tinyDB.putString("ClickedClassProName",c.getProName());
//                        for(com.rafael.apluse.classes.Date i: c.getDate())
//                        {
//                            dateList.add(i.getDate());
//                            tinyDB.putListString("DateStringList",dateList);
//                        }
//                    }
//                }





                Toast.makeText(view.getContext(),className, Toast.LENGTH_SHORT).show();
                //tinyDB.putListObject("Services",ArrayList(businesses));

              //  Intent intent = new Intent(mContext, BusinessDetail.class);
             //   mContext.startActivity(intent);
            }


        });
    }

    @Override
    public int getItemCount() {
        Log.d("GGGGGGGGGZZZ",tasks.size()+"");
        return tasks.size();

    }
    private Long getDaysDifference(Date fromDate, Date toDate) {

        if (fromDate == null || toDate == null)
        {
            return Long.parseLong("0");
        }
        else
        {
            return ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
        }
    }

    private void saveSubTasks(ArrayList<SubTask> subTasks) {
//        val sharedPreference: SharedPreferences? = activity?.getSharedPreferences("sharedPreferencesClass",
//                AppCompatActivity.MODE_PRIVATE
//        )
        SharedPreferences sharedPreference = mContext.getSharedPreferences("sharedPreferencesSubTask",AppCompatActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();
        Gson gson = new Gson();
        String json = gson.toJson(subTasks);
        editor.putString("SubTasks",json);
        editor.apply();
//        val editor: SharedPreferences.Editor? = sharedPreference?.edit()
//        val gson = Gson()
//        val json : String = gson.toJson(classList)
//        editor?.putString("classList",json)
//        editor?.apply()
    }
//    public void filterZipCodeList(@NotNull ArrayList<Business> filteredList) {
//        businesses = filteredList;
//        notifyDataSetChanged();
//    }
//
//
//    public void filterNameList(@NotNull ArrayList<Business> filteredList) {
//        businesses.clear();
//        businesses = filteredList;
//        notifyDataSetChanged();
//
//    }

}
