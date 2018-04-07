package hugo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class CryptocurrencyTest {
    
  @Mock
  Client jerseyClient;
  
  @Mock
  Builder builder;
  
  @Mock
  WebResource resource;
  @Mock
  ClientResponse response;
  
  private static final List<JSONObject> CRYPTO_TEST_DATA = new ArrayList<JSONObject>();
   
  @Before
  public void setUp() throws JSONException {
    CRYPTO_TEST_DATA.add(new JSONObject("{ \"id\": \"bitcoin\", "
        + "\"price_usd\": \"100.0\",\"market_cap_usd\": \"100.0\" }"));
  }
  
  @Test
  public void testCryptoCurrencyPriceAndMarketCapPrinted() throws JSONException, RestApiException {
    
    when(jerseyClient.resource(anyString())).thenReturn(resource);
    when(resource.accept(anyString())).thenReturn(builder);
    when(builder.get(ClientResponse.class)).thenReturn(response);
    when(response.getEntity(List.class)).thenReturn(CRYPTO_TEST_DATA);
    when(response.getStatus()).thenReturn(200);
    CryptocurrencyRestService rest = new CryptocurrencyRestService(jerseyClient);
    
    String cryptoCurr = rest.getCryptoInfo("bitcoin");
    Assert.assertThat(cryptoCurr,containsString("Price: USD")); 
    Assert.assertThat(cryptoCurr,containsString("Market Cap: USD"));    

  }
}
