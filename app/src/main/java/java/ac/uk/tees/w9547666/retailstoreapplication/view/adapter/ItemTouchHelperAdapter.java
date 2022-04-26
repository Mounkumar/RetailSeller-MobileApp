package java.ac.uk.tees.w9547666.retailstoreapplication.view.adapter;

/**
 * @author w9547666
 */

public interface ItemTouchHelperAdapter {


    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
