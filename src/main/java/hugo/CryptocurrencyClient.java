package hugo;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import java.util.Scanner;
import java.util.logging.Logger;

import org.codehaus.jettison.json.JSONException;
/**
 * Entry point to making an api call to <a href=https://api.coinmarketcap.com>coinmarketcap</a>
 * for a given crypto-currency.
 * @author Peter Wachira
 *
 */

class CryptocurrencyClient {
  private static Logger log = Logger.getLogger(CryptocurrencyClient.class.getName());
  private static final String EXIT_CMD = "exit";
  
  public static void main(String[] args) throws JSONException {
    System.out.println("Enter a valid cryptocurrency name to get its price and market cap:");
    System.out.println("To exit the application, type in the word \"exit\"");
    try (Scanner scanner = new Scanner(System.in)) {
      while (true) {
        String cryptoName = scanner.nextLine();
        if (cryptoName.equals(EXIT_CMD)) {
          break;
        }
        if (!cryptoName.isEmpty()) {
          ClientConfig clientConfig = new DefaultClientConfig();
          clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
          Client jerseyClient = Client.create(clientConfig);
          CryptocurrencyRestService rest = new CryptocurrencyRestService(jerseyClient);
          try {
            System.out.println(rest.getCryptoInfo(cryptoName));
          } catch (RestApiException  | IllegalArgumentException ex) {
            log.severe("There was an error calling the rest API. "
                    + "You may have supplied an invalid  cryptocurrency \n"
                    + ex);
          }
        } else {
          log.warning("Please supply a valid cryptocurrency name.");
        }
      }
    }
    System.out.println("Exiting the application");
  }
}