package ntut.csie.sslab.team.application.springboot.web.config;


import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBus;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("TeamEventBusInjection")
public class TeamEventBusInjection {
    @Bean(name="teamEventBus")
    public DomainEventBus eventBus() {
        return new GoogleEventBus();
    }
}