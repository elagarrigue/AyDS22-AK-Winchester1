package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.artistinfo;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WikipediaArtistInfoAPI {

  @GET("api.php?action=query&list=search&utf8=&format=json&srlimit=1")
   Call<String> getArtistInfo(@Query("srsearch") String artist);

}
