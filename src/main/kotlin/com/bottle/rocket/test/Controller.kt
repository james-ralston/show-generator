package com.bottle.rocket.test

import com.bottle.rocket.test.model.AdAsset
import com.bottle.rocket.test.model.Container
import com.bottle.rocket.test.model.ImageAsset
import com.bottle.rocket.test.model.VideoAsset
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.util.function.component1
import reactor.util.function.component2
import reactor.util.function.component3
import java.util.*

@RestController
@RequestMapping("/v1")
class ShowController() {

    private val random = Random()

    @GetMapping("/generate")
    fun generateShow(): Mono<Container> = Mono.zip(
            Mono.just(VideoAsset.generate()),
            Mono.just(AdAsset.generate()),
            Mono.just(ImageAsset.generate())
    ).map { (videoAsset, adAsset, imageAsset) -> listOf(videoAsset, adAsset, imageAsset) }
            .map { assets ->
                Container(
                        name = "new show",
                        description = "cool new show",
                        assets = assets
                )
            }
            .doOnNext { show ->
                println(" a new show ${show.name} has been created with the following assets:")
                show.assets.forEach { it.prettyPrint()}
            }
            .map { it }
}