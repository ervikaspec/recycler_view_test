package star.agro.com.agrostartest.Adapter;

/**
 * Created by vikasmalhotra on 3/13/16.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import star.agro.com.agrostartest.Model.Catalogue;
import star.agro.com.agrostartest.R;

public class CatalogueAdapter extends RecyclerView.Adapter<CatalogueAdapter.ViewHolder> {

    List<Catalogue> data;

    public CatalogueAdapter() {
        super.setHasStableIds(true);
        this.data = new ArrayList();
    }

    public void setData(List<Catalogue> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup group, int type) {
        ViewHolder element;
        LinearLayout view = (LinearLayout) LayoutInflater.from(group.getContext()).inflate(R.layout.item_view, group, false);
        element = new ViewHolder(view);
        return element;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Catalogue catalogue = data.get(position);
        holder.title.setText(catalogue.title);
        holder.subtitle.setText(catalogue.subtitle);
        holder.imageView.setImageResource(R.drawable.mobile); //can use catalogue.uri here
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title, subtitle;
        LinearLayout rootView;

        public ViewHolder(LinearLayout itemView) {
            super(itemView);
            this.rootView = itemView;
            this.imageView = (ImageView) this.rootView.findViewById(R.id.imageView);
            this.title = (TextView) this.rootView.findViewById(R.id.title);
            this.subtitle = (TextView) this.rootView.findViewById(R.id.subtitle);
        }
    }
}
