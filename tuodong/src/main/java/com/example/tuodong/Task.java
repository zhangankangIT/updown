package com.example.tuodong;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Task {
    @GET("ios/cf/dish_list.php?stage_id=1&limit=20")
    Observable<Bean>getInit();
}
