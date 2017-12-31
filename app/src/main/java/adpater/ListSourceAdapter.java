package adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import common.Common;
import de.hdodenhof.circleimageview.CircleImageView;
import inter.IconBetterIdeaService;
import inter.ItemClickListener;
import inter.NewsService;
import mif50.com.newsreaderapp.ListNews;
import mif50.com.newsreaderapp.R;
import model.IconBetterIdea;
import model.Website;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohamed on 12/11/17.
 */

public class ListSourceAdapter extends RecyclerView.Adapter<ListSourceViewHolder> {
    private Website model;
    private Context context;
    private LayoutInflater inflater;
    private IconBetterIdeaService mService;

    public ListSourceAdapter(Context context,Website model){
        this.context=context;
        this.model=model;
        inflater=LayoutInflater.from(context);
        mService= Common.getIconService();
    }

    @Override
    public ListSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item =inflater.inflate(R.layout.service_layout,parent,false);
        return new ListSourceViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final ListSourceViewHolder holder, int position) {
        // to prepare url of icon that return data (iconBetterApi + url of item )
        StringBuilder iconBetterApi=new StringBuilder("https://icons.better-idea.org/allicons.json?url=");
        iconBetterApi.append(model.getSources().get(position).getUrl());
        // mService => return data as Object of IconBetterIdea
        mService.getIconUrl(iconBetterApi.toString()).enqueue(new Callback<IconBetterIdea>() {
            @Override
            public void onResponse(Call<IconBetterIdea> call, Response<IconBetterIdea> response) {
                if (response.body().getIcons().size()>0) {
                    Picasso.with(context).load(response.body().getIcons().get(0).getUrl())
                            .into(holder.image_item);
                }
            }

            @Override
            public void onFailure(Call<IconBetterIdea> call, Throwable t) {

            }
        });
        holder.title_item.setText(model.getSources().get(position).getName());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                // move to ListNews Activity
                context.startActivity(ListNews.newIntent(context,model.getSources().get(position).getId(),
                        model.getSources().get(position).getSortBysAvailable().get(0)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return model.getSources().size();
    }
}
class ListSourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public CircleImageView image_item;
    public TextView title_item;
    private ItemClickListener itemClickListener;

    public ListSourceViewHolder(View itemView) {
        super(itemView);
        image_item=itemView.findViewById(R.id.image_service);
        title_item=itemView.findViewById(R.id.title_service);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
