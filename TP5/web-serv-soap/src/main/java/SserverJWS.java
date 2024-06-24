import jakarta.xml.ws.Endpoint;
import ws.BanqueService;

public class SserverJWS {
    public static void main(String[] args) {
        String url = "http://localhost:9090/";
        Endpoint.publish(url,new BanqueService());
        System.out.println("Web service is ready on "+url);
    }
}
