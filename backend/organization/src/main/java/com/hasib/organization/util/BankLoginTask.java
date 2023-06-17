package com.hasib.organization.util;

import com.hasib.organization.dto.bank.BankLoginDto;
import com.hasib.organization.dto.bank.BankLoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class BankLoginTask {

    private final WebClient bankClient;

    @Autowired
    public BankLoginTask(WebClient bankClient) {
        this.bankClient = bankClient;
    }

    @Scheduled(fixedRate = ApplicationConstants.PERIODICAL_LOGIN_DELAY)
    public void loginPeriodically() {
        BankLoginDto loginDto = new BankLoginDto("org", "org");
        bankClient.post()
                .uri("/auth/login")
                .body(Mono.just(loginDto), BankLoginDto.class)
                .retrieve()
                .toEntity(BankLoginResponseDto.class)
                .subscribe(response -> {
                    ApplicationConstants.BANK_TOKEN = Objects.requireNonNull(response.getBody()).getAccessToken();
                }, System.out::println);
    }
}
