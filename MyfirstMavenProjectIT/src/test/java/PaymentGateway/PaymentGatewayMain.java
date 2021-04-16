package PaymentGateway;

import java.io.IOException;
//import com.opencsv.exceptions.CsvValidationException;

public class PaymentGatewayMain {

	public static void main(String[] args) throws IOException, InterruptedException  {
		// TODO Auto-generated method stub
		// Create an instance of the PaymentGatewayKeywords class
		
		PaymentGatewayKeywords pgKeywords = new PaymentGatewayKeywords();
		
		// call runTest
		pgKeywords.runTestStart();

		//pgKeywords.runTestAutoGeneratedData();
		//pgKeywords.runTestAlert();
		pgKeywords.runTestReadFromFile();
		//pgKeywords.creditlimt();



		pgKeywords.cleanup();
	}

}