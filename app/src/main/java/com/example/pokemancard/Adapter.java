package com.example.pokemancard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable{
    private Context context;
    private ArrayList<CardDisplay> cardDisplays;
    private ArrayList<CardDisplay> cardDisplaysFull;

    private OnItemClickListener mListener;




    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public Adapter(Context context, ArrayList<CardDisplay> cardDisplays) {
        this.context = context;
        this.cardDisplays = cardDisplays;
        cardDisplaysFull=new ArrayList<>(cardDisplays);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pokocard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardDisplay cardDisplay=cardDisplays.get(position);
        String imageUrl =cardDisplay.getmImgUrl();
        String title=cardDisplay.getmName();
        String body=cardDisplay.getmBody();

        holder.textViewTitle.setText(title);
        holder.textViewBody.setText(body);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return cardDisplays.size();
    }

    @Override
    public Filter getFilter() {

        return exampleFilter;
    }
    private Filter exampleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CardDisplay> filteredList=new ArrayList<>();
            if(constraint==null||constraint.length()==0){
                filteredList.addAll(cardDisplaysFull);

            }else{
                String filterPattern =constraint.toString().toLowerCase().trim();

                for(CardDisplay item :cardDisplaysFull){
                    if(item.getmName().toLowerCase().contains(filterPattern));
                    filteredList.add(item);
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
          cardDisplays.clear();
          cardDisplays.addAll((Collection<? extends CardDisplay>) results.values);
          notifyDataSetChanged();

        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textViewTitle;
        public TextView textViewBody;
//        public ImageView dImg;
   public TextView dCandy;
        public TextView weight;
        public TextView height;
        public TextView weakness;
//        public TextView dBody;
//        public TextView dName;








        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imageView=itemView.findViewById(R.id.imgcard);
            textViewTitle=itemView.findViewById(R.id.txtcardtitle);
            textViewBody=itemView.findViewById(R.id.txtcardbody);
//            dImg=itemView.findViewById(R.id.dimg);
           dCandy=itemView.findViewById(R.id.candy);
//            dBody=itemView.findViewById(R.id.dbody);
           weakness=itemView.findViewById(R.id.weakness);
            weight=itemView.findViewById(R.id.weight);
           height=itemView.findViewById(R.id.height);
//            dName=itemView.findViewById(R.id.dtitle);
//
//
//
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position =getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

//    public void updateList(List<CardDisplay> newList){
//        cardDisplays=new ArrayList<>();
//        cardDisplays.addAll(newList);
//        notifyDataSetChanged();
//    }
}
