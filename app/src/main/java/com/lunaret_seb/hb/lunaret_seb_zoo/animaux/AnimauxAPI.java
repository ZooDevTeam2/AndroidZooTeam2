package com.lunaret_seb.hb.lunaret_seb_zoo.animaux;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hb on 20/06/2016.
 */
public interface AnimauxAPI {

    @GET("list/animaux")
     Call<List<Animaux>> getListAnimaux() ;

}
