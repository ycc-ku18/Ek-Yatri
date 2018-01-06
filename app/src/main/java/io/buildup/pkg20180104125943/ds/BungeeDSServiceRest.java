
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

public interface BungeeDSServiceRest{

	@GET("/app/5a4e253b78cda5040075be72/r/bungeeDS")
	void queryBungeeDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<BungeeDSItem>> cb);

	@GET("/app/5a4e253b78cda5040075be72/r/bungeeDS/{id}")
	void getBungeeDSItemById(@Path("id") String id, Callback<BungeeDSItem> cb);

	@DELETE("/app/5a4e253b78cda5040075be72/r/bungeeDS/{id}")
  void deleteBungeeDSItemById(@Path("id") String id, Callback<BungeeDSItem> cb);

  @POST("/app/5a4e253b78cda5040075be72/r/bungeeDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<BungeeDSItem>> cb);

  @POST("/app/5a4e253b78cda5040075be72/r/bungeeDS")
  void createBungeeDSItem(@Body BungeeDSItem item, Callback<BungeeDSItem> cb);

  @PUT("/app/5a4e253b78cda5040075be72/r/bungeeDS/{id}")
  void updateBungeeDSItem(@Path("id") String id, @Body BungeeDSItem item, Callback<BungeeDSItem> cb);

  @GET("/app/5a4e253b78cda5040075be72/r/bungeeDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/5a4e253b78cda5040075be72/r/bungeeDS")
    void createBungeeDSItem(
        @Part("data") BungeeDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<BungeeDSItem> cb);
    
    @Multipart
    @PUT("/app/5a4e253b78cda5040075be72/r/bungeeDS/{id}")
    void updateBungeeDSItem(
        @Path("id") String id,
        @Part("data") BungeeDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<BungeeDSItem> cb);
}
