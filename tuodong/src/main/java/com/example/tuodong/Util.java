package com.example.tuodong;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class Util extends ItemTouchHelper.Callback{
    private TouchCallBack tou;
    private boolean swipe = true;
        public Util(TouchCallBack tou) {
            this.tou = tou;
        }
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            //上下
            int i = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            //向左
            int left = ItemTouchHelper.LEFT;
            return makeMovementFlags(i, left);
        }
        /*
           拖动item时回调，可以调用Adapter的notifyMoved方法来交换两个ViewHolder的位置
           最后返回true，表示被拖动的ViewHolder已经移动到了目的的位置
            */
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder viewHolder1) {
            tou.itemMove(viewHolder.getAdapterPosition(),viewHolder1.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            tou.onItemDelete(viewHolder.getAdapterPosition());
        }
        //支持长安拖拽默认是true

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        //支持滑动删除，即可以调用到onSwiped()方法，默认是true

        @Override
        public boolean isItemViewSwipeEnabled() {
            return swipe;
        }
        //设置是否支持上面滑动

        public void setSwipe(boolean swipe) {
            this.swipe = swipe;
        }
}
