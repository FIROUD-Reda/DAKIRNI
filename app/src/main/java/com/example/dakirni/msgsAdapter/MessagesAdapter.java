package com.example.dakirni.msgsAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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

    public MessagesAdapter(List<Message> messageList, Context context) {
        this.messageList = messageList;
        this.mContext = context;
    }


    @NonNull
    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_list_item_design, parent, false);
        return new MessagesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.ViewHolder holder, int position) {

        String msgLabel = messageList.get(position).getMsgLabel();
        String textContent = messageList.get(position).getMsgContent();
        String msgImages = messageList.get(position).getMsgImage();
        String msgVoices = messageList.get(position).getMsgVoice();
        Date msgCreationDate = messageList.get(position).getMsgCreationDate();
        String msgColor=messageList.get(position).getMsgColor();
        holder.setData(msgLabel,msgColor, textContent, msgImages, msgVoices, msgCreationDate);


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
        private View msgStatusView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //here use xml ids
            //give different name not like constructor
            msgLabelView = itemView.findViewById(R.id.msgLabel);
            msgContextView = itemView.findViewById(R.id.msgContent);
            msgCreationDateView = itemView.findViewById(R.id.msgDate);
            msgStatusView=itemView.findViewById(R.id.msg_status_view);
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
                    Intent intent = new Intent(mContext, MsgActivity.class);
                    intent.putExtra("Label", messageList.get(getAdapterPosition()).getMsgLabel());
                    intent.putExtra("Context", messageList.get(getAdapterPosition()).getMsgContent());
                    intent.putExtra("Date", messageList.get(getAdapterPosition()).getMsgCreationDate());
                    intent.putExtra("Image", messageList.get(getAdapterPosition()).getMsgImage());
                    intent.putExtra("Voice", messageList.get(getAdapterPosition()).getMsgVoice());
                    mContext.startActivity(intent);
                }
            });

        }

        public void setData(String msgLabel,String msgColor, String msgContent, String msgImages, String msgVoices, Date msgCreationDate) {

            msgLabelView.setText(msgLabel);
            msgContextView.setText(msgContent);
            msgStatusView.setBackgroundColor(Color.parseColor(msgColor));
            String baseDate = msgCreationDate.toGMTString();
            String date = baseDate.substring(0, baseDate.length() - 17) + baseDate.substring(baseDate.length() - 13, baseDate.length() - 7);

            msgCreationDateView.setText(date);


        }

    }
}
