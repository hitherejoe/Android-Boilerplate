package uk.co.ribot.androidboilerplate.data.remote;

import uk.co.ribot.androidboilerplate.data.model.Ribot;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

public interface RibotsService {

    String ENDPOINT = "https://ribots-api.ribot.io";

    @GET("/ribots")
    Observable<List<Ribot>> getRibots();
}
