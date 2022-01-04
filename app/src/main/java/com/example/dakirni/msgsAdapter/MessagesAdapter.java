package com.example.dakirni.msgsAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dakirni.MsgActivity;
import com.example.dakirni.R;

import java.util.Date;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private List<Message> messageList;
    private Context mContext;

    public MessagesAdapter(List<Message>messageList, Context context) {
        this.messageList=messageList;
        this.mContext = context;
    }


    @NonNull
    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_list_item_design,parent,false);
        return new MessagesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.ViewHolder holder, int position) {

        String msgLabel = messageList.get(position).getMsgLabel();
        String textContent=messageList.get(position).getTextContent();
        String[] msgImages=messageList.get(position).getImagesArray();
        String[] msgVoices=messageList.get(position).getVoicesArray();
        Date msgCreationDate=messageList.get(position).getCreationDate();
        holder.setData(msgLabel,textContent,msgImages,msgVoices,msgCreationDate);



    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    //view holder class



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView msgLabelView;
        private TextView msgContextView;
        private TextView msgCreationDateView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //here use xml ids
            //give different name not like constructor
            msgLabelView=itemView.findViewById(R.id.msgLabel);
            msgContextView=itemView.findViewById(R.id.msgContent);
            msgCreationDateView=itemView.findViewById(R.id.msgDate);

itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
//        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
//        builder.setTitle(msgLabelView.getText().toString());
//        builder.setMessage(msgContextView.getText().toString());
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//        builder.show();
        Intent intent=new Intent(mContext, MsgActivity.class);
        intent.putExtra("Label",msgLabelView.getText().toString());
        intent.putExtra("Context",msgContextView.getText().toString());
        intent.putExtra("Date",msgCreationDateView.getText().toString());
        mContext.startActivity(intent);
    }
});

        }

        public void setData(String msgLabel, String msgContent, String[] msgImages,String[] msgVoices,Date msgCreationDate) {

            msgLabelView.setText(msgLabel);
            msgContextView.setText(msgContent);
            String baseDate=msgCreationDate.toLocaleString();
            String date=baseDate.toString();
            msgCreationDateView.setText(date);



        }

    }
}
