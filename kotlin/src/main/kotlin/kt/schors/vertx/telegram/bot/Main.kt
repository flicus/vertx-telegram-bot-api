package kt.schors.vertx.telegram.bot

import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.core.json.JsonArray

fun main(args: Array<String>) {

    val arr: JsonArray = JsonArray()
    arr.add("1")
    arr.add("2")
    arr.add("3")
    arr
            .asSequence()
            .filter { it is String }
            .map {
                (it as String).toInt()
            }
            .filter { it >= 2 }
            .forEach { println(it) }


    var options = VertxOptions().setWorkerPoolSize(40)
//    var vertx = Vertx.vertx(VertxOptions(workerPoolSize = 40))
    var vertx = Vertx.vertx(options)

//    var deploymentOptions = DeploymentOptions(instances = 1)
    var deploymentOptions = DeploymentOptions().setInstances(1)
    vertx.deployVerticle(TestVert(), deploymentOptions)
}
