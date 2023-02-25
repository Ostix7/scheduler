package ua.edu.ukma.mandarin.scheduler.security.jwt;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@ConfigurationProperties(prefix = "scheduler.jwt")
public class JwtProperties {

  String secret;
}
