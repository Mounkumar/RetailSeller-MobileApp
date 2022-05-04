package java.ac.uk.tees.w9547666.retailstoreapplication.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.ac.uk.tees.w9547666.retailstoreapplication.R;
import java.ac.uk.tees.w9547666.retailstoreapplication.model.Repository;
import java.ac.uk.tees.w9547666.retailstoreapplication.model.entities.Money;
import java.ac.uk.tees.w9547666.retailstoreapplication.model.entities.Product;
import java.ac.uk.tees.w9547666.retailstoreapplication.util.ColorGenerator;
import java.ac.uk.tees.w9547666.retailstoreapplication.util.Utils;
import java.ac.uk.tees.w9547666.retailstoreapplication.view.activities.HomeActivity;
import java.ac.uk.tees.w9547666.retailstoreapplication.view.customview.TextDrawable;
import java.ac.uk.tees.w9547666.retailstoreapplication.view.customview.TextDrawable.IBuilder;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author w9547666
 */

public class ProductListAdapter extends
        RecyclerView.Adapter<ProductListAdapter.VersionViewHolder> implements
        ItemTouchHelperAdapter {

    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;

    private IBuilder mDrawableBuilder;

    private TextDrawable drawable;

    private String ImageUrl;

    private List<Product> productList = new ArrayList<Product>();
    private OnItemClickListener clickListener;

    private Context context;

    public ProductListAdapter(String subcategoryKey, Context context,
                              boolean isCartlist) {

        if (isCartlist) {

            productList = Repository.getCenterRepository()
                    .getListOfProductsInShoppingList();

        } else {

            productList = Repository.getCenterRepository().getMapOfProductsInCategory()
                    .get(subcategoryKey);
        }

        this.context = context;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_product_list, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    public int getImage(String imageName) {

      //  int drawableResourceId = this.getResources().getIdentifier(imageName, "drawable", this.getPackageName());

        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }
    @Override
    public void onBindViewHolder(final VersionViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {

        holder.itemName.setText(productList.get(position)
                .getItemName());

        holder.itemDesc.setText(productList.get(position)
                .getItemShortDesc());
        String sellCostString = Money.rupees(
                BigDecimal.valueOf(Double.parseDouble(productList.get(position)
                        .getSellMRP())))
                + "  ";
        String buyMRP = Money.rupees(
                BigDecimal.valueOf(Double.parseDouble(productList.get(position)
                        .getMRP()))).toString();
        String costString = sellCostString + buyMRP;

        holder.itemCost.setText(costString, BufferType.SPANNABLE);

        Spannable spannable = (Spannable) holder.itemCost.getText();

        spannable.setSpan(new StrikethroughSpan(), sellCostString.length(),
                costString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mDrawableBuilder = TextDrawable.builder().beginConfig().withBorder(4)
                .endConfig().roundRect(10);

        drawable = mDrawableBuilder.build(String.valueOf(productList
                .get(position).getItemName().charAt(0)), mColorGenerator
                .getColor(productList.get(position).getItemName()));

        ImageUrl = productList.get(position).getImageURL();


        Glide.with(context).load(getImage(ImageUrl)).placeholder(drawable)
                .error(drawable)
                .centerCrop().into(holder.imagView);


        holder.addItem.findViewById(R.id.add_item).setOnClickListener(
                v -> {


                    //current object
                    Product tempObj = productList.get(position);


                    //if current object is lready in shopping list
                    if (Repository.getCenterRepository()
                            .getListOfProductsInShoppingList().contains(tempObj)) {


                        //get position of current item in shopping list
                        int indexOfTempInShopingList = Repository
                                .getCenterRepository().getListOfProductsInShoppingList()
                                .indexOf(tempObj);

                        // increase quantity of current item in shopping list
                        if (Integer.parseInt(tempObj.getQuantity()) == 0) {

                            ((HomeActivity) getContext())
                                    .updateItemCount(true);

                        }


                        // update quanity in shopping list
                        Repository
                                .getCenterRepository()
                                .getListOfProductsInShoppingList()
                                .get(indexOfTempInShopingList)
                                .setQuantity(
                                        String.valueOf(Integer
                                                .parseInt(tempObj
                                                        .getQuantity()) + 1));


                        //update checkout amount
                        ((HomeActivity) getContext()).updateCheckOutAmount(
                               new BigDecimal(productList.get(position).getSellMRP())
                               /* BigDecimal
                                        .valueOf(Long
                                                .valueOf(productList
                                                        .get(position)
                                                        .getSellMRP()))*/,
                                true);

                        // update current item quanitity
                        holder.quanitity.setText(tempObj.getQuantity());

                    } else {

                        ((HomeActivity) getContext()).updateItemCount(true);

                        tempObj.setQuantity(String.valueOf(1));

                        holder.quanitity.setText(tempObj.getQuantity());

                        Repository.getCenterRepository()
                                .getListOfProductsInShoppingList().add(tempObj);

                        ((HomeActivity) getContext()).updateCheckOutAmount(
                                new BigDecimal(productList.get(position).getSellMRP())
                                /*BigDecimal
                                        .valueOf(Long
                                                .valueOf(productList
                                                        .get(position)
                                                        .getSellMRP()))*/,
                                true);

                    }

                    Utils.vibrate(getContext());

                });

        holder.removeItem.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Product tempObj = (productList).get(position);

                if (Repository.getCenterRepository().getListOfProductsInShoppingList()
                        .contains(tempObj)) {

                    int indexOfTempInShopingList = Repository
                            .getCenterRepository().getListOfProductsInShoppingList()
                            .indexOf(tempObj);

                    if (Integer.parseInt(tempObj.getQuantity()) != 0) {

                        Repository
                                .getCenterRepository()
                                .getListOfProductsInShoppingList()
                                .get(indexOfTempInShopingList)
                                .setQuantity(
                                        String.valueOf(Integer.parseInt(tempObj
                                                .getQuantity()) - 1));

                        ((HomeActivity) getContext()).updateCheckOutAmount(
                                BigDecimal.valueOf(Long.parseLong(productList
                                        .get(position).getSellMRP())),
                                false);

                        holder.quanitity.setText(Repository
                                .getCenterRepository().getListOfProductsInShoppingList()
                                .get(indexOfTempInShopingList).getQuantity());

                        Utils.vibrate(getContext());

                        if (Integer.parseInt(Repository
                                .getCenterRepository().getListOfProductsInShoppingList()
                                .get(indexOfTempInShopingList).getQuantity()) == 0) {

                            Repository.getCenterRepository()
                                    .getListOfProductsInShoppingList()
                                    .remove(indexOfTempInShopingList);

                            notifyDataSetChanged();

                            ((HomeActivity) getContext())
                                    .updateItemCount(false);

                        }

                    }

                }

            }

        });

    }


    private HomeActivity getContext() {

        return (HomeActivity) context;
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public void SetOnItemClickListener(
            final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public void onItemDismiss(int position) {
        productList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(productList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(productList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements
            OnClickListener {
        TextView itemName, itemDesc, itemCost, availability, quanitity,
                addItem, removeItem;
        ImageView imagView;

        public VersionViewHolder(View itemView) {
            super(itemView);

            itemName = (TextView) itemView.findViewById(R.id.item_name);

            itemDesc = (TextView) itemView.findViewById(R.id.item_short_desc);

            itemCost = (TextView) itemView.findViewById(R.id.item_price);

            availability = (TextView) itemView
                    .findViewById(R.id.iteam_avilable);

            quanitity = (TextView) itemView.findViewById(R.id.iteam_amount);

            itemName.setSelected(true);

            imagView = ((ImageView) itemView.findViewById(R.id.product_thumb));

            addItem = ((TextView) itemView.findViewById(R.id.add_item));

            removeItem = ((TextView) itemView.findViewById(R.id.remove_item));

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

}
