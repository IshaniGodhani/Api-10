package com.example.api_10;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.View_Handler> {

    Activity activity;
    ArrayList<DataModel> list;
    public Recycler_Adapter(Activity activity, ArrayList<DataModel> list) {
        this.activity=activity;
        this.list=list;
    }

    @NonNull
    @Override
    public Recycler_Adapter.View_Handler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(activity).inflate(R.layout.item_list,parent,false);
        View_Handler view_handler=new View_Handler(view);
        return view_handler;
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_Adapter.View_Handler holder, int position) {
    DataModel dataModel=list.get(position);

    int id=dataModel.getId();
    String name=dataModel.getName();
    String username=dataModel.getUsername();
    String email=dataModel.getEmail();
    String phone=dataModel.getPhone();
    String website=dataModel.getWebsite();
   
    holder.Id.setText(""+id);
    holder.Name.setText(""+name);
    holder.Username.setText(""+username);
    holder.Email.setText(""+email);
    holder.Phone.setText(""+phone);
    holder.Website.setText(""+website);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class View_Handler extends RecyclerView.ViewHolder {
        TextView Id,Name,Username,Email,Street,Suite,City,Zipcode,Phone,Website;
        public View_Handler(@NonNull View itemView) {
            super(itemView);
            Id=itemView.findViewById(R.id.item_id);
            Name=itemView.findViewById(R.id.item_name);
            Username=itemView.findViewById(R.id.item_username);
            Email=itemView.findViewById(R.id.item_email);
            Street=itemView.findViewById(R.id.item_street);
            Suite=itemView.findViewById(R.id.item_suit);
            City=itemView.findViewById(R.id.item_city);
            Zipcode=itemView.findViewById(R.id.item_zip_code);
            Phone=itemView.findViewById(R.id.item_phone);
            Website=itemView.findViewById(R.id.item_website);
        }
    }
}
