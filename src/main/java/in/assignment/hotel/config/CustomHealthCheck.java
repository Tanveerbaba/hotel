package in.assignment.hotel.config;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthCheck extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        builder.up().withDetail("Hotel Service", "Service is UP!!!");
    }
}
