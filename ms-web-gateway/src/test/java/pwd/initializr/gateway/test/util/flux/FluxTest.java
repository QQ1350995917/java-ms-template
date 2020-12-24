package pwd.initializr.gateway.test.util.flux;

import java.time.Duration;
import reactor.core.publisher.Flux;

/**
 * pwd.initializr.gateway.test.util.flux@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2020-12-23 11:06
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class FluxTest {

    public static void main(String[] args) {
        test1();

    }

    private static void test1(){
        Flux.interval(Duration.ofSeconds(2)).doOnNext(System.out::println).blockLast();
    }
    private static  void test0 (){
        Flux.range(1, 10)
            .timeout(Flux.never(), v -> Flux.never())
            .subscribe(System.out::println);

    }
}
