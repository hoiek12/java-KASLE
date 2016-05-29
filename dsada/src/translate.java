import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class translate {
	public static void main(String args){
		Translate.setClientId("c3dc57a1-5b2f-47d2-9827-a34f8b6c5f0f");
		Translate.setClientSecret("TdLm0TS6XRWQMI963p9yL3rJ0Yj+EC5YjAeFX6n2XBo");
		         
		try {
		    String translatedText = Translate.execute("¾È³ç", Language.KOREAN, Language.ENGLISH);
		    System.out.println(translatedText);
		}catch (Exception e) {
		    e.printStackTrace();
		}
		System.out.println("dsadasd");
	}
}