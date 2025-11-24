package com.webflux;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;

public class MonoFluxTest {
	@Test
	public void testMono() {
		Mono<String> monoString=Mono.just(" spring boot");
		monoString.subscribe(System.out::println);
	}
}
