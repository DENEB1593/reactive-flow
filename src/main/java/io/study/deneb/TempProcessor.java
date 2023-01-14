package io.study.deneb;

import java.util.concurrent.Flow.*;

/**
 * subscribe 후 수신된 데이터를 변환한다.
 */
public class TempProcessor implements Processor<TempInfo, TempInfo> {

  private Subscriber<? super TempInfo> subscriber;

  @Override
  public void subscribe(Subscriber subscriber) {
    this.subscriber = subscriber;
  }

  @Override
  public void onNext(TempInfo temp) {
    // 섭씨 -> 화씨로 변환하여 Subscriber에게 전달한다.
    subscriber.onNext(
      new TempInfo(temp.getTown(), (temp.getTemp() - 32) * 5 / 9));
  }

  @Override
  public void onSubscribe(Subscription subscription) {
    subscriber.onSubscribe(subscription);
  }

  @Override
  public void onError(Throwable throwable) {
    subscriber.onError(throwable);
  }

  @Override
  public void onComplete() {
    subscriber.onComplete();
  }
}
