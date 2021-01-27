package userTransactionRestAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import userTransactionRestAPI.transaction.TransactionController;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ApplicationTests {

	@Autowired
	private TransactionController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}