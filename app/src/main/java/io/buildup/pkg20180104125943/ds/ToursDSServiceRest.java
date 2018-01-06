
package io.buildup.pkg20180104125943.ds;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Path;
import retrofit.http.PUT;
import retrofit.mime.TypedByteArray;
import retrofit.http.Part;
import retrofit.http.Multipart;

public interface ToursDSServiceRest{

	@GET("/app/5a4e253b78cda5040075be72/r/toursDS")
	void queryToursDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<ToursDSItem>> cb);

	@GET("/app/5a4e253b78cda5040075be72/r/toursDS/{id}")
	void getToursDSItemById(@Path("id") String id, Callback<ToursDSItem> cb);

	@DELETE("/app/5a4e253b78cda5040075be72/r/toursDS/{id}")
  void deleteToursDSItemById(@Path("id") String id, Callback<ToursDSItem> cb);

  @POST("/app/5a4e253b78cda5040075be72/r/toursDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<ToursDSItem>> cb);

  @POST("/app/5a4e253b78cda5040075be72/r/toursDS")
  void createToursDSItem(@Body ToursDSItem item, Callback<ToursDSItem> cb);

  @PUT("/app/5a4e253b78cda5040075be72/r/toursDS/{id}")
  void updateToursDSItem(@Path("id") String id, @Body ToursDSItem item, Callback<ToursDSItem> cb);

  @GET("/app/5a4e253b78cda5040075be72/r/toursDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/5a4e253b78cda5040075be72/r/toursDS")
    void createToursDSItem(
        @Part("data") ToursDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<ToursDSItem> cb);
    
    @Multipart
    @PUT("/app/5a4e253b78cda5040075be72/r/toursDS/{id}")
    void updateToursDSItem(
        @Path("id") String id,
        @Part("data") ToursDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<ToursDSItem> cb);
}
