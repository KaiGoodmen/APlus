package com.rafael.apluse.adapters;

import android.content.Context;
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

import com.rafael.apluse.R;
import com.rafael.apluse.classes.Date;
import com.rafael.apluse.classes.StudentClass;
import com.rafael.apluse.classes.TinyDB;
import com.rafael.apluse.fragments.ClassPageFragment;

import java.util.ArrayList;

public class AddTaskAdapter extends RecyclerView.Adapter<AddTaskAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    //private ArrayList<About> about ;
    private ArrayList<StudentClass> classes;
    private final Context mContext;


    public static class ViewHolder extends RecyclerView.ViewHolder {
       // ImageView image;
        TextView className;
        TextView proName;
        CardView cardView;
        ClassPageFragment classPageFragment;

        public ViewHolder(View itemView) {
            super(itemView);
           // image = itemView.findViewById(R.id.ivHomeRVImage);
            className = itemView.findViewById(R.id.tvClassNameHomeRVCard);
            proName = itemView.findViewById(R.id.tvProNameHomeRVCard);
            cardView = itemView.findViewById(R.id.classRVCardView);
        }

    }

    public AddTaskAdapter(Context context, ArrayList<StudentClass>classes) {
        mContext = context;
        this.classes = classes;
        //this.businesses = businesses;

       // Log.d("GGGGGGGGGGGGG",about.toString());

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_class_card_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  int position) {
        TinyDB tinyDB = new TinyDB(mContext);

      //  Log.d(TAG, "onBindViewHolder: called.");
       // Business currentBusiness = businesses.get(position);
        StudentClass currentClass = classes.get(position);
        //  About currentAbout = about.get(position);
        tinyDB.putInt("currentPosition",position);
      //  Log.d("GGGGGGGGGHHH",about.toString());
        holder.className.setText(currentClass.getClassName());
        holder.proName.setText(currentClass.getProName());
        holder.cardView.setCardBackgroundColor(Color.parseColor(currentClass.getColor()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("GGGGGXXXXXX",position+"");
                //tinyDB.putInt("POSITION_KEY",position);
                String className = classes.get(position).getClassName();
//                String proName = classes.get(position).getProName();
//                String classBGColor = classes.get(position).getColor();
//                String location = classes.get(position).getLocation();
//                String phoneNumber = classes.get(position).getProPhone();
//                String email = classes.get(position).getProEmail();
//
//                ArrayList<String> dateList = new ArrayList<>();
//
//                for(Date d: classes.get(position).getDate())
//                {
//                    dateList.add(d.getDate());
//                    tinyDB.putListString("DateStringList",dateList);
//
//                }
//


                tinyDB.putString("RVOnClickClassName",className);
//                tinyDB.putString("RVOnClickProName",proName);
//                tinyDB.putString("ClickedClassBGColor",classBGColor);
//                tinyDB.putString("RVOnClickClassLocation",location);
//                tinyDB.putString("RVOnClickClassPhoneNumber",phoneNumber);
//                tinyDB.putString("RVOnClickClassEmail",email);


                Toast.makeText(view.getContext(),className, Toast.LENGTH_SHORT).show();
//                //tinyDB.putListObject("Services",ArrayList(businesses));
//
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                Fragment myFragment = new ClassPageFragment();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, myFragment).addToBackStack(null).commit();



//                Intent intent = new Intent(mContext, BusinessDetail.class);
//                mContext.startActivity(intent);
            }


        });
    }

    @Override
    public int getItemCount() {
        Log.d("GGGGGGGGGZZZ",classes.size()+"");
        return classes.size();


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
