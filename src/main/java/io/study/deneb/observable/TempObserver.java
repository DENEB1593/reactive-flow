package io.study.deneb.observable;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.study.deneb.TempInfo;

public class TempObserver implements Observer<TempInfo> {
  @Override
  public void onSubscribe(@NonNull Disposable disposable) {


  }

  @Override
  public void onNext(@NonNull TempInfo tempInfo) {
    System.out.println(tempInfo); //온도를 출력한다
  }

  @Override
  public void onError(@NonNull Throwable throwable) {
    System.out.println("Some problem: " + throwable.getMessage());
  }

  @Override
  public void onComplete() {
    System.out.println("FIN");
  }
}
