package hugo;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * This class makes an api call to <a href=https://api.coinmarketcap.com/v1/ticker>coinmarketcap</a>.
 * @author Peter Wachira
 *
 */

public class CryptocurrencyRestService {
  private static final String URL = "https://api.coinmarketcap.com/v1/ticker/";
  private Client jerseyClient;
  
  public CryptocurrencyRestService(Client jerseyClient) {
    this.jerseyClient = jerseyClient;
  }

  /** 
   * This method makes an api call to return the price 
   *    and market cap in USD of any given cryptocurrency.
   *    
   * @param cryptoName Name of the Cryptocurrency to print information for.
   * @return price and market cap in USD as a String.
   * @throws JSONException if response is not well formatted JSON.
   * @throws RestApiException if there is an http error code.
   * 
   */
  public String getCryptoInfo(String cryptoName) throws JSONException, RestApiException {

    WebResource resource = jerseyClient.resource(URL + cryptoName + "/");
    ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

    if (response.getStatus() != 200) {
      throw new RestApiException("Invalid cryptocurrency name was entered!");
    } else {
      @SuppressWarnings("unchecked")
      List<Object>  data = response.getEntity(List.class);
      JSONObject json = new JSONObject(data.get(0).toString());
      return ("Price: USD " + json.getString("price_usd")
            + "\nMarket Cap: USD " + json.getString("market_cap_usd"));
    }
  }
    

}
