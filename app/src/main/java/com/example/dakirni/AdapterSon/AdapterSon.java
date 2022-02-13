package com.example.dakirni.AdapterSon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dakirni.List_son;
import com.example.dakirni.ParentsDashBoard;
import com.example.dakirni.R;
import com.example.dakirni.UpdateFather;
import com.example.dakirni.connectingToBackEnd.CrudFather;
import com.example.dakirni.connection.APIClient;
import com.example.dakirni.database.son.SonDbHelper;
import com.example.dakirni.environements.environementVariablesOfDakirni;
import com.example.dakirni.fatherObjects.FatherCrudResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdapterSon extends RecyclerView.Adapter<AdapterSon.ItemViewHolder> {
    Retrofit retrofit = APIClient.getRetrofitInstance();

    CrudFather crudFather= retrofit.create(CrudFather.class);

    private List<ModelClassforson> fatherList;
    private Context mContext;

    public AdapterSon(List<ModelClassforson>fatherList, Context context) {
        this.fatherList=fatherList;
        this.mContext = context;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design_for_son,parent,false);
        return new ItemViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

//        int resource = fatherList.get(position).getImageview();
        String btmap=fatherList.get(position).getImageview();
        String name=fatherList.get(position).getTextview1();
        String msg=fatherList.get(position).getTextview2();
        String line=fatherList.get(position).getDivider();

        holder.setData(btmap,name,msg,line);



    }

    @Override
    public int getItemCount() {
        return fatherList.size();
    }

    //view holder class



    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private TextView textView2;
        private TextView divider;
        private ImageView imageViewUpdate;
        private ImageView imageViewDelete;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            //here use xml ids
            //give different name not like constructor
            imageView=itemView.findViewById(R.id.image_father);
            imageViewUpdate=itemView.findViewById(R.id.updateItem);
            imageViewDelete=itemView.findViewById(R.id.deleteItem);
            textView=itemView.findViewById(R.id.name_father);
            textView2=itemView.findViewById(R.id.Key);
            divider=itemView.findViewById(R.id.Divider);
            imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SonDbHelper sonDbHelper = new SonDbHelper(mContext);

                    ArrayList<String> arrayList = sonDbHelper.lireToken();
                    StringBuffer maListe = new StringBuffer();

                    try {
                        Iterator<String> iter = arrayList.iterator();
                        while (iter.hasNext()) {
                            maListe.append(iter.next());
                        }
                        Toast.makeText(mContext,maListe.toString(),Toast.LENGTH_SHORT).show();
                    }catch (ArrayIndexOutOfBoundsException e){
                        Toast.makeText(mContext,"Aucun Résultat trouvé !",Toast.LENGTH_SHORT).show();
                    }
//                Toast.makeText(getApplicationContext(),maListe.toString(),Toast.LENGTH_SHORT).show();
                    int position = getAdapterPosition();
                    ModelClassforson item = fatherList.get(position);
                    Call<List<FatherCrudResponse>> call = crudFather.deleteOneFather(maListe.toString(),item.getTextview2().split(":",2)[1]);
                    call.enqueue(new Callback<List<FatherCrudResponse>>() {
                        @Override
                        public void onResponse(Call<List<FatherCrudResponse>> call, @NonNull Response<List<FatherCrudResponse>> response) {

                            if (response.code() == 200) {

                                Toast.makeText(mContext,"Item deleted !",Toast.LENGTH_LONG).show();

                            }
                            else if (response.code() == 409) {
                                Toast.makeText(mContext,"User not found ! Try Signup !",Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<List<FatherCrudResponse>> call, Throwable t) {
                            Toast.makeText(mContext, "Fail", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });
            imageViewUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SonDbHelper sonDbHelper = new SonDbHelper(mContext);

                    ArrayList<String> arrayList = sonDbHelper.lireToken();
                    StringBuilder maListe = new StringBuilder();

                    try {
                        Iterator<String> iter = arrayList.iterator();
                        while (iter.hasNext()) {
                            maListe.append(iter.next());
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        Toast.makeText(mContext,"Aucun Résultat trouvé !",Toast.LENGTH_SHORT).show();
                    }//                Toast.makeText(getApplicationContext(),maListe.toString(),Toast.LENGTH_SHORT).show();
                    int position = getAdapterPosition();
                    ModelClassforson item = fatherList.get(position);


                    Call<FatherCrudResponse> call = crudFather.getOneFather(maListe.toString(),item.getTextview2().split(":",2)[1]);
                    call.enqueue(new Callback<FatherCrudResponse>() {
                        @Override
                        public void onResponse(Call<FatherCrudResponse> call, @NonNull Response<FatherCrudResponse> response) {

                            if (response.code() == 200) {

                                Intent intent = new Intent(mContext, UpdateFather.class);
                                intent.putExtra("key", response.body().getKey());
                                intent.putExtra("age", String.valueOf(response.body().getAge()));
                                intent.putExtra("name", response.body().getName());
                                intent.putExtra("relation", response.body().getRelation());
                                intent.putExtra("photo", response.body().getPhoto());
                                mContext.startActivity(intent);
                            }
                            else if (response.code() == 409) {
                                Toast.makeText(mContext,"User not found ! Try Signup !",Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<FatherCrudResponse> call, Throwable t) {
                            Toast.makeText(mContext, "Fail", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Log.d("RecyclerView", "onClick：");
                    int position = getAdapterPosition();
                    ModelClassforson item = fatherList.get(position);
                ///to get the key
                   Toast.makeText(mContext,item.getTextview2(),Toast.LENGTH_SHORT).show();

                    environementVariablesOfDakirni.key=item.getTextview2().split(":",2)[1];
                    Intent intent=new Intent(mContext, ParentsDashBoard.class);
                    intent.putExtra("key",item.getTextview2());
                    mContext.startActivity(intent);
                }
            });


        }
        private Bitmap decodeImage(String stringedImage) {
            byte[] bytes = Base64.decode(stringedImage, Base64.DEFAULT);
            // Initialize bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            // set bitmap on imageView
            return bitmap;
        }
        public void setData(String img64,String name, String msg, String line) {

//           imageView.setImageResource(resource);
           this.imageView.setImageBitmap(decodeImage(img64));

            textView.setText(name);
           textView2.setText(msg);
            divider.setText(line);

        }

        public String getToken(Context context){
            SonDbHelper sonDbHelper = new SonDbHelper(mContext);

            ArrayList<String> arrayList = sonDbHelper.lireToken();
            StringBuffer maListe = new StringBuffer();

            try {
                Iterator<String> iter = arrayList.iterator();
                while (iter.hasNext()) {
                    maListe.append(iter.next());
                }
                Toast.makeText(mContext,maListe.toString(),Toast.LENGTH_SHORT).show();
                return maListe.toString();
            }catch (ArrayIndexOutOfBoundsException e){
                Toast.makeText(mContext,"Aucun Résultat trouvé !",Toast.LENGTH_SHORT).show();
                return "";
            }
        }
    }
}
