package io.study.deneb.flow;

import io.study.deneb.TempInfo;
import io.study.deneb.TempProcessor;
import io.study.deneb.TempSubscriber;
import io.study.deneb.TempSubscription;

import java.util.concurrent.Flow.*;

public class Main {
  public static void main(String[] args) {
    //getTemperatures("Seoul").subscribe(new TempSubscriber());
    // 화씨 -> 섭씨 온도 변횐
    getCelsiusTemperatures("Tokyo").subscribe(new TempSubscriber());
  }

  private static Publisher<TempInfo> getTemperatures(String town) {
    return subscriber -> subscriber.onSubscribe(
      new TempSubscription(subscriber, town));
  }

  private static Publisher<TempInfo> getCelsiusTemperatures(String town) {
    return subscriber -> {
      TempProcessor processor = new TempProcessor();
      processor.subscribe(subscriber);
      processor.onSubscribe(new TempSubscription(processor, town));
    };
  }

}
