package adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import inter.ItemClickListener;
import mif50.com.newsreaderapp.DetailsArticle;
import mif50.com.newsreaderapp.R;
import model.Article;
import model.ISO8601DateParser;

/**
 * Created by mohamed on 12/14/17.
 */

public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsViewHolder> {
    private Context context;
    private List<Article> articles;
    private LayoutInflater layoutInflater;

    public ListNewsAdapter(Context context,List<Article> articles){
        this.context=context;
        this.articles=articles;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public ListNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item=layoutInflater.inflate(R.layout.item_layout,parent,false);
        return new ListNewsViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ListNewsViewHolder holder, int position) {
        // bind data here
        Picasso.with(context).load(articles.get(position).getUrlToImage())
                .into(holder.image_news); // set image into ImageView in item_layout.xml

        String title=articles.get(position).getTitle();
        if (title.length() >65){// check if title more than 65 chart
            holder.title_news.setText(title.substring(0,65)+" ... "); // get first 65 char and set in TextView title in item_layout.xml
        }else {//
            holder.title_news.setText(title); // set in TextView title in item_layout.xml
        }
        // handle date by Using IOS8601DateParsing to => 10 ago
        Date date=null;
        try{
            date= ISO8601DateParser.parse(articles.get(position).getPublishedAt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.date_news.setReferenceTime(date.getTime()); // set In TextView in item_layout.xml
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                context.startActivity(DetailsArticle.newIntent(context,articles.get(position).getUrl())); // move to DetailsArticle and send url
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
class ListNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public CircleImageView image_news;
    public TextView title_news;
    public RelativeTimeTextView date_news;
    private ItemClickListener itemClickListener;
    public ListNewsViewHolder(View itemView) {
        super(itemView);
        image_news=itemView.findViewById(R.id.image_news);
        title_news=itemView.findViewById(R.id.title_news);
        date_news=itemView.findViewById(R.id.date_news);
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
