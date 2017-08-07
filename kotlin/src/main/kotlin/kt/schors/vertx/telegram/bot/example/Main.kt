package kt.schors.vertx.telegram.bot.example

import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions

fun main(args: Array<String>) {
    var options = VertxOptions().setWorkerPoolSize(40)
    val vertx = Vertx.vertx(options)
//    val vertx = Vertx.vertx(VertxOptions(workerPoolSize = 40))
    var deploymentOptions = DeploymentOptions().setInstances(1)
//    vertx.deployVerticle(TestBot(), DeploymentOptions(instances = 1))
    vertx.deployVerticle(TestBot(), deploymentOptions)

}
