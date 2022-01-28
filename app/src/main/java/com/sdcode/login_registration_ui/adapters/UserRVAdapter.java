package com.sdcode.login_registration_ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.sdcode.login_registration_ui.R;
import com.sdcode.login_registration_ui.models.ModelClassRVUser;

import java.util.ArrayList;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.UsersRVViewHolder>{

    private OnItemClickListener mListener;

    ArrayList<ModelClassRVUser> modelClassList;
    private Context context;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public Integer getUserId(int position) {
        return modelClassList.get(position).getUserId();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public UserRVAdapter(ArrayList<ModelClassRVUser> objectModelClassList, Context context) {
        modelClassList = objectModelClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public UsersRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_user,parent,false);
        return new UsersRVViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersRVViewHolder holder, int position) {

        ModelClassRVUser modelClass = modelClassList.get(position);
        holder.userName.setText(modelClass.getFName() + " " + modelClass.getLName());
        holder.userEmail.setText(modelClass.getEmail());
//        Integer userId = modelClass.getUserId();

        if (modelClass.getGender().equals("Male")){
            holder.avatarImage.setImageDrawable(context.getResources().getDrawable(R.drawable.avatar_male));
        }else{
            holder.avatarImage.setImageDrawable(context.getResources().getDrawable(R.drawable.avatar_female));
        }
    }

    @Override
    public int getItemCount() {
        try {
            return modelClassList.size();
        } catch (Exception e) {
            Log.d("RecyclerViewAdapter", "Exception");
        }
        return 0;
    }

    public static class UsersRVViewHolder extends RecyclerView.ViewHolder{
        AppCompatImageView avatarImage;
        TextView userName,userEmail;


        public UsersRVViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            avatarImage = itemView.findViewById(R.id.rvAvatar);
            userName = itemView.findViewById(R.id.rvUserName);
            userEmail = itemView.findViewById(R.id.rvEmail);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
//                    Messagee.message(context,userName.getText().toString());
                }
            });

        }
    }
}
