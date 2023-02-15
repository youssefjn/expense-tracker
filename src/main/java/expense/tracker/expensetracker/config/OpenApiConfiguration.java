package expense.tracker.expensetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI expenseApi() {

        return new OpenAPI()
                .info(new Info().title("expense tracker API")
                        .description("Expense tracker Application"));
    }
}
