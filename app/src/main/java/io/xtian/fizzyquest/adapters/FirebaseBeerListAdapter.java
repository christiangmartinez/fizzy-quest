package io.xtian.fizzyquest.adapters;

import android.content.Context;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import io.xtian.fizzyquest.models.Beer;
import io.xtian.fizzyquest.util.ItemTouchHelperAdapter;
import io.xtian.fizzyquest.util.OnStartDragListener;

public class FirebaseBeerListAdapter extends FirebaseRecyclerAdapter<Beer, FirebaseBeerViewHolder> implements ItemTouchHelperAdapter{
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseBeerListAdapter(Class<Beer> modelClass, int modelLayout,
                                   Class<FirebaseBeerViewHolder> viewHolderClass,
                                   Query ref, OnStartDragListener onStartDragListener, Context context) {

        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void populateViewHolder(FirebaseBeerViewHolder viewHolder, Beer model, int position) {
        viewHolder.bindBeer(model);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}
