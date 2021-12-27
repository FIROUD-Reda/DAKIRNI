package com.example.dakirni.AdapterContact;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dakirni.ParentsDashBoard;
import com.example.dakirni.R;

import java.util.List;

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.ItemViewHolder> {

    private List<ModelClassforContact> contactList;
    private Context mContext;

    public AdapterContact(List<ModelClassforContact>contactList, Context context) {
        this.contactList=contactList;
        this.mContext = context;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design_for_contact,parent,false);
        return new ItemViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        int resource = contactList.get(position).getImageview();
        String name=contactList.get(position).getTextview1();
        String msg=contactList.get(position).getTextview2();
        String line=contactList.get(position).getDivider();

        holder.setData(resource,name,msg,line);



    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    //view holder class



    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private TextView textView2;
        private TextView divider;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            //here use xml ids
            //give different name not like constructor
            imageView=itemView.findViewById(R.id.image_father);
            textView=itemView.findViewById(R.id.name_father);
            textView2=itemView.findViewById(R.id.Key);
            divider=itemView.findViewById(R.id.Divider);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Log.d("RecyclerView", "onClick：");
                    int position = getAdapterPosition();
                    ModelClassforContact item = contactList.get(position);
                ///to get the key
                   Toast.makeText(mContext,item.getTextview2(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(mContext, ParentsDashBoard.class);
                    mContext.startActivity(intent);
                }
            });


        }

        public void setData(int resource, String name, String msg,String line) {

           imageView.setImageResource(resource);
            textView.setText(name);
           textView2.setText(msg);
            divider.setText(line);



        }
    }
}
