package integra.pesintegra.Services;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import integra.pesintegra.Logic.Clases.Comentari;
import integra.pesintegra.Logic.Clases.Post;
import integra.pesintegra.Logic.Clases.Post_Activitat;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostService {

    @GET("/post/get")
    Call<Post> getPost(@Query("id") String value);

    @GET("/post/all")
    Call<ArrayList<Post>> getAllPosts(@Query("type") String type, @Query("order") Integer order);

    @GET("/post/allUser")
    Call<ArrayList<Post>> getAllPostsFromUser(@Query("owner") String owner);

    @GET("/post/allHiddenUser")
    Call<ArrayList<Post>> getHiddenListFromUser(@Query("owner") String owner);

    @GET("/post/rating")
    Call<JsonObject> getPostRating(@Query("postid") String postID);

    @GET("/post/userAttends")
    Call<String> userAssisteix(@Query("postid") String postid, @Query("userid") String userid);

    @POST("post/new")
    Call<Void> createPost(@Body Post post);

    @DELETE("/post/delete")
    Call<Void> deletePost(@Query("id") String value);

    @PATCH("post/update")
    Call<Void> updatePost(@Query("postid") String postid, @Body Post post);

    @PATCH("post/attend")
    Call<Integer> addAttendant(@Query("postid") String postid, @Query("userid") String userid);

    @PATCH("post/unattend")
    Call<Integer> removeAttendant(@Query("postid") String postid, @Query("userid") String userid);

    @PATCH("post/vote")
    Call<JsonObject> votePost(@Query("postid") String postid, @Query("punts") String punts);

    @PATCH("post/newComment")
    Call<Void> createComment(@Query("postid") String postid,@Body Comentari comentari);

    @DELETE("post/deleteComment")
    Call<Void> deleteComment(@Query("postid") String postid, @Query("commentid") String commentid);

    @GET("post/userVote")
    Call<String> getUserVote(@Query("postid") String postid, @Query("userid") String userid);

    @GET("post/advancedSearch")
    Call<ArrayList<Post>> advancedSearch(@Query("text") String text, @Query("tipus") String tipus, @Query("lang") String lang, @Query("dateIni") String dateIni,@Query("dateFi") String dateFi, @Query("user") String user, @Query("tags") ArrayList<String> tags);

    @GET("/users/getBookedPosts")
    Call<ArrayList<Post>> getBookedPosts(@Query("userid") String userid);

    @GET("/post/searchByTags")
    Call<ArrayList<Post>> getPostsByTags( @Query("tags") ArrayList<String> listtags);

    @PATCH("/mod/rejectReport")
    Call<Void> rejectReportPost( @Query("postid") String postid);

    @PUT("post/report")
    Call<Void> reportPost(@Query("postid") String postid, @Query("userid") String userid);

    @GET("post/hasReported")
    Call<String> isReported(@Query("postid") String postid, @Query("userid") String userid);

    @GET("/post/reportedPosts")
    Call<ArrayList<Post>> getReportedPosts();

    @PUT("post/reportComment")
    Call<Void> reportComment(@Query("postid") String postid, @Query("comentid") String commentid, @Query("userid") String userid);

    @GET("/post/hasReportedComments")
    Call<ArrayList<Post>> getCommentedReportedPosts();
}
