package io.study.deneb.observable;


import io.reactivex.Observable;
import io.study.deneb.TempInfo;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
  public static void main(String[] args) {
    // 1초마다 Long 타입의 값을 방출(emit)한다.
    Observable<Long> onPerSec = Observable.interval(1, TimeUnit.SECONDS);

    // i: 0, 1, 2, 3, 4 .... 00
    // 1초마다 서울의 온도를 읽어온다.
    /*
    onPerSec.blockingSubscribe(i -> {
      System.out.println(TempInfo.fetch("Seoul"));
    });
     */

//    Observable<TempInfo> tokyoTemperatureObserver = getCelsiusTemperature("Tokyo");
//    tokyoTemperatureObserver.blockingSubscribe(new TempObserver());

    Observable<TempInfo> europeCitiesTemp = getCelsiusTemperatures("paris", "london", "berlin");
    europeCitiesTemp.blockingSubscribe(new TempObserver());


  }
  // 여러가지의 도시 온도를 값을 병합(merge) gksek
  public static Observable<TempInfo> getCelsiusTemperatures(String... towns) {
    return Observable.merge(Stream.of(towns)
      .map(Main::getCelsiusTemperature)
      .collect(Collectors.toList()));

  }

  // Observable map()을 활용하여 화씨->섭씨 변환을 구현한다
  public static Observable<TempInfo> getCelsiusTemperature(String town) {
    return getTemperatures(town)
      .map(temp -> new TempInfo(temp.getTown(),
        (temp.getTemp() - 32) * 5 / 9));
  }

  // 팩토리 패턴 옵져빙을 구현한다.
  public static Observable<TempInfo> getTemperatures(String town) {
    return Observable.create(emitter -> {
      Observable.interval(1, TimeUnit.SECONDS)
        .subscribe(i -> {
          // Observer 폐기여부 검증
          if (!emitter.isDisposed()) {
            // 5번의 방출을 확인하였으며, 옵져빙 종료
            if (i >= 5) {
              emitter.onComplete();
              return;
            }

            try {
              emitter.onNext(TempInfo.fetch(town));
            } catch (Exception e) {
              emitter.onError(e); // 예외 발생 시 옵져버에 공유
            }
          }

        });
    });

  }
}
