package ch.mav.mcve_testcontainers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootApplication
@Profile("local")
class LocalApplication {

    @TestConfiguration(proxyBeanMethods = false)
    static class LocalPostgreSqlContainerConfig {

        @Bean
        @ServiceConnection
        public PostgreSQLContainer<?> postgreSqlContainer() {
            return new PostgreSQLContainer<>("postgres:16-alpine")
                .withReuse(true);
        }
    }

    public static void main(final String[] args) {
        SpringApplication
            .from(Application::main)
            .withAdditionalProfiles("local")
            .with(LocalPostgreSqlContainerConfig.class)
            .run(args);
    }
}
