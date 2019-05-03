package com.example.tuodong;

public interface TouchCallBack {
        //数据交换
        void itemMove(int fromposition, int toPosition);
        //删除条目
        void onItemDelete(int position);

}
