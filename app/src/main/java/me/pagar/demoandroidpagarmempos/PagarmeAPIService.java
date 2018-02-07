package me.pagar.demoandroidpagarmempos;

import java.util.List;
import java.util.Map;
import me.pagar.demoandroidpagarmempos.models.MainRecipientBalance;
import me.pagar.demoandroidpagarmempos.models.Transaction;
import me.pagar.demoandroidpagarmempos.models.UserCredentials;
import me.pagar.demoandroidpagarmempos.models.UserInfo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by victor on 26/01/18.
 */

public interface PagarmeAPIService {
  public static final String BASE_URL = "https://api.pagar.me/1/";

  // Validate session
  @POST("sessions")
  Call<UserInfo> createSession(@Body UserCredentials credentials);

  // GET the main recipient balance
  @GET("balance")
  Call<MainRecipientBalance> getMainBalance(@Query("session_id") String sessionId,
                                            @Query("live") String isLive);

  // GET the most recent transactions
  @GET("transactions")
  Call<List<Transaction>> getTransactions(@Query("session_id") String sessionId,
                                          @Query("live") String isLive);
}
