package com.example.dakirni.AdapterFather;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dakirni.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ModelClass> userList;
    private Context mContext;

    public MyAdapter(List<ModelClass>userList, Context context) {
        this.userList=userList;
        this.mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int resource = userList.get(position).getImageview();
        String name=userList.get(position).getTextview1();
        String msg=userList.get(position).getTextview2();
        String line=userList.get(position).getDivider();

        holder.setData(resource,name,msg,line);



    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    //view holder class



    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private TextView textView2;
        private TextView divider;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //here use xml ids
            //give different name not like constructor
            imageView=itemView.findViewById(R.id.imageview);
            textView=itemView.findViewById(R.id.textview);
            textView2=itemView.findViewById(R.id.textview2);
            divider=itemView.findViewById(R.id.Divider);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Log.d("RecyclerView", "onClick：");
                    int position = getAdapterPosition();
                    ModelClass item = userList.get(position);
                   // String uri = "tel:" + item.getTextview2().trim() ;
                    String phone = item.getTextview2().toString();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    mContext.startActivity(intent);
                 //   Toast.makeText(mContext,item.getTextview2(),Toast.LENGTH_SHORT).show();
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
