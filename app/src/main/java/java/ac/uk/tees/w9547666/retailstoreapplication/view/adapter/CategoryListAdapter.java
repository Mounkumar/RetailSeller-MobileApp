package java.ac.uk.tees.w9547666.retailstoreapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.ac.uk.tees.w9547666.retailstoreapplication.R;
import java.ac.uk.tees.w9547666.retailstoreapplication.model.Repository;
import java.ac.uk.tees.w9547666.retailstoreapplication.model.entities.ProductCategory;
import java.ac.uk.tees.w9547666.retailstoreapplication.util.ColorGenerator;
import java.ac.uk.tees.w9547666.retailstoreapplication.view.customview.LabelView;
import java.ac.uk.tees.w9547666.retailstoreapplication.view.customview.TextDrawable;
import java.ac.uk.tees.w9547666.retailstoreapplication.view.customview.TextDrawable.IBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author w9547666
 */

public class CategoryListAdapter extends
        RecyclerView.Adapter<CategoryListAdapter.VersionViewHolder> {

    public static List<ProductCategory> categoryList = new ArrayList<ProductCategory>();
    OnItemClickListener clickListener;
    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private IBuilder mDrawableBuilder;
    private TextDrawable drawable;
    private String ImageUrl;
    private Context context;

    public CategoryListAdapter(Context context) {

        categoryList = Repository.getCenterRepository().getListOfCategory();

        this.context = context;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_category_list, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder,
                                 int categoryIndex) {

        versionViewHolder.itemName.setText(categoryList.get(categoryIndex)
                .getProdCategoryName());

        versionViewHolder.itemDesc.setText(categoryList.get(categoryIndex)
                .getProdCategoryDescription());

        mDrawableBuilder = TextDrawable.builder().beginConfig().withBorder(4)
                .endConfig().roundRect(10);

        drawable = mDrawableBuilder.build(String.valueOf(categoryList
                        .get(categoryIndex).getProdCategoryName().charAt(0)),
                mColorGenerator.getColor(categoryList.get(categoryIndex)
                        .getProdCategoryDescription()));

        ImageUrl = categoryList.get(categoryIndex).getProdCategoryImageUrl();

        Glide.with(context).load(ImageUrl).placeholder(drawable)
                .error(drawable)
                .centerCrop().into(versionViewHolder.imagView);

        LabelView label = new LabelView(context);
        label.setText(categoryList.get(categoryIndex)
                .getProdCategoryDiscount());
        label.setBackgroundColor(0xffE91E63);
        label.setTargetView(versionViewHolder.imagView, 10,
                LabelView.Gravity.RIGHT_TOP);

    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public void SetOnItemClickListener(
            final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        TextView itemName, itemDesc, itemCost, availability, quanitity,
                addItem, removeItem;
        ImageView imagView;

        public VersionViewHolder(View itemView) {
            super(itemView);

            itemName = (TextView) itemView.findViewById(R.id.item_name);

            itemDesc = (TextView) itemView.findViewById(R.id.item_short_desc);

            itemName.setSelected(true);

            imagView = ((ImageView) itemView.findViewById(R.id.imageView));

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

}
